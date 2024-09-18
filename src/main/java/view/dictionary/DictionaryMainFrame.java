package view.dictionary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.util.UIScale;

import raven.popup.GlassPanePopup;
import view.dictionary.form.BackgroundForm;
import view.dictionary.form.FormManager;
import view.dictionary.form.ReadForm;

public class DictionaryMainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5317746742223505427L;
	
	private final boolean UNDECORATED = !true;

    public DictionaryMainFrame() {
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(UIScale.scale(new Dimension(1366, 768)));
        setLocationRelativeTo(null);
        if (UNDECORATED) {
            setUndecorated(UNDECORATED);
            setBackground(new Color(0, 0, 0, 0));
        } else {
            getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
        }
        setContentPane(new BackgroundForm(UNDECORATED));
        GlassPanePopup.install(this);
        FormManager.install(this, UNDECORATED);
        FormManager.showForm(new ReadForm());
        FormManager.logout();
    }
	public static void main(String[] args) {
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("themes");
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
		FlatMacLightLaf.setup();
		SwingUtilities.invokeLater(() -> 
		new DictionaryMainFrame().setVisible(true)
		);
	}
	
}
