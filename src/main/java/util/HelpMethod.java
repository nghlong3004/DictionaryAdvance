package util;

public class HelpMethod {
	
	public static boolean isRegExp(final String ... s) {
		for(String items: s) {
			if(!items.matches(Constants.REGULAR_EXPRESSIONS)) {
				return false;
			}
		}
		return true;
	}
	
	
}
