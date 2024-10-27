package service;

import model.user.User;

public interface UserServiceInterface {

  public boolean login(User user);

  public User getUserByEmail(String email);

  public void handleLoginSuccess(String username, boolean remember);

  public void register(User user);

  public User getUser();

}
