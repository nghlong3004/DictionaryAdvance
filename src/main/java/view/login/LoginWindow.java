package view.login;
import java.awt.Dimension;

import javax.swing.JFrame;
public class LoginWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4045164794757628053L;
	private LoginPanel signIn;
	private MyPanel overlay;
	public LoginWindow(){
		super();
		setting();
	}
	private void setting() {
		setTitle("Login");
		initialization();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(851, 512));
		setLocationRelativeTo(null);
		setContentPane(signIn);
		setGlassPane(overlay);
	}
	private void initialization() {
		signIn = new LoginPanel(this);
		overlay = new MyPanel();
		
	}
	public void setOverlay() {
		if (!overlay.isVisible()) {
            overlay.setVisible(true);
            overlay.opacity = 0.0f;
            overlay.fadeInTimer.start();
        } else {
        	overlay.fadeOutTimer.start();
        }
	}
	
}
