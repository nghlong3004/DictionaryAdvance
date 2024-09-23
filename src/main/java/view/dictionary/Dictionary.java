package view.dictionary;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;

import view.dictionary.form.MainForm;
import view.login.LoginPanel;
import view.login.RegisterPanel;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Dictionary extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6664637977431167041L;
	private static Dictionary app;
    private final MainForm mainForm;
    private final LoginPanel loginPanel;
    private final RegisterPanel registerPanel;
    
    public static void install() {
    	if(app == null) {
    		app = new Dictionary();
    	}
    }

    private Dictionary() {
        initComponents();
        setSize(new Dimension(1368, 768));
        setLocationRelativeTo(null);
        loginPanel = new LoginPanel();
        registerPanel = new RegisterPanel(loginPanel);
        setContentPane(loginPanel);
        mainForm = new MainForm(loginPanel);
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
        //Notifications.getInstance().setJFrame(this);
        setVisible(true);
    }

    public static void showForm(Component component) {
        component.applyComponentOrientation(app.getComponentOrientation());
        app.mainForm.showForm(component);
    }

    public static void open() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.mainForm);
        app.mainForm.update();
        app.mainForm.applyComponentOrientation(app.getComponentOrientation());
        setSelectedMenu(0, 0);
        app.mainForm.hideMenu();
        SwingUtilities.updateComponentTreeUI(app.mainForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }
    
    public static void resigter() {
    	FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.registerPanel);
        app.loginPanel.applyComponentOrientation(app.getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(app.registerPanel);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }
    
    public static void login() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.loginPanel);
        System.out.println(app.loginPanel.getUser().getUsername());
        app.applyComponentOrientation(app.getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(app.loginPanel);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void setSelectedMenu(int index, int subIndex) {
        app.mainForm.setSelectedMenu(index, subIndex);
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        pack();
    }
}
