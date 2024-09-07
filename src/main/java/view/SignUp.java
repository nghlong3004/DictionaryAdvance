package view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUp extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5368538115377511397L;
	//private JLabel boxId, boxPassword, boxVerifyPass, boxFullName;
	private JTextField id, fullName;
	private JPasswordField password, verifyPass;
	//private JButton register;
	public SignUp() {
		super();
		// new implement
		newImplement();
		// set layout
		setSize(400, 400);
		setLayout(new GridLayout(2, 2));
		this.add(id);
		this.add(fullName);
		this.add(password);
		this.add(verifyPass);
	}
	private void newImplement() {
//		boxId = new JLabel();
//		boxPassword = new JLabel();
//		boxFullName = new JLabel();
//		boxVerifyPass = new JLabel();
		id = new JTextField(20);
		fullName = new JTextField(20);
		password = new JPasswordField(20);
		verifyPass = new JPasswordField(20);
//		register = new JButton();
		fullName.setText("otoke");
	}
	
	
	
	
	
	

}
