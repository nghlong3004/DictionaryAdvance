package repository.account;

import java.util.ArrayList;
import java.util.List;
import repository.DataRepository;
import repository.FileRepository;

public class AccountFileRepository extends FileRepository implements DataRepository {

  public AccountFileRepository(String fileName) {
    super(fileName);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void save(String[] keys, String[] values) {
    List<String[]> users = read();
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

  @Override
  public List<String[]> read() {
    return super.read();
  }

  @Override
  public List<String[]> target(String[] datas) {
    for (String[] user : read()) {
      for (String data : user) {
        if (data.contains("username")) {
          String[] username = data.split("/");
          if (username.length > 1) {
            if (username[1].trim().equalsIgnoreCase(datas[0])) {
              List<String[]> arr = new ArrayList<String[]>();
              arr.add(user);
              return arr;
            }
          }
        }
      }
    }
    return null;
  }


}
