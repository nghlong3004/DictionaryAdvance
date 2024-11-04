package view.login;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controller.UserController;
import model.user.User;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import util.ObjectContainer;
import util.view.NotificationUI;
import view.ViewDictionary;
import view.login.component.ButtonLink;
import view.notifications.Notification;
import javax.swing.*;
import static util.Constants.Image.IMAGE_FACEBOOK;
import static util.Constants.Image.IMAGE_GOOGLE;
import static util.Constants.Image.IMAGE_PATH;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel {

  private static final long serialVersionUID = -7766038430284342110L;
  public static final String ID = "login_id";

  private final UserController userController = ObjectContainer.getUserController();

  private JTextField txtEmail;

  private JPasswordField txtPassword;

  public Login() {
    setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 380", "[fill]"));
    initialized();
    JTextArea text = new JTextArea("Đăng nhập.");
    text.setEditable(false);
    text.setFocusable(false);
    text.putClientProperty(FlatClientProperties.STYLE, "" + "border:0,0,0,0;" + "background:null;");
    add(text);

    add(new JSeparator(), "gapy 15 15");

    JLabel lbEmail = new JLabel("Email");
    lbEmail.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");
    lbEmail.putClientProperty(FlatClientProperties.STYLE,
        "" + "border:0,0,0,0;" + "background:null;");
    add(lbEmail);

    txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập vào email của bạn");

    add(txtEmail);

    JLabel lbPassword = new JLabel("Password");
    lbPassword.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");
    lbPassword.putClientProperty(FlatClientProperties.STYLE,
        "" + "border:0,0,0,0;" + "background:null;");
    add(lbPassword, "gapy 10 n");

    txtPassword = new JPasswordField();
    installRevealButton(txtPassword);
    txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập mật khẩu của bạn");

    add(txtPassword);

    JCheckBox remember = new JCheckBox("Nhớ mật khẩu");
    remember.putClientProperty(FlatClientProperties.STYLE,
        "" + "border:0,0,0,0;" + "background:null;");
    add(remember, "split 2,gapy 10 10");
    ButtonLink cmdForgotPassword = new ButtonLink("Quên mật khẩu ?");
    add(cmdForgotPassword, "gapx push n");

    JButton cmdLogin = new ButtonLink("Đăng nhập");
    cmdLogin.putClientProperty(FlatClientProperties.STYLE, "" + "foreground:#FFFFFF;");
    add(cmdLogin);

    add(new JSeparator(), "gapy 15 15");

    add(callFbAndGg());

    add(new JLabel("Không có tài khoản ?"), "split 2,gapx push n");
    ButtonLink cmdSignUp = new ButtonLink("Sign up");
    add(cmdSignUp, "gapx n push");

    // event
    cmdLogin.addActionListener(actionEvent -> {
      User user = new User();
      user.setEmail(txtEmail.getText());
      user.setPassword(new String(txtPassword.getPassword()));
      if (userController.login(user)) {
        userController.handleLoginSuccess(user.getEmail(), remember.isSelected());
        ModalDialog.closeModal(ID);
        ViewDictionary.open();
        Notification.getInstance().clearAll();
        NotificationUI.succes("Log in successfully!");
      } else {
        NotificationUI.succes("Tài khoản hoặc mật khẩu không chính xác !");
      }
    });

    cmdSignUp.addActionListener(actionEvent -> {
      String icon = "image/signup.svg";
      ModalDialog.pushModal(new CustomModalBorder(new Resigter(this), "Sign up", icon), ID);
      Notification.getInstance().clearAll();
      NotificationUI.succes("Sign up!");
    });

    cmdForgotPassword.addActionListener(actionEvent -> {
      String icon = "image/forgot_password.svg";
      ModalDialog.pushModal(new CustomModalBorder(new ForgotPassword(), "Forgot password", icon),
          ID);
      Notification.getInstance().clearAll();
      NotificationUI.succes("Forgot password!");
    });
  }

  private void initialized() {
    txtEmail = new JTextField();

  }

  public void setPassword(String message) {
    if (message != null) {
      this.txtPassword.setText(message);
    }
  }

  public void setEmail(String message) {
    if (message != null) {
      this.txtEmail.setText(message);
    }
  }

  public void register(User user) {
    userController.register(user);
  }

  private ImageIcon getIcon(String path) {
    ImageIcon icon = new ImageIcon(path);
    Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    return new ImageIcon(scaledImage);
  }

  private Component callExternalApi(String destination) {
    JButton button = new JButton(getIcon(IMAGE_PATH + destination));
    button.setFocusPainted(false);
    button.putClientProperty(FlatClientProperties.STYLE,
        "" + "[light]foreground:darken(@foreground, 80%);"
            + "[dark]foreground:lighten(@foreground, 60%);"
            + "[light]background:lighten(@background, 80%);"
            + "[dark]background:lighten(@background, 60%);");
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    return button;
  }

  private Component callFbAndGg() {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
    panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");
    panel.add(callExternalApi(IMAGE_FACEBOOK));
    panel.add(callExternalApi(IMAGE_GOOGLE));
    return panel;
  }

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
}
