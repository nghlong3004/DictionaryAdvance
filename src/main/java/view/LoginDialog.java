package view;

import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class LoginDialog extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4045164794757628053L;
	
	private SignInView signInView;
	private JLayeredPane layeredPane;
	//private SignUp signUp;
	
	public LoginDialog(){
		super();
		setUndecorated(true);
		
		this.setSize(851, 482);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));
		//this.signInView = new SignInView(this);
		this.layeredPane = new JLayeredPane();
		this.signInView = new SignInView(this);
		//this.signUp = new SignUp();
		layeredPane.add(signInView, JLayeredPane.DEFAULT_LAYER);
		this.add(layeredPane);
	}
	
	
	
	
}
