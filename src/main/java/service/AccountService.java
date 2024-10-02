package service;

import static util.repository.Utils.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.DataSource;
import model.account.User;
import repository.DataRepository;
import repository.DataRepositoryFactory;

public class AccountService implements AccountServiceInterface {

  private static AccountService instance;

  private DataRepository repository;
  private User user;
  private List<User> accounts;
  private Gson gson = new GsonBuilder().setPrettyPrinting().create();

  public static synchronized AccountService getInstance(DataSource dataSource) {
    if (instance == null) {
      instance = new AccountService(dataSource);
    }
    return instance;
  }

  private AccountService(DataSource dataSource) {
    DataRepositoryFactory repositoryFactory = new DataRepositoryFactory(dataSource, "Account");
    repository = repositoryFactory.creatRepository();
    accounts = new ArrayList<User>();
    Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
    accounts = gson.fromJson(repository.read(), userListType);
    for (User user : accounts) {
      if (user.isRemember()) {
        this.user = user;
        break;
      }
    }
    if (user == null) {
      user = new User();
    }

  }

  // update if login successfully
  @Override
  public void handleLoginSuccess(String username, boolean remember) {
    for (int i = 0; i < accounts.size(); ++i) {
      User curUser = accounts.get(i);
      if (username.equalsIgnoreCase(curUser.getUsername())) {
        curUser.setLastLogin(nowTimeinString());
        curUser.setRemember(remember);
        accounts.set(i, curUser);
      } else {
        if (curUser.isRemember()) {
          curUser.setRemember(false);
          accounts.set(i, curUser);
        }
      }
    }
    saveAccounts();
  }
  // save

  public void saveAccounts() {
    repository.save(gson.toJson(accounts));
  }


  @Override
  public User getUser() {
    return this.user;
  }

  @Override
  public boolean isUsernameAvailable(String username) {
    for (User user : accounts) {
      if (user.getUsername().equalsIgnoreCase(username)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean login(User newUser) {
    for (User user : accounts) {
      if (user.getUsername().equalsIgnoreCase(newUser.getUsername())) {
        return user.getPassword().equals(newUser.getPassword());
      }
    }
    return false;
  }

  @Override
  public void register(User user) {
    accounts.add(user);
    saveAccounts();

  }


}
