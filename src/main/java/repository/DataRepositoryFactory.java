package repository;

import model.DataSource;

public class DataRepositoryFactory<T> {
	private DataSource dataSource;
    private Class<T> type;

    public DataRepositoryFactory(DataSource dataSource, Class<T> type) {
        this.dataSource = dataSource;
        this.type = type;
    }

    public DataRepository<T> creatRepository() {
        String repositoryType = dataSource.getType();

        if (repositoryType.equalsIgnoreCase("database")) {
            return new DatabaseRepository<T>(
                dataSource.getDbUrl(),
                dataSource.getDbUsername(),
                dataSource.getDbPassword(),
                type
            );
        } else if (repositoryType.equalsIgnoreCase("file")) {
            return new FileRepository<T>(
                dataSource.getFilePathUser(),dataSource.getFilePathDictionary(),
                type
            );
        } else {
            throw new IllegalArgumentException("Unknown repository type: " + repositoryType);
        }
    }
}
