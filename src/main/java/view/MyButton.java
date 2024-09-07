package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

public class MyButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4906428158360590901L;

	public MyButton(String title) {
		super(title);
		setting();
		click();
	}
	public MyButton(ImageIcon icon) {
		super(icon);
		setting();
		click();
	}
	public MyButton(ImageIcon icon, JPasswordField password) {
		super(icon);
		setting();
		setColor(Color.white);
		colorOver = new Color(224, 224, 224);
		colorClick = Color.white;
		click();
		addActionListener(ae -> {
	         passwordClick = !passwordClick;
	         password.setEchoChar(passwordClick ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
	     });
	}
	
	private void setting() {
		setForeground(Color.black);
		setBorderPainted(false);
		setColor(Color.orange);
		setFocusable(false);
		color = Color.orange;
		colorOver = new Color(245, 208, 82);
		colorClick = new Color(215, 175, 40 );
		borderColor = new Color(219, 232, 249);
		setContentAreaFilled(false);
	}
	private void click() {
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setBackground(colorOver);
				over = true;
			}
			public void mouseExited(MouseEvent e) {
				setBackground(color);
				over = false;
			}
			public void mousePressed(MouseEvent e) {
				setBackground(colorClick);
			}
			public void mouseReleased(MouseEvent e) {
				if(over) {
					setBackground(colorOver);
				}
				else {
					setBackground(color);
				}
			}
		});
	}
	private boolean passwordClick = false;
	private boolean over = true;
	private Color color;
	private Color colorOver;
	private Color colorClick;
	private Color borderColor;
	private int radius = 50;
	
	@Override
	protected void paintComponent(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// paint border
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
		super.paintComponent(grphcs);
	}
	
	public boolean isOver() {
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		setBackground(color);
	}

	public Color getColorOver() {
		return colorOver;
	}

	public void setColorOver(Color colorOver) {
		this.colorOver = colorOver;
	}

	public Color getColorClick() {
		return colorClick;
	}

	public void setColorClick(Color colorClick) {
		this.colorClick = colorClick;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
