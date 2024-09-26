package util.view;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import view.notifications.Notification;

import javax.swing.*;
import java.awt.*;

public class NotificationUI {

    public static Icon getIcon(String key, Icon defaultValue) {
        Icon icon = UIManager.getIcon(key);
        if (icon == null) {
            return defaultValue;
        }
        return icon;
    }
    public static void welcome() {
    	Notification.getInstance().show(Notification.Type.INFO, Notification.Location.TOP_CENTER, "Welcome to Dictionary Advance by Nghlong3004!");
    }
    public static void goodbye() {
    	Notification.getInstance().clearAll();
    	Notification.getInstance().show(Notification.Type.INFO, Notification.Location.TOP_CENTER, "GoodBye! See you again.");
    }
    public static void succes(String message) {
    	Notification.getInstance().show(Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, message);
    }
    public static void warning(String message) {
    	Notification.getInstance().show(Notification.Type.WARNING, Notification.Location.TOP_CENTER, message);
    }

    public static Insets getInsets(String key, Insets defaultValue) {
        Insets insets = UIManager.getInsets(key);
        if (insets == null) {
            return defaultValue;
        }
        return insets;
    }

    public static String getString(String key, String defaultValue) {
        String string = UIManager.getString(key);
        if (string == null) {
            return defaultValue;
        }
        return string;
    }

    public static Icon createIcon(String path, Color color, float scale) {
        FlatSVGIcon icon = new FlatSVGIcon(path, scale);
        if (color != null) {
            FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
            colorFilter.add(new Color(150, 150, 150), color);
            icon.setColorFilter(colorFilter);
        }
        return icon;
    }
}

