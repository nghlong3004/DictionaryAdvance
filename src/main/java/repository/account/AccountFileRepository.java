package repository.account;

import com.google.gson.JsonElement;
import repository.DataRepository;
import repository.FileRepository;

public class AccountFileRepository extends FileRepository implements DataRepository {

  public AccountFileRepository(String fileName) {
    super(fileName);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void save(String json) {
    // TODO Auto-generated method stub

  }

  @Override
  public void update() {
    // TODO Auto-generated method stub

  }

  @Override
  public JsonElement read() {
    // TODO Auto-generated method stub
    return super.load();
  }

}
