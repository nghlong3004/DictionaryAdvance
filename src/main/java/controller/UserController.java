package controller;

import model.user.User;
import service.UserServiceInterface;

public class UserController {

  // controller ---call--> service
  private final UserServiceInterface userService;

  public UserController(UserServiceInterface userService) {
    this.userService = userService;
  }

  public User getUserByEmail(String username) {
    return userService.getUserByEmail(username);
  }

  // login
  public boolean login(User user) {
    return userService.login(user);
  }

  // successfully
  public void handleLoginSuccess(String username, boolean remember) {
    userService.handleLoginSuccess(username, remember);
  }

  // register
  public void register(User user) {
    userService.register(user);
  }

  // get user
  public User getUser() {
    return userService.getUser();
  }

}
