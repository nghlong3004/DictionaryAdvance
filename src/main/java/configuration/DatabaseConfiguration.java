package configuration;

public class DatabaseConfiguration {
  private final String dbUrl;
  private final String dbUsername;
  private final String dbPassword;

  public DatabaseConfiguration(String dbUrl, String dbUsername, String dbPassword) {
    this.dbUrl = dbUrl;
    this.dbUsername = dbUsername;
    this.dbPassword = dbPassword;
  }

  public String getDbUrl() {
    return dbUrl;
  }

  public String getDbUsername() {
    return dbUsername;
  }

  public String getDbPassword() {
    return dbPassword;
  }



}
