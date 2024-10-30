package repository;

import java.util.List;
import configuration.DatabaseConfiguration;
import util.DatabaseExecutor;

public abstract class DatabaseRepository {

  private DatabaseExecutor databaseExecutor;

  public DatabaseRepository(DatabaseConfiguration databaseConfiguration) {
    databaseExecutor = new DatabaseExecutor(databaseConfiguration);
  }

  public List<List<Object>> databaseExecute(String query) {
    return databaseExecutor.execute(query);
  }

}
