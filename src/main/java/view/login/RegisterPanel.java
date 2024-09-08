package view.login;

import static util.HelpMethod.*;

import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;

public class RegisterPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5368538115377511397L;
	
	private LoginPanel signIn;
	private RegisterWindow window;
	
	private JTextField textEmail;
	private JTextField textUsername;
	private JTextField textFullname;
	
	private JPasswordField textPassword;
	private JPasswordField textRePassword;
	
	private ButtonGroup groupPeople;
	private JRadioButton radioMale;
	private JRadioButton radioFemale;
	private JRadioButton radioDefault;
	private String gender;
	
	private JButton buttonRegister;
	private JButton buttonExit;
	
	public RegisterPanel(LoginPanel signIn, RegisterWindow window) {
		super();
		this.window = window;
		setting(signIn);
	}
	private void setting(LoginPanel signIn) {
		this.signIn = signIn;
		setLayout(new MigLayout("fill, insets 10", "[center]", "[center]"));
		initialization();
		// setup focus to textUsername in first request
		SwingUtilities.invokeLater(() -> textUsername.requestFocusInWindow());
		// new implement
		JPanel panel = new JPanel(new MigLayout("", "fill, 250:280"));
		JPanel panelTitleExit = new JPanel(new MigLayout("wrap 3", "[] 16 [grow] []"));
		JLabel title = new JLabel("Đăng ký");
		JPanel panelGender = new JPanel(new MigLayout("", "fill, 61:70"));
		// put property
		putClientProperty(FlatClientProperties.STYLE, "" +
				"background:null");
		panelGender.putClientProperty(FlatClientProperties.STYLE, "" +
										"background:null");
		panelTitleExit.putClientProperty(FlatClientProperties.STYLE, "" +
				"background:null");
		panel.putClientProperty(FlatClientProperties.STYLE, "" + 
										"arc:20;" + 
										"[light]background:darken(@background, 3%);" +
										"[dark]background:lighten(@background, 3%);");
		title.putClientProperty(FlatClientProperties.STYLE, "" +
										"font:bold +10");
		buttonExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonExit.putClientProperty(FlatClientProperties.STYLE, "" + 
										"[light]foreground:darken(@foreground, 30%);" +
										"[dark]foreground:lighten(@foreground, 10%);");
		buttonRegister.putClientProperty(FlatClientProperties.STYLE, "" + 
										"[light]foreground:darken(@foreground, 30%);" +
										"[dark]foreground:lighten(@foreground, 30%);");
		textPassword.putClientProperty(FlatClientProperties.STYLE, "" +
										"showRevealButton:true");
		textRePassword.putClientProperty(FlatClientProperties.STYLE, "" +
										"showRevealButton:true");
		textUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên đăng nhập hoặc email");
		textFullname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập họ và tên");
		textEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập email");
		textPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập mật khẩu");
		textRePassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập lại mật khẩu");
		buttonRegister.putClientProperty(FlatClientProperties.STYLE, "" + 
										"borderWidth: 0;" +
										"focusWidth: 0;"  +
										"innerFocusWidth: 0");
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
			signIn.getLoginFrame().setOverlay();
			signIn.getLoginFrame().setEnabled(true);
			window.dispose();
		});
		buttonRegister.addActionListener(e -> {
			String username = textUsername.getText();
			String password = new String(textPassword.getPassword());
			String rePassword = new String(textRePassword.getPassword());
			if(!password.equals(rePassword)) {
				JOptionPane.showMessageDialog(this, "Mật khẩu mới không khớp");
			}
			else if(!isValidUsername(username, signIn.getAccount())) {
				JOptionPane.showMessageDialog(this, "Tên đăng nhập không hợp lệ hoặc đã tồn tại");
			}
			else if(isValidUsername(username, signIn.getAccount()) && isValidPassword(password) && !gender.isEmpty()) {
				signIn.setUsername(username);
				signIn.setPassword(password);
				JOptionPane.showMessageDialog(this, "Đăng ký thành công");
				signIn.getLoginFrame().setEnabled(true);
				signIn.getLoginFrame().setOverlay();
				signIn.getAccount().addUser(username, password);
				window.dispose();
			}
			else {
				JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không hợp lệ");
			}
		});
		ItemListener itemListener = e -> {
		    if (e.getStateChange() == ItemEvent.SELECTED) {
		        JRadioButton selected = (JRadioButton) e.getItem();
		        if (selected.equals(radioMale)) {
		            gender = "Male";
		        } else if (selected.equals(radioFemale)) {
		            gender = "Female";
		        } else if (selected.equals(radioDefault)) {
		            gender = "Default";
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
		gender = new String();
		
		buttonRegister = new JButton("Đăng ký");
		buttonExit = new JButton("x");
	}

	
	
	
	

}