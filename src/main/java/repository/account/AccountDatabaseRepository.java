package repository.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import configuration.DatabaseConfiguration;
import model.account.User;
import repository.DataRepository;
import repository.DatabaseRepository;
import util.Constants;
import util.repository.Utils;

public class AccountDatabaseRepository extends DatabaseRepository implements DataRepository {

  public AccountDatabaseRepository(DatabaseConfiguration databaseConfiguration) {
    super(databaseConfiguration);
    // TODO Auto-generated constructor stub
  }



  @Override
  public void update(String[] key, String[] value) {


  }



  @Override
  public void save(String[] keys, String[] values) {
    if (keys != null && values != null && keys.length > 1) {
      User data = new User();
      Utils.setValues(data, keys, values);

      String columnsString = String.join(", ", keys);
      String valuesString = String.join(", ", values);
      if (keys[0].equals("token")) {
        super.saveToken("remember", keys[1], values[0]);
      } else {
        super.saveData("users", columnsString, valuesString);
      }
    }

  }



  @Override
  public void delete(String[] keys, String[] values) {
    if (keys != null && values != null && keys.length > 1)
      super.deleteData(keys[0], keys[1], values[0]);

  }



  @Override
  public List<String[]> read() {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public List<String[]> target(String[] datas) {
    return getUser(super.targetData(datas[0], datas[1], datas[2]));
  }

  private List<String[]> getUser(Map<String, String> mp) {
    if (mp == null || mp.isEmpty()) {
      return null;
    }
    List<String[]> users = new ArrayList<String[]>();
    String[] values = new String[Constants.KEY_USER.length];
    for (int i = 0; i < Constants.KEY_USER.length; ++i) {
      values[i] = mp.get(Constants.KEY_USER[i]);
    }
    users.add(Constants.KEY_USER);
    users.add(values);
    return users;
  }

}
