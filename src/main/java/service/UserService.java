package service;

import model.account.User;
import repository.AccountInterface;

public class UserService implements UserServiceInterface{
	
	private static UserService instance;
	private final AccountInterface account;
	
	public static synchronized UserService getInstance(AccountInterface account) {
		if(instance == null) {
			instance = new UserService(account);
		}
		return instance;
	}
	private UserService(AccountInterface account) {
		this.account = account;
	}
	@Override
	public boolean isUsernameAvailable(String username) {
		return account.isUsernameAvailable(username);
	}
	// login
	@Override
	public boolean login(User user) {
		return account.isUser(user);
	}
	// successfully
	@Override
	public void handleLoginSuccess(String username, boolean remember) {
		account.handleLoginSuccess(username, remember);
	}
	// register
	@Override
	public void register(User user) {
		account.addUser(user);
	}
	// get user
	@Override
	public User getUser() {
		return account.getUser();
	}
}
