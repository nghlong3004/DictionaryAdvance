package view.login;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controller.UserController;
import model.user.User;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import util.ObjectContainer;
import util.view.NotificationUI;
import view.login.component.ButtonLink;
import view.notifications.Notification;
import view.notifications.Notification.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import javax.swing.*;

public class ForgotPassword extends JPanel {

  private static final long serialVersionUID = 1017710573385464160L;

  private final UserController userController = ObjectContainer.getUserController();

  private User user;

  public ForgotPassword() {
    setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 380", "[fill]"));

    JTextArea text = new JTextArea(
        "Vui lòng nhập địa chỉ email bạn đã sử dụng để tạo \ntài khoản của mình và tôi sẽ gửi cho bạn đoạn mã OTP \nđể bạn đặt lại mật khẩu của bạn.");
    text.setEditable(false);
    text.setFocusable(false);
    text.putClientProperty(FlatClientProperties.STYLE, "" + "border:0,0,0,0;" + "background:null;");
    add(text);

    add(new JSeparator(), "gapy 15 15");

    JLabel lbEmail = new JLabel("Email");
    lbEmail.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");
    add(lbEmail);

    JTextField txtEmail = new JTextField();
    txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập vào email của bạn");
    add(txtEmail);

    JLabel lbOTP = new JLabel("OTP");
    lbOTP.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");
    add(lbOTP);

    JTextField txtOTP = new JTextField();
    txtOTP.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập mã OTP");
    add(txtOTP);

    JButton cmdSubmit = new ButtonLink("Submit");
    cmdSubmit.putClientProperty(FlatClientProperties.STYLE, "" + "foreground:#FFFFFF;");

    add(cmdSubmit, "gapy 15 15");

    cmdSubmit.setEnabled(false);
    txtOTP.setEnabled(false);

    ButtonLink cmdBackLogin = new ButtonLink("Quay trở lại đăng nhập ?");
    add(cmdBackLogin, "grow 0,al center");
    installRevealButton(txtEmail, txtOTP, cmdSubmit);
    // event
    cmdSubmit.addActionListener(actionEvent -> {
      if (userController.isOTP(txtOTP.getText())) {
        Notification.getInstance().clearAll();
        Notification.getInstance().show(Type.INFO, "Tạo mật khẩu mới!");
        ModalDialog.pushModal(new CustomModalBorder(new createrPassword(), "Tạo mật khẩu.",
            "image/forgot_password.svg"), Login.ID);
      } else {
        Notification.getInstance().clearAll();
        Notification.getInstance().show(Type.WARNING, "mã OTP SAI!");
      }
    });

    cmdBackLogin.addActionListener(actionEvent -> {
      Notification.getInstance().clearAll();
      Notification.getInstance().show(Type.INFO, "Đăng nhập!");
      ModalDialog.popModel(Login.ID);
    });
  }

  private void installRevealButton(JTextField txt, JTextField txtOTP, JButton cmdSubmit) {
    JToolBar toolBar = new JToolBar();
    toolBar.putClientProperty(FlatClientProperties.STYLE, "" + "margin:0,0,0,5;");
    JButton button = new JButton("Gửi mã OTP");

    button.addActionListener(new ActionListener() {
      private boolean show = true;

      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        if (show) {
          user = userController.getUserByEmail(txt.getText());;
          if (user == null) {
            Notification.getInstance().clearAll();
            NotificationUI.warning("Tên đăng nhập không đã tồn tại!");
            cmdSubmit.setEnabled(false);
            txtOTP.setEnabled(false);
          } else {
            Notification.getInstance().clearAll();
            NotificationUI.succes("Đã gửi mã OTP, vui lòng check!");
            new Thread(() -> {
              userController.sendOTPtoEmail(txt.getText());
            }).start();
            cmdSubmit.setEnabled(true);
            txtOTP.setEnabled(true);
            show = false;
            LocalDateTime startTime = LocalDateTime.now().plus(Duration.ofSeconds(60));
            timer = new Timer(100, e -> {
              Duration duration = Duration.between(LocalDateTime.now(), startTime);
              long second = duration.getSeconds();
              int seconds = (int) second % 60;
              String zero = "";
              if (seconds < 10) {
                zero = "0";
              }
              button.setText(zero + second);
              if (seconds == 0) {
                show = true;
                button.setText("Gửi mã OTP");
                timer.stop();
              }
            });
            timer.start();
          }
        }
      }
    });
    toolBar.add(button);
    txt.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_COMPONENT, toolBar);
  }

  private Timer timer;

  private class createrPassword extends JPanel {

    private static final long serialVersionUID = 4579914950175271155L;

    private void installRevealButton(JPasswordField txt) {
      FlatSVGIcon iconEye = new FlatSVGIcon("image/eye.svg", 0.3f);
      FlatSVGIcon iconHide = new FlatSVGIcon("image/hide.svg", 0.3f);

      JToolBar toolBar = new JToolBar();
      toolBar.putClientProperty(FlatClientProperties.STYLE, "" + "margin:0,0,0,5;");
      JButton button = new JButton(iconEye);

      button.addActionListener(new ActionListener() {

        private char defaultEchoChart = txt.getEchoChar();
        private boolean show;

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
          show = !show;
          if (show) {
            button.setIcon(iconHide);
            txt.setEchoChar((char) 0);
          } else {
            button.setIcon(iconEye);
            txt.setEchoChar(defaultEchoChart);
          }
        }
      });
      toolBar.add(button);
      txt.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_COMPONENT, toolBar);
    }

    public createrPassword() {
      setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 380", "[fill]"));
      JTextArea text = new JTextArea("Nhập mật khẩu mới.");
      text.setEditable(false);
      text.setFocusable(false);
      text.putClientProperty(FlatClientProperties.STYLE,
          "" + "border:0,0,0,0;" + "background:null;");
      add(text);

      add(new JSeparator(), "gapy 15 15");

      JLabel lbPassword = new JLabel("Tạo mật khẩu mới");
      lbPassword.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");
      add(lbPassword, "gapy 10 n");
      JPasswordField txtPassword = new JPasswordField();
      installRevealButton(txtPassword);
      txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tối thiểu 6 kí tự");
      add(txtPassword);

      JLabel lbRePassword = new JLabel("Nhập lại mật khẩu");
      lbRePassword.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");
      add(lbRePassword, "gapy 10 n");

      JPasswordField txtRePassword = new JPasswordField();
      installRevealButton(txtRePassword);
      txtRePassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập lại mật khẩu");
      add(txtRePassword);

      JButton cmdSubmit = new ButtonLink("Submit");
      cmdSubmit.putClientProperty(FlatClientProperties.STYLE, "" + "foreground:#FFFFFF;");

      add(cmdSubmit, "gapy 15 15");
      cmdSubmit.addActionListener(actionEvent -> {
        if (txtPassword.getPassword().length < 6) {
          Notification.getInstance().clearAll();
          NotificationUI.warning("Mật khẩu đang ít hơn 6 kí tự!");
        } else if (!Arrays.equals(txtPassword.getPassword(), txtRePassword.getPassword())) {
          Notification.getInstance().clearAll();
          NotificationUI.warning("Mật khẩu không khớp nhau!");
        } else {
          new Thread(() -> {
            userController.updatePasswordByUser(user);
          }).start();
          ModalDialog.closeModal(Login.ID);
          Notification.getInstance().clearAll();
          NotificationUI.succes("Tạo mật khẩu mới thành công!");
        }

      });

    }

  }
}
