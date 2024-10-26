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

  public String getType() {
    return getPropertyValue("data.source");
  }

  public String getDbUrl() {
    return getPropertyValue("db.url");
  }

  public String getDbUsername() {
    return getPropertyValue("db.username");
  }

  public String getDbPassword() {
    return getPropertyValue("db.password");
  }

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
