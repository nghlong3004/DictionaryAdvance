package repository.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import configuration.DatabaseConfiguration;
import model.user.User;
import repository.DatabaseRepository;
import repository.UserRepository;
import util.RowInsert;

public class UserDatabaseRepository extends DatabaseRepository implements UserRepository {

  public UserDatabaseRepository(DatabaseConfiguration databaseConfiguration) {
    super(databaseConfiguration);
  }

  @Override
  public User getUserByEmail(String email) {
    String query = "SELECT * FROM users WHERE email = ?";
    List<Object> parmas = Collections.singletonList(email);
    List<Object> users = databaseExecute(query, parmas, User.class);
    if (users != null && !users.isEmpty()) {
      if (users.get(0) instanceof User) {
        return (User) users.get(0);
      }
    }
    return null;
  }

  @Override
  public void saveUser(User user) {
    try {
      String query = RowInsert.generateInsertSQL(new User());
      List<Object> params = user.getAttributesAsList();
      databaseExecute(query, params, User.class);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateUser(User user) {
    String query =
        "UPDATE users SET updated = ?, gender = ?, fullname = ?, birthday = ?, password = ? WHERE email = ?";
    List<Object> params = new ArrayList<>();
    params.add(user.getUpdated());
    params.add(user.getGender());
    params.add(user.getFullname());
    params.add(user.getBirthday());
    params.add(user.getPassword());
    params.add(user.getEmail());
    databaseExecute(query, params, User.class);
  }

  @Override
  public void deleteUser(User user) {
    String query = "DELETE FROM users WHERE email = ?";
    List<Object> params = Collections.singletonList(user.getEmail());
    databaseExecute(query, params, User.class);
  }



}
