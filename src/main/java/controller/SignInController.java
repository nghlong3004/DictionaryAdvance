package controller;

import model.Account;
import model.User;
import service.SignInService;

public class SignInController{
	
	// controller ---call--> service
	private final SignInService signInService = new SignInService();
	
	public boolean login(Account account, User user) {
		return signInService.login(account, user);
	}
	
}
