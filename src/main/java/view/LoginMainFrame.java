package view;
import java.awt.Dimension;

import javax.swing.JFrame;
public class LoginMainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4045164794757628053L;
	private SignInPanel signIn;
	private MyPanel overlay;
	public LoginMainFrame(){
		super();
		setting();
		;
	}
	private void setting() {
		setTitle("Login");
		signIn = new SignInPanel(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(851, 512));
		setLocationRelativeTo(null);
		setContentPane(signIn);
		overlay = new MyPanel();
		setGlassPane(overlay);
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
