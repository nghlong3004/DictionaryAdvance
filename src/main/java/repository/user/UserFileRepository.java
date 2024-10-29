package repository.user;

import configuration.FileConfiguration;
import model.user.User;
import repository.FileRepository;
import repository.UserRepository;

public class UserFileRepository extends FileRepository implements UserRepository {

  public UserFileRepository(FileConfiguration fileConfiguration) {
    super(fileConfiguration);
  }

  @Override
  public User getUserByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void saveUser(User user) {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateUser(User user) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteUser(User user) {
    // TODO Auto-generated method stub

  }



}
