package model;

import java.util.TreeMap;

public class DatabaseRepository implements Repository<String>{
	
	@SuppressWarnings("unused")
	private String dbUrl;
	@SuppressWarnings("unused")
	private String dbUsername;
	@SuppressWarnings("unused")
	private String dbPassword;
	public DatabaseRepository(String dbUrl, String dbUsername, String dbPassword) {
		this.dbUrl = dbUrl;
		this.dbPassword = dbPassword;
		this.dbUsername = dbUsername;
	}
	
	@Override
	public void save(String data, Account account) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public TreeMap<String, String> load() {
		
		return null;
	}

	@Override
	public void delete() {
		
		
	}

	@Override
	public String loadUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(String data, String user) {
		// TODO Auto-generated method stub
		
	}

}
