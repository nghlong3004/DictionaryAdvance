package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.Timer;

public class MyButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4906428158360590901L;
	private Color startColor = Color.BLACK;
    private Color endColor = Color.WHITE;
    private Timer timer;
    private float progress = 0.0f;
    private int maxSize;
    private Point mousePoint;
    private BufferedImage buffer;

    public MyButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mousePoint = e.getPoint();
                maxSize = Math.max(getWidth(), getHeight());
                buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                startExpansion();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                stopExpansion();
            }
        });
    }

    private void startExpansion() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        progress = 0.0f;
        timer = new Timer(5, new ActionListener() { // Reduced delay for smoother animation
            @Override
            public void actionPerformed(ActionEvent e) {
                progress += 0.1f; // Increased progress step for faster expansion
                if (progress >= 1.0f) {
                    progress = 1.0f;
                    timer.stop();
                }
                repaint();
            }
        });
        timer.start();
    }

    private void stopExpansion() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        progress = 0.0f;
        timer = new Timer(5, new ActionListener() { // Reduced delay for smoother animation
            @Override
            public void actionPerformed(ActionEvent e) {
                progress += 0.1f; // Increased progress step for faster expansion
                if (progress >= 1.0f) {
                    progress = 1.0f;
                    timer.stop();
                }
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the expanding color effect
        if (buffer != null) {
            Graphics2D bufferGraphics = buffer.createGraphics();
            int radius = (int) (maxSize * progress);

            // Draw expanding circle in the buffer
            Color color = interpolateColor(startColor, endColor, progress);
            bufferGraphics.setColor(color);
            bufferGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            bufferGraphics.fillOval(mousePoint.x - radius, mousePoint.y - radius, radius * 2, radius * 2);
            bufferGraphics.dispose();

            g2d.drawImage(buffer, 0, 0, null);
        }

        // Draw the button text on top of the color effect
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        super.paintComponent(g);
        g2d.setColor(getForeground());
        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString(getText(), getWidth() / 2 - fm.stringWidth(getText()) / 2, getHeight() / 2 + fm.getAscent() / 2);
    }

    private Color interpolateColor(Color from, Color to, float progress) {
        int r = (int) (from.getRed() + (to.getRed() - from.getRed()) * progress);
        int g = (int) (from.getGreen() + (to.getGreen() - from.getGreen()) * progress);
        int b = (int) (from.getBlue() + (to.getBlue() - from.getBlue()) * progress);
        return new Color(r, g, b);
    }
	
}
