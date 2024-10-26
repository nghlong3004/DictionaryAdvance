package view.login;

import static util.HelpMethod.*;
import static util.repository.Utils.*;

import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;
import model.user.User;
import net.miginfocom.swing.MigLayout;
import util.view.NotificationUI;
import view.ViewDictionary;
import view.notifications.Notification;

public class Register extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 5368538115377511397L;

  private LoginPanel login;

  private JTextField textEmail;
  private JTextField textUsername;
  private JTextField textFullname;

  private JPasswordField textPassword;
  private JPasswordField textRePassword;

  private ButtonGroup groupPeople;
  private JRadioButton radioMale;
  private JRadioButton radioFemale;
  private JRadioButton radioDefault;
  private int gender;

  private JButton buttonRegister;
  private JButton buttonExit;

  public Register(LoginPanel signIn) {
    super();
    setting(signIn);
  }

  private void setting(LoginPanel signIn) {
    this.login = signIn;
    setLayout(new MigLayout("fill, insets 10", "[center]", "[center]"));
    initialization();
    // new implement
    JPanel panel = new JPanel(new MigLayout("", "fill, 250:280"));
    JPanel panelTitleExit = new JPanel(new MigLayout("wrap 3", "[] 16 [grow] []"));
    JLabel title = new JLabel("Đăng ký");
    JPanel panelGender = new JPanel(new MigLayout("", "fill, 61:70"));
    // put property
    putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
    panelGender.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
    panelTitleExit.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
    panel.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:20;" + "[light]background:darken(@background, 3%);"
            + "[dark]background:lighten(@background, 3%);");
    title.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
    buttonExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
    buttonExit.putClientProperty(FlatClientProperties.STYLE,
        "" + "[light]foreground:darken(@foreground, 30%);"
            + "[dark]foreground:lighten(@foreground, 10%);");
    buttonRegister.putClientProperty(FlatClientProperties.STYLE,
        "" + "[light]foreground:darken(@foreground, 30%);"
            + "[dark]foreground:lighten(@foreground, 30%);");
    textPassword.putClientProperty(FlatClientProperties.STYLE,
        "" + "showRevealButton:true;" + "showCapsLock:true");
    textRePassword.putClientProperty(FlatClientProperties.STYLE,
        "" + "showRevealButton:true;" + "showCapsLock:true");
    textUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,
        "Nhập tên đăng nhập hoặc email");
    textFullname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập họ và tên");
    textEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập email");
    textPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập mật khẩu");
    textRePassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập lại mật khẩu");
    buttonRegister.putClientProperty(FlatClientProperties.STYLE,
        "" + "borderWidth: 0;" + "focusWidth: 0;" + "innerFocusWidth: 0");
    // groupButton
    groupPeople.add(radioMale);
    groupPeople.add(radioFemale);
    groupPeople.add(radioDefault);

    // add action
    action();

    // add panel
    panelGender.add(new JLabel("Giới tính"));
    panelGender.add(radioMale);
    panelGender.add(radioFemale);
    panelGender.add(radioDefault, "wrap");
    panelTitleExit.add(title, "left");
    panelTitleExit.add(buttonExit, "right");
    panel.add(panelTitleExit, "span, wrap");

    panel.add(new JLabel("Tên đăng nhập hoặc email"), "gapx 8, gapy 8");
    panel.add(new JLabel("Mật khẩu"), "wrap, gapx 8, gapy 8");
    panel.add(textUsername, "gapx 8");
    panel.add(textPassword, "wrap, gapx 8");
    panel.add(new JLabel("Họ và tên"), "gapx 8");
    panel.add(new JLabel("Mật khẩu"), "wrap, gapx 8");
    panel.add(textFullname, "left, gapx 8");
    panel.add(textRePassword, "left, wrap, gapx 8");
    panel.add(panelGender, "wrap, gapx 8");
    panel.add(buttonRegister, "center, span, wrap");

    add(panel);
  }

  private void action() {
    buttonExit.addActionListener(e -> {
      ViewDictionary.login();
    });
    buttonRegister.addActionListener(e -> {
      String username = textUsername.getText();
      String password = new String(textPassword.getPassword());
      String rePassword = new String(textRePassword.getPassword());
      String fullname = textFullname.getText();
      if (!password.equals(rePassword)) {
        NotificationUI.warning("Mật khẩu mới không khớp!");
      } else if (!isValidUsername(username)) {
        NotificationUI.warning("Tên đăng nhập không hợp lệ!");
      } else if (login.getUserByEmail(username) != null) {
        NotificationUI.warning("Tên đăng nhập đã tồn tại!");
      } else if (isValidUsername(username) && isValidPassword(password)
          && login.getUserByEmail(username) == null && gender >= 0) {
        login.setUsername(username);
        login.setPassword(password);
        Notification.getInstance().clearAll();
        NotificationUI.succes("Registered successfully!!");
        User user = new User(username, password, fullname, gender, null, nowTime(), null);
        login.register(user);
        ViewDictionary.login();
      } else {
        NotificationUI.warning("Tên đăng nhập hoặc mật khẩu không hợp lệ!");
      }
    });
    ItemListener itemListener = e -> {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        JRadioButton selected = (JRadioButton) e.getItem();
        if (selected.equals(radioMale)) {
          gender = 0;
        } else if (selected.equals(radioFemale)) {
          gender = 1;
        } else if (selected.equals(radioDefault)) {
          gender = 2;
        }
      }
    };
    radioMale.addItemListener(itemListener);
    radioFemale.addItemListener(itemListener);
    radioDefault.addItemListener(itemListener);

  }

  private void initialization() {
    textEmail = new JTextField();
    textUsername = new JTextField();
    textFullname = new JTextField();

    textPassword = new JPasswordField();
    textRePassword = new JPasswordField();

    groupPeople = new ButtonGroup();
    radioFemale = new JRadioButton("Nữ");
    radioMale = new JRadioButton("Nam");
    radioDefault = new JRadioButton("Khác");
    
    gender = -1;
    
    buttonRegister = new JButton("Đăng ký");
    buttonExit = new JButton("x");
  }




}
