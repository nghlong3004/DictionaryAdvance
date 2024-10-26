package repository;

import repository.user.UserDatabaseRepository;
import repository.user.UserFileRepository;
import util.EnumContainer;
import util.ObjectContainer;

public class UserRepositoryFactory {
  public UserRepositoryFactory() {

  }

  public UserRepository createUserRepository() {
    return switch (EnumContainer.dataStorageType) {
      case FILE -> new UserFileRepository(ObjectContainer.getFileConfiguration());
      case DATABASE -> new UserDatabaseRepository(ObjectContainer.getDatabaseConfiguration());
      default -> null;
    };
  }

}
