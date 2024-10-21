package util.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.account.User;
import model.dictionary.Word;

public class Utils {

  public static String nowTimeinString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return nowTime().format(formatter);
  }

  public static String timeNextDay(int days) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return nowTime().plusDays(days).format(formatter);
  }

  public static LocalDateTime nowTime() {
    LocalDateTime now = LocalDateTime.now();
    return now;
  }

  public static void setValues(User user, String[] keys, String[] values) {
    for (int i = 0; i < keys.length; i++) {
      String key = keys[i];
      String value = values[i];

      switch (key.trim()) {
        case "username":
          user.setUsername(value != null ? value : "NULL");
          break;
        case "password":
          user.setPassword(value != null ? value : "NULL");
          break;
        case "fullname":
          user.setFullName(value != null ? value : "NULL");
          break;
        case "gender":
          user.setGender(value != null ? value : "NULL");
          break;
        case "email":
          user.setEmail(value != null ? value : user.getUsername() + "@gmail.com");
          break;
        case "birthday":
          user.setBirthdate(value != null ? value : "NULL");
          break;
        case "created":
          user.setRegistrationDate(value != null ? value : "NULL");
          break;
        case "updated":
          user.setLastLogin(value != null ? value : "NULL");
          break;
        case "token":
          user.setToken(value != null ? value : "NULL");
          break;
        default:
          System.out.println("User unknow attribute: " + key);
      }
    }
  }

  public static void setValues(Word entry, String[] keys, String[] values) {
    for (int i = 0; i < keys.length; i++) {
      String key = keys[i];
      String value = values[i];
      switch (key) {
        case "word":
          entry.setWord(value != null ? value : "NULL");
          break;
        case "meaning":
          entry.setMeaning(value != null ? value : "NULL");
          break;
        case "languaged":
          entry.setLanguaged(value != null ? value : "NULL");
          break;
        case "pronounce":
          entry.setPronounce(value != null ? value : "NULL");
          break;
        case "description":
          entry.setDescription(value != null ? value : "NULL");
          break;
        case "part_of_speech":
          entry.setPartOfSpeech(value != null ? value : "NULL");
          break;
        case "synonym":
          entry.setSynonym(value != null ? value : "NULL");
          break;
        case "antonym":
          entry.setAntonym(value != null ? value : "NULL");
          break;
        // case "searchTime":
        // entry.setSearchTime(value != null ? value : "NULL");
        // break;
        // case "specialized":
        // entry.setSpecialized(value != null ? value : "NULL");
        // break;
        default:
          System.out.println("Word unknow attribute:: " + key);
      }
    }
  }
}
