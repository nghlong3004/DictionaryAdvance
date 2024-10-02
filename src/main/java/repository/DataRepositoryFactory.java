package repository;

import configuration.DatabaseConfiguration;
import repository.account.AccountDatabaseRepository;
import repository.account.AccountFileRepository;
import repository.dictionary.DictionaryDatabaseRepository;
import repository.dictionary.DictionaryFileRepository;
import util.PropertyHelper;

public class DataRepositoryFactory {
	private PropertyHelper dataSource;
	private final String tag;
    public DataRepositoryFactory(PropertyHelper dataSource, String tag) {
        this.dataSource = dataSource;
        this.tag = tag;
    }

    public DataRepository creatRepository() {
        String repositoryType = dataSource.getType();

        if (repositoryType.equalsIgnoreCase("database")) {
            if(tag.equalsIgnoreCase("Dictionary")) {
              return new DictionaryDatabaseRepository(new DatabaseConfiguration(
                  dataSource.getDbUrl(),
                  dataSource.getDbUsername(),
                  dataSource.getDbPassword()
              ));
            }
            else {
              return new AccountDatabaseRepository(new DatabaseConfiguration(
                  dataSource.getDbUrl(),
                  dataSource.getDbUsername(),
                  dataSource.getDbPassword()
              ));
            }
        } else if (repositoryType.equalsIgnoreCase("file")) {
            if(tag.equalsIgnoreCase("Dictionary")) {
              return new DictionaryFileRepository(
                  dataSource.getFilePathDictionary()
                  );
            }
            else {
              return new AccountFileRepository(
                  dataSource.getFilePathUser()
                  );
            }
        } else {
            throw new IllegalArgumentException("Unknown repository type: " + repositoryType);
        }
    }
}
