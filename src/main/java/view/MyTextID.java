package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class MyTextID extends JTextField{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4906428158360590901L;
	
	public MyTextID(int size, MyLabel myLabel) {
        super(size);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder());
        addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				myLabel.setColorBorder(new Color(224, 224, 224));
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				myLabel.setColorBorder(Color.orange);
			}
		});
    }
	private int radius = 40;
	
	@Override
	protected void paintComponent(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth() - 4, getHeight() - 4, radius, radius);
		super.paintComponent(grphcs);
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
