package repository.account;

import java.util.List;
import model.account.User;
import repository.DataRepository;
import repository.FileRepository;
import util.repository.Utils;

public class AccountFileRepository extends FileRepository implements DataRepository<User> {

  public AccountFileRepository(String fileName) {
    super(fileName);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void save(String[] key, String[] value) {
    List<User> users = read();
    User user = new User();
    Utils.setValues(user, key, value);
    users.add(user);
    super.save(users);
    
  }

  @Override
  public void delete(String[] key, String[] value) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void update(String[] key, String[] value) {
    // TODO Auto-generated method stub
    
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<User> read() {
    return (List<User>) super.read();
  }

  @Override
  public User target(String[] datas) {
    for(User user: read()) {
      if(datas[0].equalsIgnoreCase(user.getUsername())) {
        return user;
      }
    }
    return null;
  }


}
