package view.login;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import com.formdev.flatlaf.FlatClientProperties;

public class MyButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4906428158360590901L;
	private String titleLeft = "<html><a href=\"#\" style=\"color:";
	private String titleDecoration = "<html><a href=\"#\" style=\"text-decoration: none; color:";
	private String titleCenter = "\">";
	private String titleRight = "</a></html>";
	public MyButton(String message, String colorMessage, String colorClick, boolean decoration) {
		if(decoration == true) {
			titleLeft = titleDecoration;
		}
		setText(titleLeft + colorMessage + titleCenter + message + titleRight);
		addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	setText(titleLeft + colorClick + titleCenter + message + titleRight);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	setText(titleLeft + colorMessage + titleCenter + message + titleRight);
            }

        });
		putClientProperty(FlatClientProperties.STYLE, "" +
				   "border: 3, 3, 3, 3");
		setContentAreaFilled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}
	
}

