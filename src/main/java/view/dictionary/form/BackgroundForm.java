package view.dictionary.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Raven
 */
public class BackgroundForm extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4519974681817033597L;
	private final boolean udecorated;
    
    public BackgroundForm(boolean udecorated) {
        this.udecorated=udecorated;
        init();
    }

    private void init() {
        setOpaque(!udecorated);
        setLayout(new BorderLayout());
        putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5;"
                + "background:$Drawer.background");
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (udecorated) {
            Graphics2D g2 = (Graphics2D) g.create();
            FlatUIUtils.setRenderingHints(g2);
            int arc = UIScale.scale(30);
            g2.setColor(getBackground());
            FlatUIUtils.paintComponentBackground(g2, 0, 0, getWidth(), getHeight(), 0, arc);
            g2.dispose();
        }
        super.paintComponent(g);
    }
}
