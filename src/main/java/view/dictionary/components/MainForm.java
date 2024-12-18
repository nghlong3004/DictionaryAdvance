package view.dictionary.components;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.UIScale;
import util.view.NotificationUI;
import view.ViewDictionary;
import view.dictionary.components.home.Lookup;
import view.dictionary.components.home.TextTranslator;
import view.dictionary.components.home.specialized.WordSpecialized;
import view.dictionary.components.profile.ProfileForm;
import view.dictionary.menu.Menu;
import view.dictionary.menu.MenuAction;
import view.login.LoginPanel;
import view.notifications.Notification;
import view.notifications.Notification.Type;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainForm extends JLayeredPane {

  private static final long serialVersionUID = 3011069027149983587L;
  
  private final LoginPanel loginPanel;

  public MainForm(LoginPanel loginPanel) {
    this.loginPanel = loginPanel;
    init(loginPanel);
  }

  private void init(LoginPanel loginPanel) {
    setBorder(new EmptyBorder(5, 5, 5, 5));
    setLayout(new MainFormLayout());
    menu = new Menu(loginPanel.getUser().getFullname(), loginPanel.getUser().getEmail());
    panelBody = new JPanel(new BorderLayout());
    initMenuArrowIcon();
    menuButton.putClientProperty(FlatClientProperties.STYLE, ""
        + "background:$Menu.button.background;" + "arc:999;" + "focusWidth:0;" + "borderWidth:0");
    menuButton.addActionListener((ActionEvent e) -> {
      setMenuFull(!menu.isMenuFull());
    });
    initMenuEvent();
    setLayer(menuButton, JLayeredPane.POPUP_LAYER);
    add(menuButton);
    add(menu);
    add(panelBody);
  }

  public void update() {
    menu.setHeader(loginPanel.getUser().getFullname(), loginPanel.getUser().getEmail());
  }

  @Override
  public void applyComponentOrientation(ComponentOrientation o) {
    super.applyComponentOrientation(o);
    initMenuArrowIcon();
  }

  private void initMenuArrowIcon() {
    if (menuButton == null) {
      menuButton = new JButton();
    }
    String icon = (getComponentOrientation().isLeftToRight()) ? "menu_left.svg" : "menu_right.svg";
    menuButton.setIcon(new FlatSVGIcon("image\\" + icon, 0.8f));
  }

  private void initMenuEvent() {
    menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
      switch (index) {
        case 0:
          switch (subIndex) {
            case 1:
              ViewDictionary.showForm(new Lookup(loginPanel.getUser().getEmail()));
              break;
            case 2:
              ViewDictionary.showForm(new TextTranslator());
              break;
            case 3:
              ViewDictionary.showForm(new WordSpecialized());
              break;
          }
          break;
        case 1:
          Notification.getInstance().clearAll();
          Notification.getInstance().show(Type.INFO, "Lịch sử");
          ViewDictionary.showForm(new HistoryForm());
          break;
        case 2:
          Notification.getInstance().clearAll();
          Notification.getInstance().show(Type.INFO, "Yêu thích");
          ViewDictionary.showForm(new FavoriteForm());
          break;
        case 3:
          Notification.getInstance().clearAll();
          Notification.getInstance().show(Type.INFO, "Mini Game");
          ViewDictionary.showForm(new MiniGameForm());
          break;
        case 4:
          Notification.getInstance().clearAll();
          Notification.getInstance().show(Type.INFO, "Tài khoản");
          ViewDictionary.showForm(new ProfileForm(loginPanel.getUser()));
          break;
        case 5:
          NotificationUI.goodbye();
          ViewDictionary.login();
          break;
      }
    });
  }

  private void setMenuFull(boolean full) {
    String icon;
    if (getComponentOrientation().isLeftToRight()) {
      icon = (full) ? "menu_left.svg" : "menu_right.svg";
    } else {
      icon = (full) ? "menu_right.svg" : "menu_left.svg";
    }
    menuButton.setIcon(new FlatSVGIcon("image\\" + icon, 0.8f));
    menu.setMenuFull(full);
    revalidate();
  }

  public void hideMenu() {
    ViewDictionary.showForm(new Lookup(loginPanel.getUser().getEmail()));
    menu.hideMenuItem();
  }

  public void showForm(Component component) {
    panelBody.removeAll();
    panelBody.add(component);
    panelBody.repaint();
    panelBody.revalidate();
  }

  public void setSelectedMenu(int index, int subIndex) {
    menu.setSelectedMenu(index, subIndex);
  }


  private Menu menu;
  private JPanel panelBody;
  private JButton menuButton;

  private class MainFormLayout implements LayoutManager {

    @Override
    public void addLayoutComponent(String name, Component comp) {}

    @Override
    public void removeLayoutComponent(Component comp) {}

    @Override
    public Dimension preferredLayoutSize(Container parent) {
      synchronized (parent.getTreeLock()) {
        return new Dimension(5, 5);
      }
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
      synchronized (parent.getTreeLock()) {
        return new Dimension(0, 0);
      }
    }

    @Override
    public void layoutContainer(Container parent) {
      synchronized (parent.getTreeLock()) {
        boolean ltr = parent.getComponentOrientation().isLeftToRight();
        Insets insets = UIScale.scale(parent.getInsets());
        int x = insets.left;
        int y = insets.top;
        int width = parent.getWidth() - (insets.left + insets.right);
        int height = parent.getHeight() - (insets.top + insets.bottom);
        int menuWidth =
            UIScale.scale(menu.isMenuFull() ? menu.getMenuMaxWidth() : menu.getMenuMinWidth());
        int menuX = ltr ? x : x + width - menuWidth;
        menu.setBounds(menuX, y, menuWidth, height);
        int menuButtonWidth = menuButton.getPreferredSize().width;
        int menuButtonHeight = menuButton.getPreferredSize().height;
        int menubX;
        if (ltr) {
          menubX = (int) (x + menuWidth - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.3f)));
        } else {
          menubX = (int) (menuX - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.7f)));
        }
        menuButton.setBounds(menubX, UIScale.scale(30), menuButtonWidth, menuButtonHeight);
        int gap = UIScale.scale(5);
        int bodyWidth = width - menuWidth - gap;
        int bodyHeight = height;
        int bodyx = ltr ? (x + menuWidth + gap) : x;
        int bodyy = y;
        panelBody.setBounds(bodyx, bodyy, bodyWidth, bodyHeight);
      }
    }
  }
}
