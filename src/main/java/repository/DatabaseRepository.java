package repository;

import java.util.Map;
import configuration.DatabaseConfiguration;
import util.DatabaseUtil;
import util.repository.Utils;

public abstract class DatabaseRepository {

  private final DatabaseConfiguration databaseConfiguration;

  public DatabaseRepository(DatabaseConfiguration databaseConfiguration) {
    this.databaseConfiguration = databaseConfiguration;
  }

  public DatabaseConfiguration getDatabaseConfiguration() {
    return databaseConfiguration;
  }

  public void updateData(String table, String key, String value) {
    String query = String.format("UPDATE %s SET %s WHERE %s", table, key, value);
    DatabaseUtil.update(databaseConfiguration, query);
  }

  public void saveData(String tableName, String key, String value) {
    String query = String.format("INSERT INTO %s(%s) VALUES(%s)", tableName, key, value);
    DatabaseUtil.save(getDatabaseConfiguration(), query);
  }

  public void saveToken(String tableName, String token, String username) {
    String query = String.format(
        "INSERT INTO %s(user_id, token, expires) SELECT user_id, '%s', '%s' FROM users WHERE users.username = '%s'",
        tableName, token, Utils.timeNextDay(3), username);

    DatabaseUtil.save(getDatabaseConfiguration(), query);
  }

  public void deleteData(String tableName, String key, String value) {
    String query = String.format("DELETE FROM %s WHERE %s = %s", tableName, key, value);
    DatabaseUtil.delete(databaseConfiguration, query);
  }

  public Map<String, String> targetData(String tableName, String key, String value) {
    String query = String.format("SELECT * FROM %s WHERE %s = %s", tableName, key, value);
    return DatabaseUtil.target(databaseConfiguration, query);

  }



}
