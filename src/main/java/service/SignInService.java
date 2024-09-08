package service;

import model.Account;
import model.User;
import util.HelpMethod;

public class SignInService {
	
	private static Account account = new Account();
	
	public SignInService() {
		
	}
	public boolean login(User user) {
		if(HelpMethod.isRegExp(user.getName(), user.getPassword()) == false) {
			return false;
		}
		if(account.isId(user.getName())) {
			if(account.getPassword(user.getName()).equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}
	public void saveDataUserCur(User user) {
		account.saveDataUserCur(user);
	}
	public Account getAccount() {
		return account;
	}
	public User getUser() {
		return account.getUser();
	}
	
	
	
}
