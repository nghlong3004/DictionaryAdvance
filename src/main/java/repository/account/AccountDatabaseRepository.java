package repository.account;

import java.util.List;
import java.util.Map;
import configuration.DatabaseConfiguration;
import model.account.User;
import repository.DataRepository;
import repository.DatabaseRepository;
import util.repository.Utils;

public class AccountDatabaseRepository extends DatabaseRepository implements DataRepository<User> {

  public AccountDatabaseRepository(DatabaseConfiguration databaseConfiguration) {
    super(databaseConfiguration);
    // TODO Auto-generated constructor stub
  }



  @Override
  public void update(String[] key, String[] value) {
    
//    String tableName = "dictionary.users";
//
//    String[] values = {"'" + Utils.nowTimeinString() + "'", "'" + Utils.nowTimeinString() + "'",
//        "'1'", "'" + user.getFullName() + "'", "'" + user.getEmail() + "'",
//        user.getBirthdate() == null ? "NULL" : "'" + user.getBirthdate() + "'",
//        "'" + user.getUsername() + "'", "'" + user.getPassword() + "'"};
//
//
//    String columnsString = String.join(", ", columnUser);
//    String valuesString = String.join(", ", values);
//
//    String query =
//        String.format("INSERT INTO %s(%s) VALUES(%s)", tableName, columnsString, valuesString);
//    DatabaseUtil.update(getDatabaseConfiguration(), query);
  }



  @Override
  public void save(String[] keys, String[] values) {
    User data = new User();
    Utils.setValues(data, keys, values);

    String columnsString = String.join(", ", keys);
    String valuesString = String.join(", ", values);
    if(keys[0].equals("token")) {
      super.saveToken("remember", keys[1], values[0]);
    }
    else {
      super.saveData("users", columnsString, valuesString);
    }

  }



  @Override
  public void delete(String[] key, String[] value) {
    // TODO Auto-generated method stub

  }



  @Override
  public List<User> read() {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public User target(String[] datas) {
    return getUser(super.targetData(datas[0], datas[1], datas[2]));
  }

  private User getUser(Map<String, String> mp) {
    User user = new User();
    if (mp == null || mp.isEmpty()) {
      return null;
    }
    user.setUsername(mp.get("username"));
    user.setPassword(mp.get("password"));
    user.setFullName(mp.get("fullname"));
    user.setGender(mp.get("gender"));
    user.setBirthdate(mp.get("birthday"));
    user.setEmail(mp.get("email"));
    user.setRegistrationDate(mp.get("created"));
    user.setLastLogin(mp.get("update"));
    user.setToken(mp.get("token"));
    return user;
  }

}
