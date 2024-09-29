package view.dictionary.components.home;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JEditorPane;

import com.formdev.flatlaf.FlatClientProperties;

public class MyEditorPane extends JEditorPane{
		/**
	 * 
	 */
	private static final long serialVersionUID = -6305691631221643485L;

	    public MyEditorPane() {
	        setOpaque(false);  
	        setFocusable(false);
	    }
	    public MyEditorPane(String a, String b) {
	    	super(a, b);
	    	putClientProperty(FlatClientProperties.STYLE, "" +
					"[light]foreground:darken(@background, 3%);" +
					"[dark]foreground:lighten(@background, 3%);" + 
					"[light]background:darken(@background, 3%);" +
					"[dark]background:lighten(@background, 3%);"
			);
	    	setOpaque(false);
	    	setFocusable(false);
	    }
	    private int cornerRadius = 30;
    	

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.decode("#e7e5e4"));
            //g2.setStroke(new BasicStroke(2));  
            g2.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, cornerRadius, cornerRadius));

            g2.dispose();
        }

}
