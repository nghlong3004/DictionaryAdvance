package view;

import static util.Constants.Image.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.SignInController;
import model.Account;
import model.User;
import util.HelpImage;

public class SignInView extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9112353425595838469L;
	// view ---call--> controller
	private SignInController signInController = new SignInController();
	private JTextField textUsername;
	private JPasswordField textPassword;
	private MyLabel boxUsername;
	private MyLabel boxPassword;
	// Event storage box
	private MyLabel boxInput;
	// where the ingredients are stored
	private MyLabel container;
	private JButton clickLogin;
	// model 
	private Account account;
	// main view
	private LoginDialog loginDialog;
	private MyButton clickExit;
	private MyButton showHide;
	public SignInView(LoginDialog loginDialog) {
		super();
		// new element
		newElement(loginDialog);
		// setting
		setting();
		// add element into sign in
		addElement();
		
		
	}

	private void newElement(LoginDialog loginDialog) {
		this.clickLogin = new MyButton("Đăng Nhập");
		this.account = new Account();
		this.loginDialog = loginDialog;
		this.boxUsername = new MyLabel(HelpImage.getIcon(IMAGE_USER));
		this.boxPassword = new MyLabel(HelpImage.getIcon(IMAGE_PASS));
		this.boxInput = new MyLabel();
		// initialization container without image
		this.container = new MyLabel(HelpImage.getIcon(IMAGE_LOGIN));
		this.clickExit = new MyButton(HelpImage.getIcon(IMAGE_EXIT_LOGIN));
		this.textUsername = new MyTextID(20, boxUsername);
		this.textPassword = new MyTextPassword(20, boxPassword);
		this.showHide = new MyButton(HelpImage.getIcon(IMAGE_HIDE), textPassword);
	}
	private void setting() {
		// set size in signInView
		this.setBounds(0 , 0 , loginDialog.getWidth(), loginDialog.getHeight());
		// let (x, y) : coordinates in boxInput
		int x = loginDialog.getWidth() - 385, y = loginDialog.getHeight() - 348;
		// (widthElement, heightElement): coordinates in id, pass, boxId, boxPass and click 
		int widthElement = loginDialog.getWidth() - 500;
		int heightElement = 150 * 3 / 11;
		// size in space
		int distanceElement = heightElement / 3;
		//textUsername
		textUsername.setBounds(30, 2, widthElement - 80, heightElement);
		boxUsername.setBounds(0, 0, widthElement, heightElement + 2);
		boxUsername.setBackground(Color.white);
		boxUsername.add(textUsername);
		boxUsername.setIsBorder(true);
		boxUsername.setIsColor();
		boxUsername.setIsImage(true);
		//Password
		textPassword.setBounds(30, 2, widthElement - 80, heightElement);
		boxPassword.setBounds(0, heightElement + distanceElement, widthElement, heightElement + 2);
		boxPassword.setBackground(Color.white);
		boxPassword.add(textPassword);
		boxPassword.setIsBorder(true);
		boxPassword.setIsColor();
		boxPassword.setIsImage(true);
		showHide.setBounds(widthElement - 40, 7, heightElement - 15, heightElement - 15);
		boxPassword.add(showHide);
		//buttonSignIn
		clickLogin.setBounds(0, (heightElement + distanceElement << 1), widthElement, heightElement);
		clickLogin.addActionListener(this);
		// exit
		clickExit.setBounds(getWidth() - 50, 10, 30, 30);
		clickExit.addActionListener(this);
		//container
		boxInput.setBounds(x, y, widthElement, 150);
		container.setBounds(0 , 0 , loginDialog.getWidth(), loginDialog.getHeight());
		boxInput.add(boxUsername);
		boxInput.add(boxPassword);
		boxInput.add(clickLogin);
		boxInput.setBackground(Color.white);
		container.add(boxInput);
		container.add(clickExit);
	}
	
	private void addElement() {
		this.setLayout(null);
		this.add(container);
		
	}
	public boolean login(Account account, User user) {
		// call check user
		return signInController.login(account, user);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(clickExit)) {
			this.loginDialog.dispose();
		}
		else {
			User user = new User();
			user.name(textUsername.getText());
			user.password(new String(textPassword.getPassword()));
			if(login(this.account, user) == true) {
				this.loginDialog.dispose();
				new DictionaryMainFrame().setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(this, "Password is incorrect!s");
			}
		}
	}
	
	public JTextField getTextUsername() {
		return textUsername;
	}
	public JPasswordField getTextPassword() {
		return textPassword;
	}

	
}

