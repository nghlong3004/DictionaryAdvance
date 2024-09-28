package repository;

import java.util.List;

public class DatabaseRepository<T> implements DataRepository<T>{
	
	@SuppressWarnings("unused")
	private String dbUrl;
	@SuppressWarnings("unused")
	private String dbUsername;
	@SuppressWarnings("unused")
	private String dbPassword;
	@SuppressWarnings("unused")
	private Class<T> type;

    public DatabaseRepository(String dbUrl, String dbUsername, String dbPassword, Class<T> type) {
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.type = type;
    }
	@SuppressWarnings("hiding")
	@Override
	public <T, I> I load(Class<I> returnType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(List<T> users) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
