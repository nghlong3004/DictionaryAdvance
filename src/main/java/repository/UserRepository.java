package repository;

import model.account.User;

public interface UserRepository {

  public User getUserByEmail(String email);

  public void saveUser(User user);

  public void updateUser(User user);
  
  public void deleteUser(User user);

}
