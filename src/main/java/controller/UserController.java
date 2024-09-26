package controller;

import model.account.User;
import service.UserService;

public class UserController{
	
	// controller ---call--> service
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	public boolean isUsernameAvailable(String username) {
		return userService.isUsernameAvailable(username);
	}
	
	// login
	public boolean login(User user) {
		return userService.login(user);
	}
	// successfully
	public void handleLoginSuccess(String username, boolean remember) {
		userService.handleLoginSuccess(username, remember);
	}
	// register
	public void register(User user) {
		userService.register(user);
	}
	// get user
	public User getUser() {
		return userService.getUser();
	}
	
}
