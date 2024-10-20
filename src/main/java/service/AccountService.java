package service;

import java.util.prefs.Preferences;
import model.account.User;
import repository.DataRepository;
import repository.DataRepositoryFactory;
import util.BCryptUtil;
import util.PropertyHelper;
import java.security.SecureRandom;

public class AccountService implements AccountServiceInterface {

  private static AccountService instance;

  public final String[] keys =
      {"updated", "created", "gender", "fullname", "email", "birthday", "username", "password"};

  private DataRepository<User> repository;
  private User user;

  public static synchronized AccountService getInstance(PropertyHelper dataSource) {
    if (instance == null) {
      instance = new AccountService(dataSource);
    }
    return instance;
  }

  @SuppressWarnings("unchecked")
  private AccountService(PropertyHelper dataSource) {
    DataRepositoryFactory repositoryFactory = new DataRepositoryFactory(dataSource);
    repository = (DataRepository<User>) repositoryFactory.creatRepository();
    user = new User();
    appIsRemember();
  }

  private void appIsRemember() {
    Preferences pre = Preferences.userRoot().node("remember");

    String token = pre.get("token", null);
    User checkUser = repository
        .target(new String[] {"(select user_id from dictionary.users where username = '"
            + token.substring(0, token.indexOf(':')) + "' )", "remember", "user_id"});
    if (token.length() > 1 && checkUser != null) {
      if (!token.equalsIgnoreCase(checkUser.getToken())) {
      } else {
        user = repository
            .target(new String[] {"'" + token.substring(0, token.indexOf(':')) + "'", "users", "username"});
        user.setToken(token);
      }
    }
  }

  // update if login successfully
  @Override
  public void handleLoginSuccess(String username, boolean isRemember) {
    if (isRemember == true) {
      remember(username);
    } else {
      remember("");
    }
  }
  // save

  @Override
  public User getUser() {
    return this.user;
  }

  @Override
  public boolean login(User newUser) {
    User checkUser =
        (User) repository.target(new String[] {"'" + newUser.getUsername() + "'", "users", "username"});
    if (checkUser == null) {
      return false;
    }
    if (checkPassword(checkUser.getPassword(), newUser.getPassword())) {
      this.user = checkUser;
      this.user.setPassword(newUser.getPassword());
      return true;
    }
    return false;
  }

  @Override
  public void register(User user) {
    User data = user;
    data.setPassword(hashPassword(user.getPassword()));
    repository.save(keys, value(data));
  }

  @Override
  public boolean isUsernameAvailable(String username) {
    if ((User) repository.target(new String[] {"'" + username + "'", "users", "username"}) == null) {
      return true;
    }
    return false;
  }

  public void remember(String username) {
    Preferences pre = Preferences.userRoot().node("remember");
    if (username == null) {
      System.err.println("no username");
    } else {
      String token = generateToken(username);
      repository.save(new String[] {"token", token }, new String[] {"'" + username + "'", ""});
      pre.put("token", token);
    }

  }

  public String hashPassword(String passwordToHash) {
    return BCryptUtil.hashPassword(passwordToHash, BCryptUtil.gensalt(12));
  }

  public boolean checkPassword(String hash, String attempt) {
    Preferences pre = Preferences.userRoot().node("remember");
    String token = pre.get("token", null);
    if(user.getToken() != null && user.getToken().equals(token)) {
      if(hash.equals(attempt)) {
        return true;
      }
    }
    return BCryptUtil.checkPassword(attempt, hash);
  }

  public String generateToken(String username) {
    long longToken = Math.abs(new SecureRandom().nextLong());
    String random = Long.toString(longToken, 16);
    return (username + ":" + random);
  }

  public String[] value(User data) {
    char gender = '0';
    switch (data.getGender()) {
      case "Male":
        gender = '1';
        break;
      case "Female":
        gender = '2';
        break;
    }
    String[] values = {"'" + data.getLastLogin() + "'", "'" + data.getRegistrationDate() + "'",
        "'" + gender + "'", "'" + data.getFullName() + "'", "'" + data.getEmail() + "'",
        data.getBirthdate() == null ? "NULL" : "'" + data.getBirthdate() + "'",
        "'" + data.getUsername() + "'", "'" + data.getPassword() + "'"};
    return values;
  }


}
