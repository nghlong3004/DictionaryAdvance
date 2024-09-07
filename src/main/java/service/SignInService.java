package service;

import model.Account;
import model.User;
import util.HelpMethod;

public class SignInService {
	

	public SignInService() {
		
	}
	public boolean login(Account account, User user) {
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
	
	
	
}
