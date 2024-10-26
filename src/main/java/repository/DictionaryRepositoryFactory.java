package repository;

import repository.dictionary.DictionaryDatabaseRepository;
import repository.dictionary.DictionaryFileRepository;
import util.EnumContainer;
import util.ObjectContainer;

public class DictionaryRepositoryFactory {
  public DictionaryRepositoryFactory() {
    
  }
  
  public DictionaryRepository createDictionaryRepository() {
    return switch(EnumContainer.dataStorageType) {
      case FILE -> new DictionaryFileRepository(ObjectContainer.getFileConfiguration());
      case DATABASE -> new DictionaryDatabaseRepository(ObjectContainer.getDatabaseConfiguration());
      default -> null;
    };
  }
  
}
