package repository;

import configuration.DatabaseConfiguration;
import repository.account.AccountDatabaseRepository;
import repository.account.AccountFileRepository;
import repository.dictionary.DictionaryDatabaseRepository;
import repository.dictionary.DictionaryFileRepository;
import util.PropertyHelper;
import util.state.AppState;
import util.state.StorageType;

public class DataRepositoryFactory {
  private PropertyHelper dataSource;

  public DataRepositoryFactory(PropertyHelper dataSource) {
    this.dataSource = dataSource;
  }

  public DataRepository creatRepository() {
    switch (StorageType.state) {
      case FILE:
        switch (AppState.state) {
          case LOGIN:
            return new AccountFileRepository(dataSource.getFilePathUser());
          case IN_APP:
            return new DictionaryFileRepository(dataSource.getFilePathDictionary());
          default:
            throw new IllegalArgumentException("Unexpected value: " + AppState.state);
        }
      case DATABASE:
        switch (AppState.state) {
          case LOGIN:
            return new AccountDatabaseRepository(new DatabaseConfiguration(dataSource.getDbUrl(),
                dataSource.getDbUsername(), dataSource.getDbPassword()));
          case IN_APP:
            return new DictionaryDatabaseRepository(new DatabaseConfiguration(dataSource.getDbUrl(),
                dataSource.getDbUsername(), dataSource.getDbPassword()));

          default:
            throw new IllegalArgumentException("Unexpected value: " + AppState.state);
        }
      default:
        throw new IllegalArgumentException("Unknown repository type: " + StorageType.state);
    }
  }
}
