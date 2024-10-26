package util.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

}
