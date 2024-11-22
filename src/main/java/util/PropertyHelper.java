package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import util.EnumContainer.DataStorageType;

public class PropertyHelper {
  private Properties properties;

  public PropertyHelper() {
    properties = new Properties();
    try (InputStream input = getClass().getResourceAsStream("/config/config.properties")) {
      properties.load(input);
      switch (getType()) {
        case "file":
          EnumContainer.dataStorageType = DataStorageType.FILE;
          break;
        case "database":
          EnumContainer.dataStorageType = DataStorageType.DATABASE;
          break;
        default:
          break;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Properties getProperties() {
    return properties;
  }

  // mail
  public String getMailUsername() {
    return getPropertyValue("mail.username");
  }

  public String getMailPassword() {
    return getPropertyValue("mail.password");
  }

  // facebook
  public String getFacebookAppID() {
    return getPropertyValue("facebook.app_id");
  }

  public String getFacebookAppSecret() {
    return getPropertyValue("facebook.app_secret");
  }

  public String getFacebookRedirect() {
    return getPropertyValue("facebook.redirect_uri");
  }

  // google
  public String getGoogleClientID() {
    return getPropertyValue("google.client_id");
  }

  public String getGoogleClientSecret() {
    return getPropertyValue("google.client_secret");
  }

  public String getGoogleRedirectUri() {
    return getPropertyValue("google.redirect_uri");
  }

  public String getGoogleScope() {
    return getPropertyValue("google.scope");
  }

  public String getGoogleApiInfo() {
    return getPropertyValue("google.api_info");
  }

  // type
  public String getType() {
    return getPropertyValue("data.source");
  }

  // database
  public String getDbUrl() {
    return getPropertyValue("db.url");
  }

  public String getDbUsername() {
    return getPropertyValue("db.username");
  }

  public String getDbPassword() {
    return getPropertyValue("db.password");
  }

  // file
  public String getFilePathUser() {
    return getPropertyValue("file.pathUser");
  }

  public String getFilePathDictionary() {
    return getPropertyValue("file.pathDictionary");
  }

  public String getImagePath() {
    return getPropertyValue("image.path");
  }

  private String getPropertyValue(String property) {
    return properties.getProperty(property);
  }

}
