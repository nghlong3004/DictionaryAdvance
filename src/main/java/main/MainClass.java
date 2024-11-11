package main;

import java.awt.Font;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import view.ViewDictionary;


public class MainClass {

  public static void main(String[] args) {
    FlatRobotoFont.install();
    FlatLaf.registerCustomDefaultsSource("themes");
    UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
    FlatDarkLaf.setup();
    SwingUtilities.invokeLater(() -> ViewDictionary.install());
  }

}
