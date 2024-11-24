package view.dictionary.components.profile;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.formdev.flatlaf.FlatClientProperties;
import controller.UserController;
import model.user.User;
import net.miginfocom.swing.MigLayout;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import util.ObjectContainer;
import util.extral.AvatarIcon;
import util.view.NotificationUI;
import view.ViewDictionary;
import view.login.component.ButtonLink;
import view.notifications.Notification;
import view.notifications.Notification.Type;

public class ProfileForm extends JPanel {

  private static final long serialVersionUID = 5656687104479956777L;

  private final UserController userController = ObjectContainer.getUserController();

  private User user;

  private short gender;
  
  private File file;

  public ProfileForm(User user) {
    this.user = user;
    initialized();
    JPanel panelSystem = (JPanel) infoSystem();

    JPanel panelUser = (JPanel) infoUser();

    JPanel panelDescription = (JPanel) description();

    JButton cmdSaveChange = new JButton("Lưu thay đổi");
    cmdSaveChange.putClientProperty(FlatClientProperties.STYLE,
        "" + "[dark]foreground:#FFFFFF;" + "[light]foreground:#57534e");
    cmdSaveChange.addActionListener(actionEvent -> {
      int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu thay đổi này?",
          "Cập nhật tài khoản", JOptionPane.YES_NO_OPTION);
      if (choice == JOptionPane.YES_OPTION) {
        Notification.getInstance().show(Type.SUCCESS, "Cập nhật tài khoản thành công");
        user.setFullname(txtFullname.getText());
        user.setBirthday(
            LocalDate.parse(txtDateOfBirth.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        user.setGender(gender);
        userController.updateUser(user);
        JOptionPane.showMessageDialog(null, "Cập nhật tài khoản thành công.");
      } else {
        Notification.getInstance().show(Type.INFO, "Hủy thao tác");
        JOptionPane.showMessageDialog(null, "Đã hủy thao tác.");
      }
    });
    GroupLayout groupLayout = new GroupLayout(this);
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        .addGroup(groupLayout.createSequentialGroup().addGap(58)
            .addComponent(panelSystem, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE).addGap(51)
            .addComponent(panelUser, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE).addGap(50))
        .addGroup(Alignment.LEADING,
            groupLayout.createSequentialGroup().addGap(150)
                .addComponent(panelDescription, GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE)
                .addGap(150))
        .addGroup(groupLayout.createSequentialGroup().addGap(568).addComponent(cmdSaveChange)
            .addGap(600)));
    groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup().addGap(81)
            .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                .addComponent(panelUser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE)
                .addComponent(panelSystem, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
            .addGap(30)
            .addComponent(panelDescription, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
            .addGap(10).addComponent(cmdSaveChange).addGap(31)));
    setLayout(groupLayout);
  }

  private JComponent description() {
    JPanel panel = new JPanel();
    panel.putClientProperty(FlatClientProperties.STYLE, "" + "arc:25;" + "background:#d6d3d1");
    panel.setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 300", "[fill]"));
    String htmlText = "<html>"
        + "<div style='padding: 10px; border-radius: 5px; color: #333; font-family: Arial;'>"
        + "<p style='font-size: 14px;'>Xóa tài khoản</p>"
        + "<p style='font-size: 10px;'>Xóa tài khoản sẽ gỡ bỏ hoàn toàn các nội dung có liên quan đến tài khoản này. Chúng tôi sẽ không giải quyết các vấn đề về dữ liệu khi bạn xóa tài khoản.</p>"
        + "</div>" + "</html>";
    JLabel lbDescription = new JLabel(htmlText);
    JButton cmdDelete = new JButton("Xóa vĩnh viễn");
    cmdDelete.addActionListener(actionEvent -> {
      Notification.getInstance().clearAll();
      Notification.getInstance().show(Type.WARNING, "Xóa tài khoản");
      int choice =
          JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa tài khoản này vĩnh viễn?",
              "Xóa tài khoản", JOptionPane.YES_NO_OPTION);
      if (choice == JOptionPane.YES_OPTION) {
        Notification.getInstance().show(Type.SUCCESS, "Xóa tài khoản thành công");
        JOptionPane.showMessageDialog(null, "Xóa thành công.");
        userController.deleteUser(user);
        ViewDictionary.login();
        NotificationUI.goodbye();
      } else {
        Notification.getInstance().show(Type.INFO, "Hủy thao tác");
        JOptionPane.showMessageDialog(null, "Đã hủy thao tác.");
      }
    });
    panel.add(lbDescription, "split 2");
    panel.add(cmdDelete);

