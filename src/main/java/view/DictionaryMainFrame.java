package view;

import javax.swing.JFrame;

public class DictionaryMainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5317746742223505427L;
	private DictionaryPanel panel;
	public DictionaryMainFrame() {
		this.setTitle("DictionaryAdvance");
		this.setSize(1200, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new DictionaryPanel();
		add(panel);
	}
	
}
