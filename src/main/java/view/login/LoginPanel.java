package view.login;

import static util.Constants.Image.*;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;

import model.account.User;
import net.miginfocom.swing.MigLayout;
import util.ObjectContainer;
import util.view.NotificationUI;
import view.dictionary.ViewDictionary;
import view.notifications.Notification;

public class LoginPanel extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 9112353425595838469L;
  private static final ObjectContainer OBJECTCONTAINER = new ObjectContainer();
  private JTextField textUsername;
  private JPasswordField textPassword;
  private User user;

  private JCheckBox remember;
  private boolean flagRemember = false;

  private JButton clickLogin;
  private ImageIcon icon;

  public LoginPanel() {
    setting();

  }

  private void setting() {
    setLayout(new MigLayout("fill, insets 20", "[grow, right]", "10[center]10"));
    initialization();
    JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 100 100 100 100", "[fill]"));
    JLabel title = new JLabel("Đăng nhập");
    JLabel description = new JLabel("Vui lòng đăng nhập vào tài khoản của bạn");
    panel.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:20;" + "[light]background:darken(@background, 3%);"
            + "[dark]background:lighten(@background, 3%);");
    panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
    title.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
    description.putClientProperty(FlatClientProperties.STYLE, "" +

        "[light]foreground:darken(@foreground, 30%);"
        + "[dark]foreground:lighten(@foreground, 30%);");
    clickLogin.putClientProperty(FlatClientProperties.STYLE,
        "" + "[light]foreground:darken(@foreground, 80%);"
            + "[dark]foreground:lighten(@foreground, 60%);");
    textUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,
        "Nhập tên đăng nhập hoặc email");
    textPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập mật khẩu");
    textPassword.putClientProperty(FlatClientProperties.STYLE,
        "" + "showRevealButton:true;" + "showCapsLock:true");
    clickLogin.putClientProperty(FlatClientProperties.STYLE,
        "" + "borderWidth: 0;" + "focusWidth: 0;" + "innerFocusWidth: 0");
    action();
    panel.add(title);
    panel.add(description);

    panel.add(new JLabel("Tài khoản"), "gapy 8");
    panel.add(textUsername);

    panel.add(new JLabel("Mật khẩu"), "gapy 8");
    panel.add(textPassword);

    panel.add(remember, "grow 0");
    panel.add(clickLogin, "gapy 10");
    panel.add(forgotPass(), "center, grow 0, gapy 10");
    panel.add(callFBandGG(), "gapy 10");
    panel.add(callSignUp(), "gapy 10");
    add(panel);
  }

  private ImageIcon getIcon(String path) {
    ImageIcon icon = new ImageIcon(path);
    Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    return new ImageIcon(scaledImage);
  }

  private Component callFBandGG() {
    JButton buttonFb = new JButton(getIcon(IMAGE_PATH + IMAGE_FACEBOOK));
    JButton buttonGg = new JButton(getIcon(IMAGE_PATH + IMAGE_GOOGLE));
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
    buttonFb.setFocusPainted(false);
    buttonGg.setFocusPainted(false);
    buttonFb.putClientProperty(FlatClientProperties.STYLE,
        "" + "[light]foreground:darken(@foreground, 80%);"
            + "[dark]foreground:lighten(@foreground, 60%);"
            + "[light]background:lighten(@background, 80%);"
            + "[dark]background:lighten(@background, 60%);");
    buttonGg.putClientProperty(FlatClientProperties.STYLE,
        "" + "[light]foreground:darken(@foreground, 80%);"
            + "[dark]foreground:lighten(@foreground, 60%);"
            + "[light]background:lighten(@background, 80%);"
            + "[dark]background:lighten(@background, 60%);");
    buttonFb.setCursor(new Cursor(Cursor.HAND_CURSOR));
    buttonGg.setCursor(new Cursor(Cursor.HAND_CURSOR));
    panel.add(buttonFb);
    panel.add(buttonGg);
    return panel;
  }

  private void action() {

    remember.addItemListener(e -> {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        flagRemember = !flagRemember;
      }
    });

    clickLogin.addActionListener(e -> {
      User user = new User();
      user.setUsername(textUsername.getText());
      user.setPassword(new String(textPassword.getPassword()));
      if (OBJECTCONTAINER.getControllerInstance().login(user)) {
        OBJECTCONTAINER.getControllerInstance().handleLoginSuccess(user.getUsername(),
            remember.isSelected());
        ViewDictionary.open();
        Notification.getInstance().clearAll();
        NotificationUI.succes("Log in successfully!");
      } else {
        NotificationUI.succes("Tài khoản hoặc mật khẩu không chính xác !");
      }
    });

  }

  private Component forgotPass() {
    MyButton clickForgotP = new MyButton("Quên mật khẩu?", "#d1d5db", "#f3f4f6", false);
    return clickForgotP;
  }

  private Component callSignUp() {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
    MyButton clickRegister = new MyButton("Đăng ký ngay", "#059669", "#86efac", true);
    JLabel label = new JLabel("Bạn chưa có tài khoản ?");
    panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
    label.putClientProperty(FlatClientProperties.STYLE,
        "" + "[light]foreground:darken(@foreground, 30%);"
            + "[dark]foreground:lighten(@foreground, 30%);");
    clickRegister.addActionListener(e -> {
      Notification.getInstance().clearAll();
      ViewDictionary.resigter();
    });
    panel.add(label);
    panel.add(clickRegister);
    return panel;
  }

  private void initialization() {

    user = OBJECTCONTAINER.getControllerInstance().getUser();

    textUsername = new JTextField(user.getUsername());

    textPassword = new JPasswordField(new String(user.getPassword()));

    clickLogin = new JButton("Đăng nhập");

    remember = new JCheckBox("Nhớ mật khẩu");
    if (user.isRemember()) {
      remember.setSelected(true);
    }

    icon = new ImageIcon(IMAGE_PATH + IMAGE_LOGIN_BACKGROUND);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
  }

  public void setPassword(String message) {
    if (message != null) {
      this.textPassword.setText(message);
    }
  }

  public void setUsername(String message) {
    if (message != null) {
      this.textUsername.setText(message);
    }
  }

  public boolean isUsernameAvailable(String username) {
    return OBJECTCONTAINER.getControllerInstance().isUsernameAvailable(username);
  }

  public User getUser() {
    return this.user;
  }

  public void register(User user) {
    this.user = user;
    OBJECTCONTAINER.getControllerInstance().register(user);
  }

}
