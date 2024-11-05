package controller;

import model.user.User;
import service.UserService;

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

  public boolean login(User user) {
    return userService.login(user);
  }

  public void handleLoginSuccess(String username, boolean remember) {
    userService.handleLoginSuccess(username, remember);
  }

  public void register(User user) {
    userService.register(user);
  }

  public User getUser() {
    return userService.getUser();
  }

}
