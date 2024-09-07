package util;

import static util.Constants.*;

import model.Account;

public class HelpMethod {
	
	public static boolean isRegExp(final String ... s) {
		for(String items: s) {
			if(!items.matches(Constants.REGULAR_EXPRESSIONS)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isValidUsername(String username, Account account) {
		if(username == null) {
			return false;
		}
		if(account.isId(username)) {
			return false;
		}
		if((username.matches(REGULAR_EMAIL_EXPRESSIONS) || username.matches(REGULAR_EXPRESSIONS))) {
			return true;
		}
		return false;
	}
	public static boolean isValidPassword(String password) {
		if(password == null) {
			return false;
		}
		if(password.matches(REGULAR_PASS_EXPRESSIONS)) {
			return true;
		}
		return false;
	}

	
}
