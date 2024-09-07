package view;

import static util.Constants.Image.*;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;

import controller.SignInController;
import model.Account;
import model.User;
import net.miginfocom.swing.MigLayout;

public class SignInPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9112353425595838469L;
	// view ---call--> controller
	private SignInController signInController = new SignInController();
	private Account account;
	// info
	private JTextField textUsername;
	private JPasswordField textPassword;
	private JCheckBox remember;
	private JButton clickLogin;
	private ImageIcon icon;
	private LoginMainFrame loginFrame;
	public SignInPanel(LoginMainFrame loginFrame) {
		super();
		this.loginFrame = loginFrame;
		setting();
	}
	private void setting() {
		setLayout(new MigLayout("fill, insets 30", "[right]", "[center]"));
		initialization();
		JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "fill, 250:280"));
		JLabel title = new JLabel("Đăng nhập");
		JLabel description = new JLabel("Vui lòng đăng nhập vào tài khoản của bạn");
		panel.putClientProperty(FlatClientProperties.STYLE, "" + 
															"arc:20;" + 
															"[light]background:darken(@background, 3%);" +
															"[dark]background:lighten(@background, 3%);");
		panel.putClientProperty(FlatClientProperties.STYLE, "" +
				"background:null");
		title.putClientProperty(FlatClientProperties.STYLE, "" +
															"font:bold +10");
		description.putClientProperty(FlatClientProperties.STYLE, "" + 
															"[light]foreground:darken(@foreground, 30%);" +
															"[dark]foreground:lighten(@foreground, 30%);");
		clickLogin.putClientProperty(FlatClientProperties.STYLE, "" + 
															"[light]foreground:darken(@foreground, 80%);" +
															"[dark]foreground:lighten(@foreground, 60%);");
		textUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên đăng nhập hoặc email");
		textPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập mật khẩu");
		textPassword.putClientProperty(FlatClientProperties.STYLE, "" +
															"showRevealButton:true");
		clickLogin.putClientProperty(FlatClientProperties.STYLE, "" + 
															"borderWidth: 0;" +
															"focusWidth: 0;"  +
															"innerFocusWidth: 0");
		clickLogin.addActionListener(e -> {
			User user = new User();
			user.name(textUsername.getText());
			user.password(new String(textPassword.getPassword()));
			if(login(account,user)) {
				new DictionaryMainFrame().setVisible(true);
				loginFrame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không chính xác!");
			}
		});
		panel.add(title);
		panel.add(description);
		
		panel.add(new JLabel("Tài khoản"), "gapy 8");
		panel.add(textUsername);
		
		panel.add(new JLabel("mật khẩu"), "gapy 8");
		panel.add(textPassword);
		
		panel.add(remember, "grow 0");
		panel.add(clickLogin, "gapy 10");
		panel.add(callSignUp(), "gapy 10");	
		add(panel);
	}
	
	private Component callSignUp() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JButton clickRegister = new JButton("<html><a href = \"#\">Đăng ký ngay</a></html>");
		JLabel label = new JLabel("Bạn chưa có tài khoản ?");
		panel.putClientProperty(FlatClientProperties.STYLE, "" +
															"background:null");
		clickRegister.putClientProperty(FlatClientProperties.STYLE, "" +
															"border:3, 3, 3, 3");
		label.putClientProperty(FlatClientProperties.STYLE, "" +
															"[light]foreground:darken(@foreground, 30%);" +
															"[dark]foreground:lighten(@foreground, 30%);");
		clickRegister.setContentAreaFilled(false);
		clickRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		clickRegister.addActionListener(e -> {
	        loginFrame.setOverlay();
	        loginFrame.setEnabled(false);
			new SignUpFrame(this).setVisible(true);
		});
		panel.add(label);
		panel.add(clickRegister);
		return panel;
	}
	
	private void initialization() {
		textUsername = new JTextField();
		
		textPassword = new JPasswordField();
		
		clickLogin = new JButton("Đăng nhập");
		
		remember = new JCheckBox("Nhớ mật khẩu");
		
		account = new Account();
		
		icon = new ImageIcon(IMAGE_LOGIN_BACKGROUND);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(icon.getImage(), 0, 0, null);
	}
	public void setPassword(String message) {
		if(message != null) {
			this.textPassword.setText(message);
		}
	}
	public void setUsername(String message) {
		if(message != null) {
			this.textUsername.setText(message);
		}
	}
	public LoginMainFrame getLoginFrame() {
		return this.loginFrame;
	}
	public boolean login(Account account, User user) {
		// call check user
		return signInController.login(account, user);
	}
	public Account getAccount() {
		return account;
	}
}

