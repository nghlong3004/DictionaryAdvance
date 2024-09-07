package main;

import javax.swing.SwingUtilities;

import view.LoginDialog;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(() -> 
		new LoginDialog().setVisible(true)
		);
	}

}
