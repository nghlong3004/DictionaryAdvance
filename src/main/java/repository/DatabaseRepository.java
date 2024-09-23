package repository;

import java.util.List;

public class DatabaseRepository<T> implements Repository<T>{
	
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
	@Override
	public List<T> loadUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void saveUsers(List<T> users) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
