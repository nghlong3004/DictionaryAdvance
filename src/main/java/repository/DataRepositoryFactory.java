package repository;

import model.DataSource;

public class RepositoryFactory<T> {
    private DataSource dataSource;
    private Class<T> type;

    public RepositoryFactory(DataSource dataSource, Class<T> type) {
        this.dataSource = dataSource;
        this.type = type;
    }

    public Repository<T> creatRepository() {
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
                dataSource.getFilePath(),
                type
            );
        } else {
            throw new IllegalArgumentException("Unknown repository type: " + repositoryType);
        }
    }
}
