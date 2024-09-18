package view.themes;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.EventQueue;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import view.dictionary.form.FormManager;

/**
 *
 * @author Raven
 */
public class ThemesChange extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 86999016271084593L;

	public ThemesChange() {
        init();
    }

    private Icon createIcon(String path) {
        FlatSVGIcon icon = new FlatSVGIcon(path, 0.5f);
        FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
        //colorFilter.add(Color.decode("#969696"), Color.decode("#969696"), Color.decode("#969696"));
        icon.setColorFilter(colorFilter);
        return icon;
    }

    private void init() {
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:null");
        setLayout(new MigLayout("al center", "[fill,200]", "fill"));
        JPanel panel = new JPanel(new MigLayout("fill", "[fill]10[fill]", "fill"));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                );
        JButton buttonLight = new JButton(createIcon("themes\\icon\\light.svg"));
        JButton buttonDark = new JButton(createIcon("themes\\icon\\dark.svg"));
        buttonLight.addActionListener(e -> changeMode(false));
        buttonDark.addActionListener(e -> changeMode(true));
        buttonLight.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "[dark]background:null;"
                + "[light]background:$Drawer.background;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:3,5,3,5");

        buttonDark.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "[dark]background:$Drawer.background;"
                + "[light]background:null;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:3,5,3,5");
        panel.add(buttonDark);
        panel.add(buttonLight);
        add(panel);
    }

    private void changeMode(boolean dark) {
        if (dark != FlatLaf.isLafDark()) {
            if (dark) {
                EventQueue.invokeLater(() -> {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMacDarkLaf.setup();
                    FlatLaf.updateUI();
                    FormManager.updateTempFormUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });
            } else {
                EventQueue.invokeLater(() -> {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMacLightLaf.setup();
                    FlatLaf.updateUI();
                    FormManager.updateTempFormUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });
            }
        }
    }
}
