package util;

public class EnumContainer {

  public static AuthenticationState authenticationState = AuthenticationState.LOGIN;
  public static DataStorageType dataStorageType = DataStorageType.DATABASE;

  public enum AuthenticationState {
    LOGIN, LOGGED_IN
  }

  public enum DataStorageType {
    DATABASE, FILE
  }

  public enum ExternalApi {
    GOOGLE, FACEBOOK
  }

}
