package controller;

import model.Account;
import model.User;
import service.SignInService;

public class SignInController{
	
	// controller ---call--> service
	private final SignInService signInService = new SignInService();
	
	public boolean login(User user) {
		return signInService.login(user);
	}
	public void saveDataUserCur(User user) {
		signInService.saveDataUserCur(user);
	}
	public Account getAccount() {
		return signInService.getAccount();
	}
	public User getUser() {
		return signInService.getUser();
	}
}
