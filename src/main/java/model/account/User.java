package model.account;

public class User {
	private String username;
    private String password;
    private String fullName;
    private String gender;
    private String birthdate;
    private String email;
    private String registrationDate;
    private String lastLogin;
    private boolean remember;

    public User(String username, String password, String fullName, String gender, String registrationDate, String lastLogin, boolean remember) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.setGender(gender);
        this.email = "unknow";
        this.registrationDate = registrationDate;
        this.lastLogin = lastLogin;
        this.setRemember(remember);
    }
    

	public User() {
		this.username = new String();
        this.password = new String();
        this.fullName = new String();
        this.birthdate = new String();
        this.email = new String();
        this.registrationDate = new String();
        this.lastLogin = new String();
        this.setRemember(false);
	}

	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public boolean isRemember() {
		return remember;
	}
	public void setRemember(boolean remember) {
		this.remember = remember;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}
    
}