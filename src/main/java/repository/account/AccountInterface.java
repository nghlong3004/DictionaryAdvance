package repository.account;

import model.account.User;

public interface AccountInterface {

  boolean isUser(User newUser);

  void handleLoginSuccess(String username, boolean remember);

  void addUser(User user);

  void saveAccounts();

  User getUser();

  boolean isUsernameAvailable(String username);

}
