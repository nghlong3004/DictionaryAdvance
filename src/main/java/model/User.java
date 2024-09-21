package model;

public class User {
	
	private String username;
	private String password;
	private String fullname;
	private String gender;
	public User() {
		username = "";
		password = "";
		fullname = "";
		gender = "";
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public User(String username, String password, String fullname, String gender) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.gender = gender;
	}
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
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
