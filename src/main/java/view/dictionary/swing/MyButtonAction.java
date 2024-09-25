package view.dictionary.swing;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;

public class MyButtonAction extends JButton {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4311708834606383167L;

	public MyButtonAction() {

        putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;"
                + "background:$Panel.background");
    }
}