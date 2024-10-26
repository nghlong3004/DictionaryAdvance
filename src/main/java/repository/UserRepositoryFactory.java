package repository;

import repository.account.AccountDatabaseRepository;
import repository.account.AccountFileRepository;
import util.EnumContainer;
import util.ObjectContainer;

public class UserRepositoryFactory {
  public UserRepositoryFactory() {
    
  }
  
  public UserRepository createUserRepository() {
    return switch (EnumContainer.dataStorageType) {
      case FILE -> new AccountFileRepository(ObjectContainer.getFileConfiguration());
      case DATABASE -> new AccountDatabaseRepository(ObjectContainer.getDatabaseConfiguration());
      default -> null;
    };
  }

}
