package controller;

import model.user.User;
import service.UserService;
import util.EnumContainer.ExternalApi;

public class UserController {

  // controller ---call--> service
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  public User getUserByEmail(String username) {
    return userService.getUserByEmail(username);
  }

  public void updateUser(User user) {
    userService.updateUser(user);
  }

  public void deleteUser(User user) {
    userService.deleteUser(user);
  }

  public void updatePasswordByUser(User user) {
    userService.updatePasswordByUser(user);
  }

  public boolean login(User user) {
    return userService.login(user);
  }

  public void handleLoginSuccess(String username, boolean remember) {
    userService.handleLoginSuccess(username, remember);
  }

  public void register(User user) {
    userService.register(user);
  }

  public void sendOTPtoEmail(String email) {
    userService.sendOTPtoEmail(email);
  }

  public boolean isOTP(String OTP) {
    return userService.isOTP(OTP);
  }

  public User getUser() {
    return userService.getUser();
  }

  public String[] loginWithCallApi(ExternalApi api) {
    return userService.loginWithCallApi(api);
  }

}
