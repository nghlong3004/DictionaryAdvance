package util;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class HelpImage {
	public static ImageIcon getIcon(String name) {
		ImageIcon icon = new ImageIcon(name);
		return icon;
	}
	public static Border getBoder() {
		Border lineBorder = BorderFactory.createLineBorder(Color.RED);
		Border emptyBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
		Border compoundBorder = new CompoundBorder(lineBorder, emptyBorder);
		return compoundBorder;
	}
	public static ImageIcon setBoundsIcon(ImageIcon icon) {
		Image image = icon.getImage();
	    int newWidth = image.getWidth(null) / 3 * 2;
	    int newHeight = image.getHeight(null) / 3 * 2;
	    Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
	    ImageIcon scaledIcon = new ImageIcon(scaledImage);
	    return scaledIcon;
	}

}
