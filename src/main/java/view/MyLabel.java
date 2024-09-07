package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyLabel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4741326447926915500L;
	private ImageIcon icon;
	public MyLabel(ImageIcon icon) {
        super(icon);
        this.icon = icon;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder());
        colorBorder = new Color(224, 224, 224);
        setBackground(Color.white);
    }
	public MyLabel() {
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder());
        colorBorder = new Color(224, 224, 224);
        setBackground(Color.white);
    }
	private int radius = 40;
	private boolean isBorder = false;
	private boolean isColor = false;
	private boolean isImage = false;
	private Color colorBorder;
	@Override
	protected void paintComponent(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(isBorder) {
			g2.setColor(colorBorder);
			g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
			g2.setColor(getBackground());
			g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
		}
		else {
			g2.setColor(getBackground());
			g2.fillRoundRect(0, 0, getWidth() - 4, getHeight() - 4, radius, radius);
		}
		if(isImage) {
			g2.drawImage(icon.getImage(), 3, 9, icon.getIconWidth(), icon.getIconHeight(), null);
		}
		super.paintComponent(grphcs);
	}
	public void setIsColor() {
		this.isColor = !this.isColor;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public void setIsBorder(boolean isBorder) {
		this.isBorder = isBorder;
	}
	public void setColorBorder(Color colorBorder) {
		this.colorBorder = colorBorder;
		repaint();
	}
	public void setIsImage(boolean isImage) {
		this.isImage = isImage;
	}
}
