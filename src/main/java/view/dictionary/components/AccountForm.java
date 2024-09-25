package view.dictionary.form;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.account.User;
import net.miginfocom.swing.MigLayout;

public class AccountForm extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2206638637030506486L;
	@SuppressWarnings("unused")
	private User user;
	public AccountForm(User user) {
		this.user = user;
		init();
	}

	private void init() {
		setLayout(new MigLayout("debug, fill", "1%[grow]1%[grow]1%", "[grow]1%[grow]"));
		add(inforImage(), "grow, top, height 60%!, width 48%!");
		add(inforText(), "grow, top, height 60%!, width 48%!, wrap");
		
	}

	private Component inforText() {
		JPanel panel = new JPanel(new GridLayout(4, 1));
		JLabel lb1 = new JLabel("OK", JLabel.CENTER);
		JLabel lb2 = new JLabel("OK", JLabel.CENTER);
		JLabel lb3 = new JLabel("OK", JLabel.CENTER);
		JLabel lb4 = new JLabel("OK", JLabel.CENTER);
		panel.add(lb1);
		panel.add(lb2);
		panel.add(lb3);
		panel.add(lb4);
		
		
		return panel;
	}

	private Component inforImage() {
		JPanel panel = new JPanel(new MigLayout("debug, fill", "5%[grow]5%", "5%[grow][grow]1%[grow]1%[grow]1%[grow]5%"));
		JPanel lb1 = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
		JLabel lb2 = new JLabel("OK", JLabel.CENTER);
		JLabel lb3 = new JLabel("OK", JLabel.CENTER);
		JLabel lb4 = new JLabel("OK", JLabel.CENTER);
		JButton btn = new JButton("OK");
		
		lb1.add(btn);
		panel.add(lb1, "cell 0 0, span 1 2, grow, wrap");
		panel.add(lb2, "cell 0 2, grow, wrap");
		panel.add(lb3, "cell 0 3, grow, wrap");
		panel.add(lb4, "cell 0 4, grow, wrap");
		
		
		return panel;
	}
	
}