    return panel;
  }

  private String deDate(String birthday) {
    String[] date = birthday.split("-");
    return date[2] + "/" + date[1] + "/" + date[0];
  }

  private JComponent headSystem() {
    JPanel panel = new JPanel();
    panel.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:25;" + "[light]background:#fef3c7;" + "[dark]background:#f5f5f4;");
    panel.setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 300", "[fill]"));
    AvatarIcon icon = new AvatarIcon(getClass().getResource("/image/user-img.png"), 100, 100, 999);
    lbAvatar.setIcon(icon);
    lbAvatar.putClientProperty(FlatClientProperties.STYLE,
        "" + "font:$Menu.header.font;" + "foreground:$Menu.foreground");
    lbAvatar.setHorizontalAlignment(SwingConstants.CENTER);
    lbAvatar.setVerticalAlignment(SwingConstants.CENTER);

    icon = new AvatarIcon(getClass().getResource("/image/change.png"), 20, 18, 999);

    JButton cmdAvatar = new MyJButton(icon);
    cmdAvatar.addActionListener(actionEvent -> {
      JFileChooser fc = new JFileChooser();
      fc.showOpenDialog(fc);
      file = fc.getSelectedFile();
      if(file != null) {
        String filePath = file.getAbsolutePath();
        if(filePath.indexOf('.') != -1) {
          AvatarIcon iconA = new AvatarIcon(filePath, 40, 40, 999);
          lbAvatar.setIcon(iconA);
        }
      }
    });
    cmdAvatar.putClientProperty(FlatClientProperties.STYLE,
        "arc:999;" + "[dark]foreground:#FFFFFF;" + "[light]foreground:#57534e;"
            + "[dark]background:#ef4444;" + "[light]background:#34d399;");

    panel.add(lbAvatar);
    panel.add(cmdAvatar, "gapx push n");
    return panel;
  }
  
  private String timeUsed() {
    Duration duration = Duration.between(user.getUpdated(), util.repository.Utils.nowTime());
    long second = duration.getSeconds();
    String htmlString = "<html>"
        + "<div style='padding: 10px; border-radius: 5px; color: #333; font-family: Arial;'>"
        + "<p style='font-size: 14px;'>Thời gian sử dụng trong ngày</p>"
        + "<p style='font-size: 10px;'>Hôm nay bạn đã sử dụng từ điển trong <span style='color: #a8a29e;'>"
        + (second / 60 / 60) % 24 + "</span> tiếng <span style='color: #a8a29e;'>"
        + (second / 60) % 60 + "</span> phút <span style='color: #a8a29e;'>" + second % 60
        + "</span> giây.</p>" + "</div>" + "</html>";
    return htmlString;
  }

  private JComponent infoSystem() {
    JPanel panel = new JPanel();
    panel.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:25;" + "[light]background:#fcd34d;" + "[dark]background:#a3a3a3;");
    panel.setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 300", "[fill]"));

    String htmlText = "<html>"
        + "<div style='padding: 10px; border-radius: 5px; color: #333; font-family: Arial;'>"
        + "<p style='font-size: 14px;'>Ngày tạo tài khoản</p>"
        + "<p style='font-size: 10px;'>Bạn đã tạo tài khoản ngày <span style='color: #007bff;'>"
        + deDate(user.getCreated().toLocalDate().toString()) + " </span></p>" + "</div>"
        + "</html>";
    lbAvatar = new JLabel();
    lbCreationDate = new JLabel(htmlText);
    lbUsageTime = new JLabel(timeUsed());
    Timer timer = new Timer(100, e -> {
      lbUsageTime.setText(timeUsed());
    });
    timer.start();
    htmlText = "<html>"
        + "<div style='padding: 10px; border-radius: 5px; color: #333; font-family: Arial;'>"
        + "<p style='font-size: 14px;'>Số lần tìm kiếm từ trong ngày</p>"
        + "<p style='font-size: 10px;'>Hôm nay bạn đã sử dụng từ điển để tìm kiếm từ <span style='color: #a8a29e;'>"
        + "0" + "</span> lần</p>" + "</div>" + "</html>";
    lbUsageSearch = new JLabel(htmlText);
    lbCreationDate.setOpaque(true);
    lbCreationDate.putClientProperty(FlatClientProperties.STYLE,
        "" + "[dark]background:#d4d4d8;" + "[light]background:#fde68a;" + "font:bold;" + "arc:25;");
    lbUsageTime.setOpaque(true);
    lbUsageTime.putClientProperty(FlatClientProperties.STYLE,
        "" + "[dark]background:#d4d4d8;" + "[light]background:#fde68a;" + "font:bold;" + "arc:25;");
    lbUsageSearch.setOpaque(true);
    lbUsageSearch.putClientProperty(FlatClientProperties.STYLE,
        "" + "[dark]background:#d4d4d8;" + "[light]background:#fde68a;" + "font:bold;" + "arc:25;");

    panel.add(headSystem());
    panel.add(lbCreationDate);
    panel.add(lbUsageSearch);
    panel.add(lbUsageTime);
    return panel;
  }

  private JComponent infoUser() {

    switch (user.getGender()) {
      case 0:
        radioMale.setSelected(true);
        break;
      case 1:
        radioFemale.setSelected(true);
        break;
      default:
        radioDefault.setSelected(true);
        break;
    }
    JPanel panel = new JPanel();

    panel.setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 300", "[fill]"));

    String htmlText = "<html>"
        + "<div style='padding: 10px; border-radius: 5px; color: #333; font-family: Arial;'>"
        + "<p style='font-size: 14px; color: #f97316;'>Thông tin tài khoản</p>" + "</div>"
        + "</html>";

    JLabel lbTitle = new JLabel(htmlText, SwingConstants.CENTER);
    panel.add(lbTitle, "gapy 10 n");

    JPanel panelGender = new JPanel(new MigLayout("", "fill, 61:70"));
    panelGender.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");

    groupPeople.add(radioMale);
    groupPeople.add(radioFemale);
    groupPeople.add(radioDefault);

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

    JLabel lbGender = new JLabel("Giới tính");
    lbGender.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");

    panelGender.add(lbGender);
    panelGender.add(radioMale);
    panelGender.add(radioFemale);
    panelGender.add(radioDefault, "wrap");
    panel.add(panelGender);

    JLabel lbEmail = new JLabel("Email");
    lbEmail.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");
    panel.add(lbEmail);

    panel.add(txtEmail, "split 2");

    AvatarIcon icon = new AvatarIcon(getClass().getResource("/image/change.png"), 20, 18, 999);

    JButton cmdEmail = new MyJButton(icon);
    cmdEmail.addActionListener(actionEvent -> {
      txtEmail.setEditable(!txtEmail.isEditable());
      txtEmail.setFocusable(!txtEmail.isFocusable());
    });
    cmdEmail.setVisible(false);
    panel.add(cmdEmail, "width 30!, gapx 10, wrap");

    JLabel lbFullname = new JLabel("Họ và tên");
    lbFullname.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");
    panel.add(lbFullname);

    panel.add(txtFullname, "split 2");

    JButton cmdFullname = new MyJButton(icon);
    cmdFullname.addActionListener(actionEvent -> {
      txtFullname.setEditable(!txtFullname.isEditable());
      txtFullname.setFocusable(!txtFullname.isFocusable());
    });
    panel.add(cmdFullname, "width 30!, gapx 10, wrap");

    JLabel lbDateOfBirth = new JLabel("Ngày tháng năm sinh");
    lbDateOfBirth.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");
    panel.add(lbDateOfBirth, "gapy 10 n");

    panel.add(txtDateOfBirth, "split 2");
    txtDateOfBirth.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void removeUpdate(DocumentEvent e) {}

      private void assistDateText() {
        Runnable doAssist = new Runnable() {
          @Override
          public void run() {
            if(txtDateOfBirth.getText().length() == 2 || txtDateOfBirth.getText().length() == 5) {
              txtDateOfBirth.setText(txtDateOfBirth.getText() + (char)47);
            }
          }
        };
        SwingUtilities.invokeLater(doAssist);
      }

      @Override
      public void insertUpdate(DocumentEvent e) {
        assistDateText();
      }

      @Override
      public void changedUpdate(DocumentEvent e) {}
    });

    JButton cmdDateOfBirth = new MyJButton(icon);
    cmdDateOfBirth.addActionListener(actionEvent -> {
      txtDateOfBirth.setEditable(!txtDateOfBirth.isEditable());
      txtDateOfBirth.setFocusable(!txtDateOfBirth.isFocusable());
    });
    panel.add(cmdDateOfBirth, "width 30!, gapx 10, wrap");

    JLabel lbChangePassword = new JLabel("Thay đổi mật khẩu");
    lbChangePassword.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold;");
    panel.add(lbChangePassword, "gapy 10 n");

    cmdChangePassword = new ButtonLink("Đổi mật khẩu");
    cmdChangePassword.putClientProperty(FlatClientProperties.STYLE,
        "" + "[dark]foreground:#FFFFFF;" + "[light]foreground:#57534e;"
            + "[dark]background:#ef4444;" + "[light]background:#34d399;");
    cmdChangePassword.addActionListener(actionEven -> {
      GlassPanePopup.showPopup(new SimplePopupBorder(new ChangePassword(), "Đổi mật khẩu"));
    });
    panel.add(cmdChangePassword);

    return panel;

  }

  private void initialized() {
    groupPeople = new ButtonGroup();

    radioDefault = new JRadioButton("Khác");
    radioFemale = new JRadioButton("Nữ");
    radioMale = new JRadioButton("Nam");

    txtDateOfBirth = new MyTextField("MM / DD / YYYY", deDate(user.getBirthday().toString()));
    txtEmail = new MyTextField("Nhập email của bạn", user.getEmail());
    txtFullname = new MyTextField("Nhập họ và tên", user.getFullname());

  }

  private JLabel lbAvatar;
  private JLabel lbCreationDate;
  private JLabel lbUsageTime;
  private JLabel lbUsageSearch;

  private ButtonGroup groupPeople;

  private JRadioButton radioMale;
  private JRadioButton radioFemale;
  private JRadioButton radioDefault;

  private JTextField txtDateOfBirth;
  private JTextField txtFullname;
  private JTextField txtEmail;

  private ButtonLink cmdChangePassword;

  private class MyTextField extends JTextField {

    private static final long serialVersionUID = 8519341340688860372L;

    public MyTextField(String message, String s) {
      super(s);
      putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, message);
      setEditable(false);
      setFocusable(false);
    }
  }

  private class MyJButton extends JButton {

    private static final long serialVersionUID = 7200663277241583843L;

    public MyJButton(AvatarIcon icon) {
      setIcon(icon);
      putClientProperty(FlatClientProperties.STYLE, "" + "foreground:#FFFFFF;");
    }
  }

}
