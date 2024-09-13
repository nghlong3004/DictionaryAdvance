package model;

public class RepositoryFactory {
	private DataSource dataSource;
	public RepositoryFactory(DataSource dataSource) {
		this.dataSource =  dataSource;
	}
	public Repository<String> creatRepository(){
		String repositoryType = dataSource.getType();
        if (repositoryType.equalsIgnoreCase("database")) {
            return new DatabaseRepository(
                dataSource.getDbUrl(),
                dataSource.getDbUsername(),
                dataSource.getDbPassword()
            );
        } else if (repositoryType.equalsIgnoreCase("file")) {
            return new FileRepository(dataSource.getFilePath());
        } else {
            throw new IllegalArgumentException("Unknown repository type: " + repositoryType);
        }
	}

}
