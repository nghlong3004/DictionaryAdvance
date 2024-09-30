package view.dictionary.components.home;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;
import util.view.ImageUtil;

public class TextTranslator extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -130365791791745994L;
	private JLabel textTop;
	private JLabel textDown;
	private String languageLeft;
	private String languageRight;
	
	
	public TextTranslator() {
		setLayout(new MigLayout("wrap, fill", "[grow]1%[grow]"));
		JPanel panel1 = new JPanel(new MigLayout("wrap, fill", "", "[grow]2%[grow]2%[grow]"));
		JPanel panel2 = new JPanel(new MigLayout("fill", "", "3%[grow]1%[grow]3%"));
		panel1.add(textEng(), "growx, height 45%");
		panel1.add(tranEV(), "center, growy, grow, height 5%");
		panel1.add(textViet(), "grow, height 45%");
		
		panel2.add(other(), "growx, top, height 70%, width 100%, wrap");
		panel2.add(new JLabel(new ImageUtil().getAvatarIcon("study.png", 250, 200, 0)), "growx, height 25%, width 100%");
		
		add(panel1, "grow,height 100%, width 65%");
		add(panel2, "grow,height 100%, width 30%");
	}
	
	private Component other() {
		
		JPanel panel = new JPanel(new MigLayout("fill", "", "[grow]2%[grow]"));
		panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
		JPanel panel2 = new JPanel(new MigLayout("fill", "[grow][grow]20%[grow][grow]", ""));
		JButton history = new JButton();
		history.setIcon(new ImageUtil().getAvatarIcon("history_icon.png", 25, 25, 0));
		history = sytle(history);
		JLabel historylb = new JLabel("Lịch sử");
		JButton remove = new JButton();
		remove.setIcon(new ImageUtil().getAvatarIcon("remove_icon.png", 25, 25, 0));
		remove = sytle(remove);
		JLabel removelb = new JLabel("Xóa lịch sử");
		JTable table = new JTable();
		
		panel2.add(history, "height 50%, width 8%!");
		panel2.add(historylb);
		panel2.add(remove, "height 50%, width 8%!");
		panel2.add(removelb);
		
		table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");

        table.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:30;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        table.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                languageLeft, languageRight
            }
        ) {
            /**
			 * 
			 */
			private static final long serialVersionUID = -4232228108642530784L;
			Class<?>[] types = new Class<?>[] {
			    Object.class,   
			    Object.class   
			};

			boolean[] canEdit = new boolean[] {
			    false, 
			    false
			};

			@Override
			public Class<?> getColumnClass(int columnIndex) {
			    return types[columnIndex];
			}

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMaxWidth(200);
            table.getColumnModel().getColumn(1).setMaxWidth(200);
        }
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table));
		panel.add(panel2, "grow, height 10%, width 100%, wrap");
		panel2.putClientProperty(FlatClientProperties.STYLE, ""
                
                + "background:null");
		panel.add(scroll, "grow, height 100%, width 100%");
		
		return panel;
		
	}
	private JButton sytle(JButton btn) {
    	btn.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "background:null;"
                + "foreground:$Menu.foreground;"
                + "focusWidth:0;"
                + "borderWidth:0;"
                + "innerFocusWidth:0");
    	return btn;
    }

	private Component textViet() {
		JPanel panel = new JPanel(new MigLayout("wrap, fill", "[grow][grow]", "[][grow]"));
		JPanel spker = new JPanel(new MigLayout());
		JButton speaker = new JButton();
		JButton mic = new JButton();
		speaker.setIcon(new ImageUtil().getAvatarIcon("loudspeaker_icon.png", 25, 25, 0));
		speaker = sytle(speaker);
		spker.add(speaker, "height 100%, width 20%!");
		textTop = new JLabel("Vietnamese");
		spker.add(textTop, "height 100%, width 50%!");
		JTextArea text = new JTextArea();
		text.setEditable(false);
		text.setFocusable(false);
		mic.setIcon(new ImageUtil().getAvatarIcon("mic.png", 35, 35, 0));
		mic = sytle(mic);
		text.putClientProperty(FlatClientProperties.STYLE, "" + 
				"[light]foreground:darken(@foreground, 80%);" +
				"[dark]foreground:lighten(@foreground, 60%);" +
				"background:null;" +
				"focusedBackground:null");
		spker.putClientProperty(FlatClientProperties.STYLE, 
				"background:null");
		mic.setVisible(false);
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
		JButton trans = new JButton();
		trans.setIcon(new ImageUtil().getAvatarIcon("reverse_icon.png", 30, 30, 999));
		trans.putClientProperty(FlatClientProperties.STYLE, "" +
							"background:null"
							);
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
		languageLeft = "EngLish";
		languageRight = "Vietnamese";
		JPanel panel = new JPanel(new MigLayout("wrap, fill", "[grow][grow]", "[][grow]"));
		JPanel spker = new JPanel(new MigLayout());
		JButton speaker = new JButton();
		JButton mic = new JButton();
		speaker.setIcon(new ImageUtil().getAvatarIcon("loudspeaker_icon.png", 25, 25, 0));
		speaker = sytle(speaker);
		spker.add(speaker, "height 100%, width 20%!");
		textDown = new JLabel("English");
		spker.add(textDown, "height 100%, width 50%!");
		MyTextArea text = new MyTextArea();
		text.setPlaceholder("Nhập văn bản cần dịch...");
		mic.setIcon(new ImageUtil().getAvatarIcon("mic.png", 35, 35, 0));
		mic = sytle(mic);
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
