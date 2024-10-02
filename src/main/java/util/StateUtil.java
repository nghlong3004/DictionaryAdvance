package util;

public class StateUtil {
  private boolean isLogin = true;
  private boolean isInApp = false;

  public boolean isLogin() {
    return isLogin;
  }

  public void setLogin(boolean isLogin) {
    this.isLogin = isLogin;
  }

  public boolean isInApp() {
    return isInApp;
  }

  public void setInApp(boolean isInApp) {
    this.isInApp = isInApp;
  }



}
