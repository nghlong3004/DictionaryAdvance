package view.dictionary.form;

import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;

import model.User;
import view.dictionary.menu.Menu;
import view.dictionary.menu.MenuDrawerBuilder;
import view.dictionary.swing.PanelSlider;
import view.dictionary.swing.SimpleTransition;
import view.login.LoginPanel;
import view.login.RegisterPanel;
import util.UndoRedo;

public class FormManager {

    private static FormManager instance;
    private final JFrame frame;

    private final UndoRedo<BaseForm> forms = new UndoRedo<>();

    private boolean menuShowing = true;
    private final PanelSlider panelSlider;
    private final MainForm mainForm;
    private final Menu menu;
    private final boolean undecorated;

    public static void install(JFrame frame, boolean undecorated) {
        instance = new FormManager(frame, undecorated);
    }

    private FormManager(JFrame frame, boolean undecorated) {
        this.frame = frame;
        panelSlider = new PanelSlider();
        mainForm = new MainForm(undecorated);
        menu = new Menu(new MenuDrawerBuilder());
        this.undecorated = undecorated;
    }

    public static void showMenu() {
        instance.menuShowing = true;
        instance.panelSlider.addSlide(instance.menu, SimpleTransition.getShowMenuTransition(instance.menu.getDrawerBuilder().getDrawerWidth(), instance.undecorated));
    }

    public static void showForm(BaseForm component) {
        if (isNewFormAble()) {
            instance.forms.add(component);
            if (instance.menuShowing == true) {
                instance.menuShowing = false;
                Image oldImage = instance.panelSlider.createOldImage();
                instance.mainForm.setForm(component);
                instance.panelSlider.addSlide(instance.mainForm, SimpleTransition.getSwitchFormTransition(oldImage, instance.menu.getDrawerBuilder().getDrawerWidth()));
            } else {
                instance.mainForm.showForm(component);
            }
            instance.forms.getCurrent().formInitAndOpen();
        }
    }

    public static void login(LoginPanel loginPanel) {
        FlatAnimatedLafChange.showSnapshot();
        instance.frame.getContentPane().removeAll();
        instance.frame.getContentPane().add(loginPanel);
        instance.frame.repaint();
        instance.frame.revalidate();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }
    
    public static void register(LoginPanel loginPanel) {
    	FlatAnimatedLafChange.showSnapshot();
        instance.frame.getContentPane().removeAll();
        instance.frame.getContentPane().add(new RegisterPanel(loginPanel));
        instance.frame.repaint();
        instance.frame.revalidate();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void loginSuccessfully(User user) {
        FlatAnimatedLafChange.showSnapshot();
        instance.frame.getContentPane().removeAll();
        instance.frame.getContentPane().add(instance.panelSlider);
        instance.frame.repaint();
        instance.frame.revalidate();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void hideMenu() {
        instance.menuShowing = false;
        instance.panelSlider.addSlide(instance.mainForm, SimpleTransition.getHideMenuTransition(instance.menu.getDrawerBuilder().getDrawerWidth(), instance.undecorated));
    }

    public static void undo() {
        if (isNewFormAble()) {
            if (!instance.menuShowing && instance.forms.isUndoAble()) {
                instance.mainForm.showForm(instance.forms.undo(), SimpleTransition.getDefaultTransition(true));
                instance.forms.getCurrent().formOpen();
            }
        }
    }

    public static void redo() {
        if (isNewFormAble()) {
            if (!instance.menuShowing && instance.forms.isRedoAble()) {
                instance.mainForm.showForm(instance.forms.redo());
                instance.forms.getCurrent().formOpen();
            }
        }
    }

    public static void refresh() {
        if (!instance.menuShowing) {
            instance.forms.getCurrent().formRefresh();
        }
    }

    public static UndoRedo<BaseForm> getForms() {
        return instance.forms;
    }

    public static boolean isNewFormAble() {
        return instance.forms.getCurrent() == null || instance.forms.getCurrent().formClose();
    }

    public static void updateTempFormUI() {
        for (BaseForm f : instance.forms) {
            SwingUtilities.updateComponentTreeUI(f);
        }
    }
}
