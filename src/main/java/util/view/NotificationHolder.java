package util.view;

import java.util.ArrayList;
import java.util.List;

import view.notifications.Notification;

public class NotificationHolder {
    private final List<Notification.NotificationAnimation> lists = new ArrayList<>();
    private final Object lock = new Object();

    public int getHoldCount() {
        return lists.size();
    }

    public Notification.NotificationAnimation getHold(Notification.Location location) {
        synchronized (lock) {
            for (int i = 0; i < lists.size(); ++i) {
                Notification.NotificationAnimation n = lists.get(i);
                if (n.getLocation() == location) {
                    return n;
                }
            }
            return null;
        }
    }

    public void removeHold(Notification.NotificationAnimation notificationAnimation) {
        synchronized (lock) {
            lists.remove(notificationAnimation);
        }
    }

    public void hold(Notification.NotificationAnimation notificationAnimation) {
        synchronized (lock) {
            lists.add(notificationAnimation);
        }
    }

    public void clearHold() {
        synchronized (lock) {
            lists.clear();
        }
    }

    public void clearHold(Notification.Location location) {
        synchronized (lock) {
            for (int i = 0; i < lists.size(); ++i) {
                Notification.NotificationAnimation n = lists.get(i);
                if (n.getLocation() == location) {
                    lists.remove(n);
                    --i;
                }
            }
        }
    }
}
