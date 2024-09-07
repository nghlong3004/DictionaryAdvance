package model;

public class User {
	
	private String username;
	private String password;
	public String getName() {
		return username;
	}
	public void name(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void password(String password) {
		this.password = password;
	}
	
}
