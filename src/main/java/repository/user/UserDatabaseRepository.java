package repository.user;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import configuration.DatabaseConfiguration;
import model.user.User;
import repository.DatabaseRepository;
import repository.UserRepository;

public class UserDatabaseRepository extends DatabaseRepository implements UserRepository {

  private final String tableName = "userinfo";

  public UserDatabaseRepository(DatabaseConfiguration databaseConfiguration) {
    super(databaseConfiguration);
  }

  @Override
  public User getUserByEmail(String email) {
    String query = String.format("SELECT * FROM %s WHERE email = '%s'", tableName, email);
    List<List<Object>> dataUsers = databaseExecute(query);
    if (dataUsers.size() < 2) {
      return null;
    }
    return mapperUser(dataUsers.get(0), dataUsers.get(1));
  }

  @Override
  public void saveUser(User user) {
    String query = String.format(
        "INSERT INTO %s (email, fullname, password, birthday, gender, created, updated) values ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
        tableName, user.getEmail(), user.getFullname(), user.getPassword(), user.getBirthday(),
        user.getGender(), user.getCreated(), user.getUpdated());
    databaseExecute(query);
  }

  @Override
  public void updateUser(User user) {
    String query = String.format(
        "UPDATE %s SET updated = '%s', gender = '%s', fullname = '%s', birthday = '%s', password = '%s' WHERE email = '%s'",
        tableName, user.getUpdated(), user.getGender(), user.getFullname(), user.getBirthday(),
        user.getPassword(), user.getEmail());
    databaseExecute(query);
  }

  @Override
  public void deleteUser(User user) {
    String query = String.format("DELETE FROM %s WHERE email = '%s'", tableName, user.getEmail());
    databaseExecute(query);
  }

  private User mapperUser(List<Object> column, List<Object> row) {
    User user = new User();
    for (int i = 0; i < column.size(); ++i) {
      String colName = (String) column.get(i);
      switch (colName) {
        case "email":
          user.setEmail((String) row.get(i));
          break;
        case "password":
          user.setPassword((String) row.get(i));
          break;
        case "birthday":
          user.setBirthday((LocalDate) row.get(i));
          break;
        case "created":
          user.setCreated(((Timestamp) row.get(i)).toLocalDateTime());
          break;
        case "updated":
          user.setUpdated(((Timestamp) row.get(i)).toLocalDateTime());
          break;
        case "fullname":
          user.setFullname((String) row.get(i));
          break;
        case "gender":
          user.setGender((short) ((int) row.get(i)));
          break;
      }
    }
    return user;
  }


}
