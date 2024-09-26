package util.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
	public static String nowTimeinString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = nowTime().format(formatter);
        return formattedDateTime;
	}
	public static LocalDateTime nowTime() {
		LocalDateTime now = LocalDateTime.now();
        return now;
	}
}
