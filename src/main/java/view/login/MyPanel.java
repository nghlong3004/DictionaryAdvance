package view.login;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MyPanel extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = -2852130150367959704L;
  public float opacity = 0.0f;
  public Timer fadeInTimer, fadeOutTimer;

  public MyPanel() {
    setOpaque(false);
    fadeInTimer = new Timer(20, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (opacity < 0.8f) {
          opacity += 0.05f;
          repaint();
        } else {
          fadeInTimer.stop();
        }
      }
    });

    fadeOutTimer = new Timer(20, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (opacity > 0.0f) {
          opacity -= 0.05f;
          repaint();
        } else {
          setVisible(false);
          fadeOutTimer.stop();
        }
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(new Color(0, 0, 0, (int) (Math.max(0, opacity) * 255)));
    g.fillRect(0, 0, getWidth(), getHeight());
  }

}
