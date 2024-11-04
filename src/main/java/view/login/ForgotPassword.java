package view.login;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import view.login.component.ButtonLink;
import javax.swing.*;

public class ForgotPassword extends JPanel {

  private static final long serialVersionUID = 1017710573385464160L;

  public ForgotPassword() {
    setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 380", "[fill]"));

    JTextArea text = new JTextArea(
        "Vui lòng nhập địa chỉ email bạn đã sử dụng để tạo \n tài khoản của mình và chúng tôi sẽ gửi cho bạn một liên kết để đặt lại \n mật khẩu của bạn.");
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

    JButton cmdSubmit = new ButtonLink("Submit");
    cmdSubmit.putClientProperty(FlatClientProperties.STYLE, "" + "foreground:#FFFFFF;");

    add(cmdSubmit, "gapy 15 15");

    ButtonLink cmdBackLogin = new ButtonLink("Back to login");
    add(cmdBackLogin, "grow 0,al center");

    // event
    cmdBackLogin.addActionListener(actionEvent -> {
      ModalDialog.popModel(Login.ID);
    });
  }
}
