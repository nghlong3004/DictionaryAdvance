package model.account;

public interface UserInterface {
	// check user name
	public boolean isUsernameAvailable(String username);
	// login
	public boolean login(User user);
	// handle login is successfully
	public void handleLoginSuccess(String username, boolean remember);
	// register
	public void register(User user);
	// get user
	public User getUser();
}
