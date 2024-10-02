package view.dictionary.tabbed;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import net.miginfocom.swing.MigLayout;

public class PanelTabbed extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = -7946395474012697422L;
  private final ButtonGroup buttonGroup;

  public PanelTabbed() {
    setLayout(new MigLayout("insets 3 300 3 30"));
    buttonGroup = new ButtonGroup();
  }

  public void addTab(JToggleButton item) {
    buttonGroup.add(item);
    add(item);
    repaint();
    revalidate();
  }
}
