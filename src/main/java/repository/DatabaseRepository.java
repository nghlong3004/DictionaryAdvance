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

  public void saveData(String tableName, String columnsString, String valuesString) {
    String query = String.format("INSERT INTO %s(%s) VALUES(%s)", tableName,
        columnsString, valuesString);
    DatabaseUtil.update(getDatabaseConfiguration(), query);
  }
  
  public void saveToken(String tableName, String token, String username) {
    String query = "insert into " + tableName + "(user_id, token, expires) "
        + "select user_id, '"  + token + "' , '" + Utils.timeNextDay(3) + "' from users where users.username = " + username;
    System.out.println(query);
    DatabaseUtil.update(getDatabaseConfiguration(), query);
  }

  public Map<String, String> targetData(String data, String nameTable, String nameWhere) {
    String query =
        "select * from " + nameTable + " where " + nameWhere + " = " + data;
    System.out.println(query);
    return DatabaseUtil.target(databaseConfiguration, query);

  }



}
