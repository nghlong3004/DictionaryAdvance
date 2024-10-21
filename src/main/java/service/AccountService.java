package service;

import java.util.List;
import java.util.prefs.Preferences;
import model.account.User;
import repository.DataRepository;
import repository.DataRepositoryFactory;
import util.BCryptUtil;
import util.Constants;
import util.PropertyHelper;
import util.repository.Utils;
import java.security.SecureRandom;

public class AccountService implements AccountServiceInterface {

  private static AccountService instance;

  private DataRepository repository;
  private User user;

  public static synchronized AccountService getInstance(PropertyHelper dataSource) {
    if (instance == null) {
      instance = new AccountService(dataSource);
    }
    return instance;
  }

  private AccountService(PropertyHelper dataSource) {
    DataRepositoryFactory repositoryFactory = new DataRepositoryFactory(dataSource);
    repository = repositoryFactory.creatRepository();
    user = new User();
    appIsRemember();
  }

  private void appIsRemember() {
    Preferences pre = Preferences.userRoot().node("remember");

    String token = pre.get("token", null);
    // stringList[0] = attribute name
    // stringList[0] = attribute value
    List<String[]> stringList = repository
        .target(new String[] {"remember", "user_id", "(select user_id from users where username = '"
            + token.substring(0, token.indexOf(':')).trim() + "' )"});

    if (token.length() > 1 && stringList != null && stringList.size() > 1) {
      User checkUser = new User();
      Utils.setValues(checkUser, stringList.get(0), stringList.get(1));
      if (!token.equalsIgnoreCase(checkUser.getToken())) {
      } else {
        stringList = repository.target(new String[] {"users", "username",
            String.format("'%s'", token.substring(0, token.indexOf(':')).trim())});
        Utils.setValues(user, stringList.get(0), stringList.get(1));
        user.setToken(token);
      }
    }
  }

  // update if login successfully
  @Override
  public void handleLoginSuccess(String username, boolean isRemember) {
    repository.delete(new String[] {"remember", "''"}, new String[] {"''"});
    if (isRemember == true) {
      remember(username);
    } else {
      remember(null);
    }
  }
  // save

  @Override
  public User getUser() {
    return this.user;
  }

  @Override
  public boolean login(User newUser) {
    List<String[]> check = repository
        .target(new String[] {"users", "username", String.format("'%s'", newUser.getUsername())});
    if (check == null | check.size() < 2) {
      return false;
    }
    User checkUser = new User();
    Utils.setValues(checkUser, check.get(0), check.get(1));
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
    repository.save(Constants.KEY_USER, value(data));
  }

  @Override
  public boolean isUsernameAvailable(String username) {
    if (repository.target(new String[] {"users", "username", username}) == null) {
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
      repository.save(new String[] {"token", token}, new String[] {username, ""});
      pre.put("token", token);
    }

  }

  public String hashPassword(String passwordToHash) {
    return BCryptUtil.hashPassword(passwordToHash, BCryptUtil.gensalt(12));
  }

  public boolean checkPassword(String hash, String attempt) {
    Preferences pre = Preferences.userRoot().node("remember");
    String token = pre.get("token", null);
    if (user.getToken() != null && user.getToken().equals(token)) {
      if (hash.equals(attempt)) {
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
