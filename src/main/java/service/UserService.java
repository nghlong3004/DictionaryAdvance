package service;

import model.user.User;
import repository.UserRepository;
import repository.UserRepositoryFactory;
import util.BCryptUtil;
import util.MappingUtil;
import util.repository.Utils;

public class UserService implements UserServiceInterface {

  private UserRepository userRepository;
  private User user;

  public UserService() {
    MappingUtil.registerMapping(User.class, "users");
    UserRepositoryFactory repositoryFactory = new UserRepositoryFactory();
    userRepository = repositoryFactory.createUserRepository();
    user = new User();
  }

  @Override
  public void handleLoginSuccess(String username, boolean isRemember) {
    user = getUserByEmail(username);
    user.setUpdated(Utils.nowTime());
    userRepository.updateUser(user);
  }

  @Override
  public User getUser() {
    return this.user;
  }

  @Override
  public boolean login(User newUser) {
    User user = getUserByEmail(newUser.getEmail());
    if (user == null) {
      return false;
    }
    return checkPassword(user.getPassword(), newUser.getPassword());
  }

  @Override
  public void register(User user) {
    User data = user;
    data.setPassword(hashPassword(user.getPassword()));
    userRepository.saveUser(data);
  }

  @Override
  public User getUserByEmail(String username) {
    return userRepository.getUserByEmail(username);
  }

  public void remember(String username) {

  }

  public String hashPassword(String passwordToHash) {
    return BCryptUtil.hashPassword(passwordToHash, BCryptUtil.gensalt(12));
  }

  public boolean checkPassword(String hash, String attempt) {
    return BCryptUtil.checkPassword(attempt, hash);
  }



}
