package view.login;

import static util.Constants.Image.*;

import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;

import controller.SignInController;
import model.Account;
import model.User;
import net.miginfocom.swing.MigLayout;
import view.DictionaryMainFrame;

public class LoginPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9112353425595838469L;
	// view ---call--> controller
	private final SignInController signInController = new SignInController();
	// info
	private JTextField textUsername;
	private JPasswordField textPassword;
	
	private JCheckBox remember;
	private boolean flagRemember = false;
	
	private JButton clickLogin;
	private ImageIcon icon;
	private LoginWindow loginFrame;
	
	public LoginPanel(LoginWindow loginFrame) {
		super();
		this.loginFrame = loginFrame;
		setting();
	}
	private void setting() {
		setLayout(new MigLayout("fill, insets 20", "[right]", "[center]"));
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
		action();
		panel.add(title);
		panel.add(description);
		
		panel.add(new JLabel("Tài khoản"), "gapy 8");
		panel.add(textUsername);
		
		panel.add(new JLabel("Mật khẩu"), "gapy 8");
		panel.add(textPassword);
		
		panel.add(remember, "grow 0");
		panel.add(clickLogin, "gapy 10");
		panel.add(forgotPass(),"center, grow 0, gapy 10");
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
		JButton buttonFb = new JButton(getIcon(IMAGE_FACEBOOK));
		JButton buttonGg = new JButton(getIcon(IMAGE_GOOGLE));
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
		buttonFb.setFocusPainted(false);
		buttonGg.setFocusPainted(false);
		buttonFb.setBackground(Color.decode("#A9A9A9"));
		buttonGg.setBackground(Color.decode("#A9A9A9"));
		buttonFb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonGg.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(buttonFb);
		panel.add(buttonGg);
		return panel;
	}
	
	private void action() {
		
		remember.addItemListener(e ->{
			if(e.getStateChange() == ItemEvent.SELECTED) {
				flagRemember = !flagRemember;
			}
		});
		
		clickLogin.addActionListener(e -> {
			User user = new User();
			user.name(textUsername.getText());
			user.password(new String(textPassword.getPassword()));
			if(login(user)) {
				new DictionaryMainFrame().setVisible(true);
				if(remember.isSelected()) {
					signInController.saveDataUserCur(user);
				}
				else {
					signInController.saveDataUserCur(null);
				}
				loginFrame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không chính xác!");
			}
		});
		
	}
	private Component forgotPass() {
		MyButton clickForgotP = new MyButton("Quên mật khẩu?", "white", "red", false);
		return clickForgotP;
	}
	
	private Component callSignUp() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
		MyButton clickRegister = new MyButton("Đăng ký ngay", "green", "red", true);
		JLabel label = new JLabel("Bạn chưa có tài khoản ?");
		panel.putClientProperty(FlatClientProperties.STYLE, "" +
															"background:null");
		label.putClientProperty(FlatClientProperties.STYLE, "" +
															"[light]foreground:darken(@foreground, 30%);" +
															"[dark]foreground:lighten(@foreground, 30%);");
		clickRegister.addActionListener(e -> {
	        loginFrame.setOverlay();
	        loginFrame.setEnabled(false);
			new RegisterWindow(this).setVisible(true);
		});
		panel.add(label);
		panel.add(clickRegister);
		return panel;
	}
	
	private void initialization() {
		String name = signInController.getUser().getName();
		String pass = signInController.getUser().getPassword();
		
		textUsername = new JTextField(name);
		
		textPassword = new JPasswordField(pass);
		
		clickLogin = new JButton("Đăng nhập");
		
		remember = new JCheckBox("Nhớ mật khẩu");
		if(name != null) {
			remember.setSelected(true);
		}
		
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
	public LoginWindow getLoginFrame() {
		return this.loginFrame;
	}
	public boolean login(User user) {
		// call check user
		return signInController.login(user);
	}
	public Account getAccount() {
		return signInController.getAccount();
	}
}