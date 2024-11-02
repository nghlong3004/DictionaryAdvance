package view.login.component;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;

public class ButtonLink extends JButton {

  /**
  * 
  */
  private static final long serialVersionUID = 6508990011264833135L;

  public ButtonLink(String text) {
    super(text);
    putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:15;" + "margin:1,5,1,5;" + "borderWidth:0;" + "focusWidth:0;"
            + "innerFocusWidth:0;" + "foreground:$Component.accentColor;" + "background:null;");
  }

  @Override
  public boolean isDefaultButton() {
    return true;
  }
}
