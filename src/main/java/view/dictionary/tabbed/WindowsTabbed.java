package view.dictionary.tabbed;

import com.formdev.flatlaf.FlatClientProperties;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class WindowsTabbed {

  private static WindowsTabbed instance;
  private JMenuBar menuBar;
  private PanelTabbed panelTabbed;
  private JPanel body;
  private TabbedForm temp;
  private final int LIMIT = 10; // -1 for unlimit
  private final boolean REMOVE_WHEN_LIMIT = false;

  public static WindowsTabbed getInstance() {
    if (instance == null) {
      instance = new WindowsTabbed();
    }
    return instance;
  }

  public void install(JFrame frame, JPanel body) {
    this.body = body;
    menuBar = new JMenuBar();
    menuBar.putClientProperty(FlatClientProperties.STYLE,
        "" + "borderColor:$TitlePane.background;" + "border:0,0,0,0");
    panelTabbed = new PanelTabbed();
    panelTabbed.putClientProperty(FlatClientProperties.STYLE,
        "" + "background:$TitlePane.background");
    menuBar.add(createScroll(panelTabbed));
    frame.setJMenuBar(menuBar);
  }

  public void removeAllTabbed() {
    panelTabbed.removeAll();
    panelTabbed.repaint();
    panelTabbed.revalidate();
    body.removeAll();
    body.revalidate();
    body.repaint();
  }

  public void unshowTab() {
    if (panelTabbed.getComponentCount() == 1) {
      panelTabbed.getComponent(0).setVisible(false);
    }
  }

  private JScrollPane createScroll(Component com) {
    JScrollPane scroll = new JScrollPane(com);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    scroll.getHorizontalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" + "width:0");
    scroll.getHorizontalScrollBar().setUnitIncrement(10);
    scroll.putClientProperty(FlatClientProperties.STYLE, "" + "border:0,0,0,0");
    return scroll;
  }

  public boolean addTab(String title, TabbedForm component) {
    if (LIMIT != -1 && panelTabbed.getComponentCount() >= LIMIT) {
      if (REMOVE_WHEN_LIMIT) {
        panelTabbed.remove(0);
      } else {
        return false;
      }
    }
    TabbedItem item = new TabbedItem(title, component);
    item.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        showForm(item.getComponent());
      }
    });
    panelTabbed.addTab(item);
    if (panelTabbed.getComponentCount() == 1) {
      showForm(component);
      item.setSelected(true);
    }
    return true;
  }

  public String[] getTabName() {
    List<String> list = new ArrayList<>();
    for (Component com : panelTabbed.getComponents()) {
      if (com instanceof TabbedItem) {
        String name = ((TabbedItem) com).getTabbedName();
        list.add(name);
      }
    }
    String[] arr = new String[list.size()];
    list.toArray(arr);
    return arr;
  }

  public int getTabSelectedIndex() {
    for (Component com : panelTabbed.getComponents()) {
      if (com instanceof TabbedItem) {
        if (((TabbedItem) com).isSelected()) {
          return panelTabbed.getComponentZOrder(com);
        }
      }
    }
    return -1;
  }

  public void showForm(TabbedForm component) {
    body.removeAll();
    body.add(component);
    body.repaint();
    body.revalidate();
    panelTabbed.repaint();
    panelTabbed.revalidate();
    component.formOpen();
    temp = component;
  }

  public TabbedForm getTemp() {
    return temp;
  }
}
