package view.dictionary;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;

import raven.popup.GlassPanePopup;
import view.dictionary.form.BackgroundForm;
import view.dictionary.form.FormManager;
import view.dictionary.form.HomeForm;
import view.login.LoginPanel;

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
        setSize(UIScale.scale(new Dimension(1200, 700)));
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
        FormManager.showForm(new HomeForm());
        FormManager.login(new LoginPanel());
    }
	
}
