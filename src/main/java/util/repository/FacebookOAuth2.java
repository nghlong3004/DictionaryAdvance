package util.repository;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.json.JSONObject;
import util.PropertyHelper;

public class FacebookOAuth2 {

  private static final Logger LOGGER = Logger.getLogger(FacebookOAuth2.class);
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

  public FacebookOAuth2(PropertyHelper propertyHelper) {
    this.propertyHelper = propertyHelper;
  }

  public String[] infoUserWithFacebook() throws Exception {
    String authorizationUrl = "https://www.facebook.com/v17.0/dialog/oauth?" + "client_id="
        + propertyHelper.getFacebookAppID() + "&redirect_uri="
        + propertyHelper.getFacebookRedirect() + "&scope=email";
    if (Desktop.isDesktopSupported()) {
      Desktop.getDesktop().browse(new URI(authorizationUrl));
    } else {
      LOGGER.debug(
          "FacebookOAuth2::infoUserWithFacebook Desktop is not supported. Please open the URL manually.");
      return null;
    }

    String redirectedUrl = getAuthorizationCode();

    if (redirectedUrl == null) {
      return null;
    }

    LOGGER.debug("FacebookOAuth2::infoUserWithFacebook Get URL on web");
    String authorizationCode = extractAuthorizationCode(redirectedUrl);

    if (authorizationCode == null) {
      LOGGER.debug(
          "FacebookOAuth2::infoUserWithFacebook Failed to extract authorization code. Please check the URL and try again.");
      return null;
    }

    String tokenUrl = "https://graph.facebook.com/v17.0/oauth/access_token?" + "client_id="
        + propertyHelper.getFacebookAppID() + "&redirect_uri="
        + propertyHelper.getFacebookRedirect() + "&client_secret="
        + propertyHelper.getFacebookAppSecret() + "&code=" + authorizationCode;

    String accessToken = fetchAccessToken(tokenUrl);
    if (accessToken == null) {
      LOGGER.debug("FacebookOAuth2::infoUserWithFacebook Error: Could not fetch access token!.");
      return null;
    }

    return fetchUserInfo(accessToken);
  }

  private String extractAuthorizationCode(String url) {
    String[] parts = url.split("\\?");
    if (parts.length > 1) {
      String[] queryParams = parts[1].split("&");
      for (String param : queryParams) {
        if (param.startsWith("code=")) {
          param = param.substring(5);
          param = param.substring(0, param.indexOf(' '));
          return param;
        }
      }
    }
    return null;
  }

  private String getAuthorizationCode() {
    try (ServerSocket server = new ServerSocket(8080)) {
      server.setSoTimeout(1000 * 60 * 3);
      Socket client = server.accept();
      BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      String line = null;
      String authorizationCode = null;
      LOGGER.info("FacebookOAuth2::getAuthorizationCode Read data from browser");
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
        LOGGER.debug("FacebookOAuth2::getAuthorizationCode Failed to obtain authorization code.");
      }
      return authorizationCode;
    } catch (Exception e) {
      LOGGER.debug("FacebookOAuth2::getAuthorizationCode User not click");
    }
    return null;
  }

  private String fetchAccessToken(String tokenUrl) {
    try {
      HttpURLConnection connection =
          (HttpURLConnection) (new URI(tokenUrl)).toURL().openConnection();
      connection.setRequestMethod("GET");

      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      reader.close();

      JSONObject jsonResponse = new JSONObject(response.toString());
      return jsonResponse.getString("access_token");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private String[] fetchUserInfo(String accessToken) {
    String userInfoUrl =
        "https://graph.facebook.com/me?fields=id,name,email&access_token=" + accessToken;
    try {
      HttpURLConnection connection =
          (HttpURLConnection) (new URI(userInfoUrl)).toURL().openConnection();
      connection.setRequestMethod("GET");

      BufferedReader reader =
          new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      reader.close();

      JSONObject userInfo = new JSONObject(response.toString());
      String userId = userInfo.getString("id");
      String userName = userInfo.getString("name");
      String userEmail = "Not available";
      if (userInfo.has("email")) {
        userEmail = userInfo.getString("email");
      }
      return new String[] {userId, userName, userEmail};
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
