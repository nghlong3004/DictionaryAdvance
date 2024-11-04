package view.login;

import java.awt.Cursor;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.formdev.flatlaf.FlatClientProperties;
import controller.UserController;
import model.user.User;
import raven.modal.ModalDialog;
import raven.modal.option.BorderOption;
import raven.modal.option.Option;
import util.ObjectContainer;


public class LoginPanel extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 9112353425595838469L;
  private final UserController userController = ObjectContainer.getUserController();
  private ImageIcon icon;

  public LoginPanel() {
    setting();

  }

  private void setting() {
    icon = new ImageIcon("src/main/resources/gif/" + "giphy.gif");
    JButton button = new JButton("Đăng nhập");
    button.setContentAreaFilled(false);
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    button.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold+3;");
    button.addActionListener(actionEvent -> {
      showLogin();
    });
    add(button);

    ModalDialog.getDefaultOption().setOpacity(2f).getBorderOption()
        .setShadow(BorderOption.Shadow.SMALL);
    Option option = ModalDialog.createOption().setCloseOnPressedEscape(false)
        .setAnimationEnabled(true).setOpacity(0.5f).setSliderDuration(600);
    String icon = "image/account.svg";
    ModalDialog.showModal(this, new CustomModalBorder(new Login(), "Login", icon), option,
        Login.ID);
  }

  private void showLogin() {
    Option option = ModalDialog.createOption().setCloseOnPressedEscape(false)
        .setAnimationEnabled(true).setOpacity(0.5f).setSliderDuration(600);
    String icon = "image/account.svg";
    ModalDialog.showModal(this, new CustomModalBorder(new Login(), "Login", icon), option,
        Login.ID);

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    if (ModalDialog.isIdExist(Login.ID) == false) {
      repaint();
    }
  }

  public User getUser() {
    return userController.getUser();
  }

}
