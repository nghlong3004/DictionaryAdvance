package repository;

import configuration.DatabaseConfiguration;

public abstract class DatabaseRepository{
	
	private final DatabaseConfiguration databaseConfiguration;

    public DatabaseRepository(DatabaseConfiguration databaseConfiguration) {
      this.databaseConfiguration = databaseConfiguration;
    }

    public DatabaseConfiguration getDatabaseConfiguration() {
      return databaseConfiguration;
    }
    
    
    
}
