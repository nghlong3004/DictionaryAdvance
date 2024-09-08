package view.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class FadeEffect {
	public float opacity = 0.0f;
    public Timer fadeInTimer, fadeOutTimer;
    
    public FadeEffect(JPanel overlay) {
    	fadeInTimer = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (opacity < 0.8f) {
                    opacity += 0.05f;
                    overlay.repaint();
                } else {
                    fadeInTimer.stop();
                }
            }
        });

        fadeOutTimer = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (opacity > 0.0f) {
                    opacity -= 0.05f;
                    overlay.repaint();
                } else {
                    overlay.setVisible(false);
                    fadeOutTimer.stop();
                }
            }
        });
	}
    
}
