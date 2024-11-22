package service;

import java.util.Random;
import model.user.User;

import repository.UserRepository;
import repository.UserRepositoryFactory;

import util.BCryptUtil;
import util.EmailUtil;
import util.EnumContainer.ExternalApi;
import util.ObjectContainer;
import util.repository.FacebookOAuth2;
import util.repository.GoogleOAuth2;
import util.repository.Utils;

public class UserService {

  private UserRepository userRepository;
  private GoogleOAuth2 googleOAuth2;
  private FacebookOAuth2 facebookOAuth2;
  private User user;
  private int OTP;

  public UserService() {
    UserRepositoryFactory repositoryFactory = new UserRepositoryFactory();
    userRepository = repositoryFactory.createUserRepository();
    user = new User();
    OTP = -1;
  }

  public void handleLoginSuccess(String username, boolean isRemember) {
    user = getUserByEmail(username);
    user.setUpdated(Utils.nowTime());
    updateUser(user);
  }

  public User getUser() {
    return this.user;
  }

  public void updateUser(User user) {
    userRepository.updateUser(user);
  }

  public void updatePasswordByUser(User user) {
    user.setPassword(hashPassword(user.getPassword()));
    updateUser(user);
  }

  public void deleteUser(User user) {
    userRepository.deleteUser(user);
  }

  public boolean login(User newUser) {
    User user = getUserByEmail(newUser.getEmail());
    if (user == null) {
      return false;
    }
    return checkPassword(user.getPassword(), newUser.getPassword());
  }

  public void register(User user) {
    User data = user;
    data.setPassword(hashPassword(user.getPassword()));
    userRepository.saveUser(data);
  }

  public User getUserByEmail(String username) {
    return userRepository.getUserByEmail(username);
  }

  public void remember(String username) {

  }

  public boolean isOTP(String OTP) {
    return OTP.equals(String.valueOf(this.OTP));
  }

  public void sendOTPtoEmail(String email) {
    OTP = generatorOTP();
    EmailUtil.sendEmail(email, OTP);
    OTP = -1;
  }

  public String[] loginWithCallApi(ExternalApi api) {
    if (googleOAuth2 == null) {
      googleOAuth2 = new GoogleOAuth2(ObjectContainer.getPropertyHelper());
    }
    if (facebookOAuth2 == null) {
      facebookOAuth2 = new FacebookOAuth2(ObjectContainer.getPropertyHelper());
    }
    switch (api) {
      case GOOGLE:
        try {
          return googleOAuth2.infoUserWithGoogle();
        } catch (Exception e) {
          e.printStackTrace();
        }
      case FACEBOOK:
        try {
          return facebookOAuth2.infoUserWithFacebook();
        } catch (Exception e) {
          e.printStackTrace();
        }
      default:
        return null;
    }

  }

  private String hashPassword(String passwordToHash) {
    return BCryptUtil.hashPassword(passwordToHash, BCryptUtil.gensalt(12));
  }

  private boolean checkPassword(String hash, String attempt) {
    return BCryptUtil.checkPassword(attempt, hash);
  }

  private int generatorOTP() {
    Random random = new Random();
    return random.nextInt(100000, 999999);
  }



}
