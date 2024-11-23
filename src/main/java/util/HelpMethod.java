package util;

import static util.Constants.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

public class HelpMethod {

  public static boolean isRegExp(final String... s) {
    for (String items : s) {
      if (!items.matches(Constants.REGULAR_EXPRESSIONS)) {
        return false;
      }
    }
    return true;
  }

  public static boolean isValidUsername(String username) {
    if (username == null) {
      return false;
    }
    if ((username.matches(REGULAR_EMAIL_EXPRESSIONS))) {
      return true;
    }
    return false;
  }

  public static boolean isDate(String dateOfBirth) {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.US)
        .withResolverStyle(ResolverStyle.STRICT);
    try {
      dateFormatter.parse(dateOfBirth);
    } catch (DateTimeParseException e) {
      return false;
    }
    return true;
  }

  public static boolean isValidPassword(String password) {
    if (password == null) {
      return false;
    }
    if (password.matches(REGULAR_PASS_EXPRESSIONS)) {
      return true;
    }
    return false;
  }


}
