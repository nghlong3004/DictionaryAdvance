package repository.account;

import com.google.gson.JsonElement;
import configuration.DatabaseConfiguration;
import repository.DataRepository;
import repository.DatabaseRepository;

public class AccountDatabaseRepository extends DatabaseRepository implements DataRepository {

  public AccountDatabaseRepository(DatabaseConfiguration databaseConfiguration) {
    super(databaseConfiguration);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void save(String json) {
    // TODO Auto-generated method stub

  }

  @Override
  public void delete() {
    // TODO Auto-generated method stub

  }

  @Override
  public void update() {
    // TODO Auto-generated method stub

  }

  @Override
  public JsonElement read() {
    // TODO Auto-generated method stub
    return null;
  }

}
