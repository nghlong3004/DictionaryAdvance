package util.repository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.json.JSONObject;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.gson.GsonFactory;
import util.PropertyHelper;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class GoogleOAuth2 {

  private static final Logger LOGGER = Logger.getLogger(GoogleOAuth2.class);
  // Setting logger
  static {
    try {
      // Create Layout out object
      PatternLayout layout = new PatternLayout();
      layout.setConversionPattern("[%-5l] %d{yyyy-MM-dd HH:mm:ss.SSS} %c{1}:%L - %m%n");
      // Create Appender object having layout object
      ConsoleAppender appender = new ConsoleAppender(layout);
      appender.setName("STDOUT");
      // Add Appender object to Logger object
      LOGGER.addAppender(appender);
      // Logger level to retrieve log message
      LOGGER.setLevel(Level.DEBUG);
      // Logger info
      LOGGER.info("GoogleOAuth2::Log4j Setup ready");

    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.fatal("GoogleOAuth2::Problem while setting up Log4j");
    }
  }

  private final PropertyHelper propertyHelper;

  public GoogleOAuth2(PropertyHelper propertyHelper) {
    this.propertyHelper = propertyHelper;
  }

  public String[] infoUserWithGoogle() throws Exception {
    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

    LOGGER.debug("GoogleOAuth2::infoUserWithGoogle Configuration AuthorizationCodeFlow");
    AuthorizationCodeFlow flow =
        new AuthorizationCodeFlow.Builder(BearerToken.authorizationHeaderAccessMethod(),
            new NetHttpTransport(), jsonFactory,
            new GenericUrl("https://oauth2.googleapis.com/token"),
            new ClientParametersAuthentication(propertyHelper.getGoogleClientID(),
                propertyHelper.getGoogleClientSecret()),
            propertyHelper.getGoogleClientID(), "https://accounts.google.com/o/oauth2/auth")
                .build();

    LOGGER.debug("GoogleOAuth2::infoUserWithGoogle Create URL Login");
    AuthorizationCodeRequestUrl authorizationUrl =
        flow.newAuthorizationUrl().setRedirectUri(propertyHelper.getGoogleRedirectUri())
            .setScopes(Collections.singletonList(propertyHelper.getGoogleScope()));
    String loginUrl = authorizationUrl.build();
    if (Desktop.isDesktopSupported()) {
      Desktop.getDesktop().browse(new URI(loginUrl));
    } else {
      LOGGER.debug(
          "GoogleOAuth2::infoUserWithGoogle Desktop is not supported. Please open the URL manually.");
      return null;
    }
    String redirectedUrl = getAuthorizationCode();
    if (redirectedUrl == null) {
      return null;
    }

    LOGGER.debug("GoogleOAuth2::infoUserWithGoogle Get URL on web");
    String authorizationCode = extractAuthorizationCode(redirectedUrl);
    if (authorizationCode == null) {
      LOGGER.debug(
          "GoogleOAuth2::infoUserWithGoogle Failed to extract authorization code. Please check the URL and try again.");
      return null;
    }

    String decodedAuthorizationCode = URLDecoder.decode(authorizationCode, StandardCharsets.UTF_8);

    LOGGER.debug("GoogleOAuth2::infoUserWithGoogle Exchange authorization code for Access Token");
    TokenResponse tokenResponse = flow.newTokenRequest(decodedAuthorizationCode)
        .setRedirectUri(propertyHelper.getGoogleRedirectUri()).execute();

    LOGGER.info("GoogleOAuth2::infoUserWithGoogle Get infomation user");
    return fetchUserInfo(tokenResponse.getAccessToken());
  }

  private String getAuthorizationCode() {
    try (ServerSocket server = new ServerSocket(8080)) {
      server.setSoTimeout(1000 * 60 * 3);
      Socket client = server.accept();
      BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      String line;
      String authorizationCode = null;
      LOGGER.info("GoogleOAuth2::getAuthorizationCode Read data from browser");
      while ((line = reader.readLine()) != null) {
        if (line.startsWith("GET ")) {
          authorizationCode = line;
          break;
        }
        if (line.trim().isEmpty())
          break;
      }
      client.getOutputStream()
          .write(("HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n\r\n"
              + "<h1>Login successful!!</h1> <br> <h2> You can close this window </h2>")
                  .getBytes());
      client.getOutputStream().flush();
      client.close();

      if (authorizationCode == null) {
        LOGGER.debug("GoogleOAuth2::getAuthorizationCode Failed to obtain authorization code.");
      }
      return authorizationCode;
    } catch (Exception e) {
      LOGGER.debug("GoogleOAuth2::getAuthorizationCode User not click");
    }
    return null;
  }

  private String extractAuthorizationCode(String url) {
    try {
      Pattern pattern = Pattern.compile("[?&]code=([^&]+)");
      Matcher matcher = pattern.matcher(url);
      if (matcher.find()) {
        return matcher.group(1);
      }
    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.debug("GoogleOAuth2::extractAuthorizationCode", e);
    }
    return null;
  }

  private String[] fetchUserInfo(String accessToken) throws Exception {
    HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory(
        request -> request.setParser(new JsonObjectParser(GsonFactory.getDefaultInstance())));

    String userInfoUrl = propertyHelper.getGoogleApiInfo();

    HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(userInfoUrl));
    request.getHeaders().setAuthorization("Bearer " + accessToken);
    HttpResponse response = request.execute();

    JSONObject userInfo = new JSONObject(response.parseAsString());
    String userId = "";
    String userName = userInfo.getString("name");
    String userEmail = "Not available";
    if (userInfo.has("email")) {
      userEmail = userInfo.getString("email");
    }
    LOGGER.info("GoogleOAuth2::fetchUserInfo Login with Google successfully!!");
    return new String[] {userId, userName, userEmail};
  }

}
