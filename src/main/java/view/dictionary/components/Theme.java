package view.dictionary.components;

import javax.swing.*;

public class Theme extends JPanel {

  /**
  * 
  */
  private static final long serialVersionUID = -1720764269615481811L;
  private LookAndFeel oldTheme = UIManager.getLookAndFeel();

  public Theme() {
    init();
  }

  private void init() {}

  protected final void formCheck() {
    if (oldTheme != UIManager.getLookAndFeel()) {
      oldTheme = UIManager.getLookAndFeel();
      SwingUtilities.updateComponentTreeUI(this);
    }
  }
}
