package view.login;

import java.awt.Dimension;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;

public class RegisterWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2405329773259686149L;
	
	private RegisterPanel signUp;
	
	public RegisterWindow(LoginPanel signIn) {
		super();
		setting(signIn);
	}
	private void setting(LoginPanel signIn) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(540, 300));
		setLocationRelativeTo(null);
		initialization(signIn);
		add(signUp);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));
	}
	private void initialization(LoginPanel signIn) {
		signUp = new RegisterPanel(signIn, this);
		
	}
	
}
