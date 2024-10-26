package repository.account;

import configuration.FileConfiguration;
import model.account.User;
import repository.FileRepository;
import repository.UserRepository;

public class AccountFileRepository extends FileRepository implements UserRepository {

  public AccountFileRepository(FileConfiguration fileConfiguration) {
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
