package service;

import model.account.User;

public interface AccountServiceInterface {

  public boolean login(User user);
  
  public User getUserByEmail(String email);

  public void handleLoginSuccess(String username, boolean remember);

  public void register(User user);

  public User getUser();

}
