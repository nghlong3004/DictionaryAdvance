package service;

import model.account.User;
import repository.AccountRepository;

public class UserService {
	
	public static UserService instance;
	private final AccountRepository account;
	
	public static synchronized UserService getInstance(AccountRepository account) {
		if(instance == null) {
			instance = new UserService(account);
		}
		return instance;
	}
	private UserService(AccountRepository account) {
		this.account = account;
	}
	
	public boolean isUsername(String username) {
		return account.isUsername(username);
	}
	// login
	public boolean login(User user) {
		return account.isUser(user);
	}
	// successfully
	public void successfully(String username, boolean remember) {
		account.updateUser(username, remember);
	}
	// register
	public void register(User user) {
		account.addUser(user);
	}
	// get user
	public User getUser() {
		return account.getUser();
	}
}
