package controller;

import model.account.User;
import service.UserService;

public class UserController{
	
	// controller ---call--> service
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	public boolean isUsername(String username) {
		return userService.isUsername(username);
	}
	
	// login
	public boolean login(User user) {
		return userService.login(user);
	}
	// successfully
	public void successfully(String username, boolean remember) {
		userService.successfully(username, remember);
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
