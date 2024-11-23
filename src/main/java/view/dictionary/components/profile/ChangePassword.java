package view.dictionary.components.profile;

import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingWorker;
import com.formdev.flatlaf.FlatClientProperties;
import controller.UserController;
import model.user.User;
import net.miginfocom.swing.MigLayout;
import raven.popup.GlassPanePopup;
import util.ObjectContainer;
import util.view.NotificationUI;
import view.notifications.Notification;

public class ChangePassword extends JPanel {

  private static final long serialVersionUID = 5961458275236658191L;
  
  private final UserController userController = ObjectContainer.getUserController();

  private JLabel description;

  private JPasswordField txtPassword;
  private JPasswordField txtRePassword;

  private JButton cmdDone;

  public ChangePassword() {
    initialized();
    setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 300", "[fill]"));
    description.setText("<html><body style='width: 320px;'>"
        + "<p style='color: white;'>Mật khẩu mới của bạn phải bao gồm ít nhất 6 kí tự.</p>"
        + "</body></html>");
    description.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +6;");
    txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mật khẩu mới");
    txtRePassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập lại mật khẩu mới");
    cmdDone.putClientProperty(FlatClientProperties.STYLE, "" + "[dark]foreground:#FFFFFF;" + "[light]foreground:#1e293b;");
    cmdDone.addActionListener(actionEven -> {
      User user = userController.getUser();
      char[] password = txtPassword.getPassword();
      char[] rePassword = txtRePassword.getPassword();

      Notification.getInstance().clearAll();

      if (password.length < 6) {
          NotificationUI.warning("Mật khẩu phải có ít nhất 6 ký tự!");
      } else if (!Arrays.equals(password, rePassword)) {
          NotificationUI.warning("Mật khẩu không khớp!");
      } else {
          SwingWorker<Void, Void> worker = new SwingWorker<>() {
              @Override
              protected Void doInBackground() throws Exception {
                  userController.updatePasswordByUser(user);
                  return null;
              }
              @Override
              protected void done() {
                  try {
                      get();
                      NotificationUI.succes("Cập nhật mật khẩu thành công!");
                  } catch (Exception e) {
                      NotificationUI.warning("Đã xảy ra lỗi khi cập nhật mật khẩu!");
                      e.printStackTrace();
                  }
              }
          };
          worker.execute();
          GlassPanePopup.closePopupAll();
      }
      Arrays.fill(password, '\0');
      Arrays.fill(rePassword, '\0');
      
    });
    add(description, "gapy 10, height 40");
    add(txtPassword, "gapy 10, height 40");
    add(txtRePassword, "gapy 10, height 40");
    add(cmdDone, "gapy 5, , height 35");
  }

  private void initialized() {
    description = new JLabel();
    txtPassword = new JPasswordField();
    txtRePassword = new JPasswordField();
    cmdDone = new JButton("Đổi mật khẩu");
  }

}
