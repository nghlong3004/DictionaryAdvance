package view.dictionary.components.home;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;

public class TextTranslator extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -130365791791745994L;
	private JLabel textTop;
	private JLabel textDown;
	
	
	public TextTranslator() {
		setLayout(new MigLayout("wrap, fill", "[grow]1%[grow]"));
		JPanel panel1 = new JPanel(new MigLayout("wrap, fill", "", "[grow]2%[grow]2%[grow]"));
		JPanel panel2 = new JPanel(new MigLayout("fill", "", "3%[grow]1%[grow]3%"));
		panel1.add(textEng(), "growx, height 45%");
		panel1.add(tranEV(), "center, growy, grow, height 5%");
		panel1.add(textViet(), "grow, height 45%");
		
		panel2.add(other(), "growx, top, height 70%, width 100%, wrap");
		panel2.add(new JLabel("k", JLabel.CENTER), "growx, height 25%, width 100%");
		
		add(panel1, "grow,height 100%, width 65%");
		add(panel2, "grow,height 100%, width 30%");
	}
	
	private Component other() {
		JPanel panel = new JPanel(new MigLayout("fill", "", "[grow]2%[grow]"));
		JPanel panel2 = new JPanel(new MigLayout("fill", "[][]20%[][]", ""));
		JButton history = new JButton();
		JLabel historylb = new JLabel("Lịch sử");
		JButton remove = new JButton();
		JLabel removelb = new JLabel("Xóa lịch sử");
		JTable table = new JTable();
		
		panel2.add(history, "height 50%, width 8%!");
		panel2.add(historylb);
		panel2.add(remove, "height 50%, width 8%!");
		panel2.add(removelb);
		
		panel.add(panel2, "grow, height 10%, width 100%, wrap");
		panel.add(table, "grow, height 100%, width 100%");
		
		return panel;
		
	}

	private Component textViet() {
		JPanel panel = new JPanel(new MigLayout("wrap, fill", "[grow][grow]", "[][grow]"));
		JPanel spker = new JPanel(new MigLayout());
		JButton speaker = new JButton("");
		JButton mic = new JButton("mic");
		spker.add(speaker, "height 100%, width 20%!");
		textTop = new JLabel("Vietnamese");
		spker.add(textTop, "height 100%, width 50%!");
		JTextArea text = new JTextArea("");
		speaker.putClientProperty(FlatClientProperties.STYLE, "" +
				"arc:20;" + 
				"[light]foreground:darken(@foreground, 80%);" +
				"[dark]foreground:lighten(@foreground, 60%);");
		mic.putClientProperty(FlatClientProperties.STYLE, "" + 
				"arc:20;" + 
				"[light]foreground:darken(@foreground, 80%);" +
				"[dark]foreground:lighten(@foreground, 60%);");
		text.putClientProperty(FlatClientProperties.STYLE, "" + 
				"[light]foreground:darken(@foreground, 80%);" +
				"[dark]foreground:lighten(@foreground, 60%);" +
				"background:null");
		spker.putClientProperty(FlatClientProperties.STYLE, 
				"background:null");
		panel.add(spker, "cell 0 0, left, height 5%, width 20%!");
		panel.add(mic, "cell 1 0, right, height 5%, width 8%, wrap");
		panel.add(text, "span 2 1, grow, height 90%, width 99%");
		panel.putClientProperty(FlatClientProperties.STYLE, "" + 
				"arc:20;" + 
				"[light]background:darken(@background, 3%);" +
				"[dark]background:lighten(@background, 3%);");
		return panel;
	}

	private Component tranEV() {
		JPanel panel = new JPanel(new MigLayout("fill", "[grow][grow][grow]"));
		JLabel left = new JLabel("EngLish", JLabel.CENTER);
		JButton trans = new JButton("OK");
		JLabel right = new JLabel("VietNamese", JLabel.CENTER);
		trans.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = left.getText();
				left.setText(right.getText());
				textTop.setText(right.getText());
				right.setText(s);
				textDown.setText(s);
			}
		});
		panel.add(left, "growy, width 43%");
		panel.add(trans, "growy, width 5%!");
		panel.add(right, "growy, width 43%");
		panel.putClientProperty(FlatClientProperties.STYLE, "" + 
				"arc:20;" + 
				"[light]background:darken(@background, 3%);" +
				"[dark]background:lighten(@background, 3%);");
		return panel;
	}

	private Component textEng() {
		JPanel panel = new JPanel(new MigLayout("wrap, fill", "[grow][grow]", "[][grow]"));
		JPanel spker = new JPanel(new MigLayout());
		JButton speaker = new JButton("");
		JButton mic = new JButton("mic");
		spker.add(speaker, "height 100%, width 20%!");
		textDown = new JLabel("English");
		spker.add(textDown, "height 100%, width 50%!");
		JTextArea text = new JTextArea("Nhập văn bản cần dịch...");
		speaker.putClientProperty(FlatClientProperties.STYLE, "" +
				"arc:20;" + 
				"[light]foreground:darken(@foreground, 80%);" +
				"[dark]foreground:lighten(@foreground, 60%);");
		mic.putClientProperty(FlatClientProperties.STYLE, "" + 
				"arc:20;" + 
				"[light]foreground:darken(@foreground, 80%);" +
				"[dark]foreground:lighten(@foreground, 60%);");
		text.putClientProperty(FlatClientProperties.STYLE, "" + 
				"[light]foreground:darken(@foreground, 80%);" +
				"[dark]foreground:lighten(@foreground, 60%);" +
				"background:null");
		spker.putClientProperty(FlatClientProperties.STYLE, 
				"background:null");
		panel.add(spker, "cell 0 0, left, height 5%, width 20%!");
		panel.add(mic, "cell 1 0, right, height 5%, width 8%, wrap");
		panel.add(text, "span 2 1, grow, height 90%, width 99%");
		panel.putClientProperty(FlatClientProperties.STYLE, "" + 
				"arc:20;" + 
				"[light]background:darken(@background, 3%);" +
				"[dark]background:lighten(@background, 3%);");
		return panel;
	}
	

}
