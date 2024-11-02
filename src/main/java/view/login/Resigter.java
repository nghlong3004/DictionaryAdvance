package view.login;

import com.formdev.flatlaf.FlatClientProperties;
import controller.UserController;
import model.user.User;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import util.ObjectContainer;
import util.view.NotificationUI;
import view.login.component.ButtonLink;
import view.notifications.Notification;
import static util.HelpMethod.isValidPassword;
import static util.HelpMethod.isValidUsername;
import static util.repository.Utils.nowTime;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class Resigter extends JPanel {

  /**
  * 
  */
  private static final long serialVersionUID = -5725310224569743587L;

  private final UserController userController = ObjectContainer.getUserController();
  private Login login;
  private JTextField txtFullname;
  private JTextField txtEmail;
  private JTextField txtDateOfBirth;
  private JPasswordField txtPassword;
  private JPasswordField txtRePassword;

  private JButton cmdSignUp;
  private ButtonLink cmdBackLogin;

  private ButtonGroup groupPeople;
  private JRadioButton radioMale;
  private JRadioButton radioFemale;
  private JRadioButton radioDefault;
  private int gender;

  public Resigter(Login login) {
    setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 300", "[fill]"));
    this.login = login;
    initialized();

    JTextArea text = new JTextArea("Đăng ký tài khoản.");
    text.setEditable(false);
    text.setFocusable(false);
    text.putClientProperty(FlatClientProperties.STYLE, "" + "border:0,0,0,0;" + "background:null;");
    add(text);

    add(new JSeparator(), "gapy 8 8");

    JLabel lbEmail = new JLabel("Email");
    lbEmail.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");
    add(lbEmail);

    txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập email của bạn");
    add(txtEmail);

    JLabel lbFullname = new JLabel("Họ và tên");
    lbFullname.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");
    add(lbFullname);

    txtFullname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập họ và tên");
    add(txtFullname);

    JLabel lbPassword = new JLabel("Tạo mật khẩu mới");
    lbPassword.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");
    add(lbPassword, "gapy 10 n");

    txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tối thiểu 8 kí tự");
    add(txtPassword);

    JLabel lbRePassword = new JLabel("Tạo mật khẩu mới");
    lbRePassword.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");
    add(lbRePassword, "gapy 10 n");

    txtRePassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập lại mật khẩu");
    add(txtRePassword);

    JLabel lbDateOfBirth = new JLabel("Ngày tháng năm sinh");
    lbDateOfBirth.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");
    add(lbDateOfBirth, "gapy 10 n");

    txtDateOfBirth = new JTextField();
    txtDateOfBirth.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "MM / DD / YYYY");
    add(txtDateOfBirth);

    JLabel lbNote =
        new JLabel("Chúng tôi sẽ dành cho bạn một món quà đặc biệt vào ngày sinh nhật của bạn");
    lbNote.putClientProperty(FlatClientProperties.STYLE,
        "" + "font:-1;" + "foreground:$Label.disabledForeground;");
    add(lbNote);

    JPanel panelGender = new JPanel(new MigLayout("", "fill, 61:70"));
    panelGender.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");

    groupPeople.add(radioMale);
    groupPeople.add(radioFemale);
    groupPeople.add(radioDefault);

    panelGender.add(new JLabel("Giới tính"));
    panelGender.add(radioMale);
    panelGender.add(radioFemale);
    panelGender.add(radioDefault, "wrap");
    add(panelGender);

    add(new JCheckBox("Đồng ý với điều khoản"), "gapy 10 10");

    cmdSignUp = new ButtonLink("Đăng ký");
    cmdSignUp.putClientProperty(FlatClientProperties.STYLE, "" + "foreground:#FFFFFF;");
    add(cmdSignUp);

    add(new JSeparator(), "gapy 15 15");

    add(new JLabel("Đã có tài khoản ?"), "split 2, gapx push n");

    cmdBackLogin = new ButtonLink("Đăng nhập");
    add(cmdBackLogin, "gapx n push");

    // event
    action();
  }

  private void action() {
    cmdBackLogin.addActionListener(actionEvent -> {
      ModalDialog.popModel(Login.ID);
    });
    cmdSignUp.addActionListener(actionEvent -> {
      String email = txtEmail.getText();
      String password = new String(txtPassword.getPassword());
      String rePassword = new String(txtPassword.getPassword());
      String fullname = txtFullname.getText();
      if (!password.equals(rePassword)) {
        NotificationUI.warning("Mật khẩu mới không khớp!");
      } else if (!isValidUsername(email)) {
        NotificationUI.warning("Tên đăng nhập không hợp lệ!");
      } else if (userController.getUserByEmail(email) != null) {
        NotificationUI.warning("Tên đăng nhập đã tồn tại!");
      } else if (isValidUsername(email) && isValidPassword(password)
          && userController.getUserByEmail(email) == null && gender >= 0) {
        login.setEmail(email);
        login.setPassword(password);
        Notification.getInstance().clearAll();
        NotificationUI.succes("Registered successfully!!");
        User user = new User(email, password, fullname, gender,
            LocalDate.parse(txtDateOfBirth.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            nowTime(), nowTime());
        userController.register(user);
        ModalDialog.popModel(Login.ID);
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

  private void initialized() {
    txtEmail = new JTextField();
    txtFullname = new JTextField();

    txtPassword = new JPasswordField();
    txtRePassword = new JPasswordField();

    groupPeople = new ButtonGroup();
    radioFemale = new JRadioButton("Nữ");
    radioMale = new JRadioButton("Nam");
    radioDefault = new JRadioButton("Khác");

    gender = -1;


  }
}
