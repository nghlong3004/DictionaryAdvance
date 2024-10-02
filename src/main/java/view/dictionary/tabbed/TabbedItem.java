package view.dictionary.tabbed;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;

public class TabbedItem extends JToggleButton {

  /**
   * 
   */
  private static final long serialVersionUID = -7335381808019970528L;

  public TabbedForm getComponent() {
    return component;
  }

  public String getTabbedName() {
    return tabbedName;
  }

  private final TabbedForm component;
  private final String tabbedName;

  public TabbedItem(String name, TabbedForm component) {
    this.tabbedName = name;
    this.component = component;
    init(name);
  }

  private void init(String name) {
    setLayout(new MigLayout("", "[]10[]"));
    putClientProperty(FlatClientProperties.STYLE, "" + "borderWidth:0;" + "focusWidth:0;"
        + "innerFocusWidth:0;" + "background:null;" + "arc:10;" + "margin:2,8,2,5");
    add(new JLabel(name));

  }

  @Override
  public void paint(Graphics grphcs) {
    super.paint(grphcs);
    if (!isSelected()
        && getParent().getComponentZOrder(this) != getParent().getComponentCount() - 1) {
      Graphics2D g2 = (Graphics2D) grphcs.create();
      FlatUIUtils.setRenderingHints(g2);
      g2.setColor(UIManager.getColor("Component.borderColor"));
      float m = UIScale.scale(5);
      float s = UIScale.scale(1);
      g2.fill(new Rectangle2D.Double(getWidth() - s, m, s, getHeight() - m * 2));
      g2.dispose();
    }
  }
}
