package view.dictionary.components.home;

import static util.Constants.ColorApp.*;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import net.miginfocom.swing.MigLayout;
import util.extral.AvatarIcon;

public class Lookup extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private JEditorPane helloPane;
    
    public Lookup(String username) {
    	putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:null");
    	setLayout(new MigLayout("fill", "[grow, 800:900:1000]10[grow, 120:180]", "[][grow][grow]"));
        JPanel left = new JPanel(new MigLayout("", "[grow, 120:150]", "[]20[]"));
        JEditorPane textExplain = new JEditorPane();
        

		String html = "<html>" +
		        "<div style='text-align: center;'>" +
		        "<table>" +
		        "<tr><td style='text-align: left;'>Xin chào</td></tr>" +
		        "<tr><td style='text-align: left; font-size: 24px; color: " + 
		        rndColor() + 
		        ";'>" +
		        username +
		        "</td></tr>" +
		        "</table>" +
		        "</div>" +
		        "</html>";
		helloPane = new JEditorPane("text/html", html);
		helloPane.setFocusable(false);
		helloPane.setEditable(false);
		textExplain.setFocusable(false);
		JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(textExplain);
        scroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:null");
        JScrollBar vscroll = scroll.getVerticalScrollBar();
        vscroll.setUnitIncrement(10);
        vscroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "width:$Menu.scroll.width;"
                + "trackInsets:$Menu.scroll.trackInsets;"
                + "thumbInsets:$Menu.scroll.thumbInsets;"
                + "background:$Menu.ScrollBar.background;"
                + "thumb:$Menu.ScrollBar.thumb");
        left.add(searchPanel(), "grow");
        add(left, "left, top, grow, wrap");
        add(down(), "left, top, wrap");
        add(textExplain, "top, left, grow, height 420:500:600, width 850:1400:1500");
        add(upRight(helloPane), "cell 1 0, center, width 250!, wrap");
        add(right(), "right, span 1 2");
    }
    private String rndColor() {
    	Random rnd = new Random();
    	int index = Math.abs(rnd.nextInt() % colors.length);
    	return colors[index];
    }
    
    public void initSearchForm(String username) {
    	
		String html = "<html>" +
		        "<div style='text-align: center;'>" +
		        "<table>" +
		        "<tr><td style='text-align: left;'>Xin chào</td></tr>" +
		        "<tr><td style='text-align: left; font-size: 24px; color: " + 
		        rndColor() + 
		        ";'>" +
		        username +
		        "</td></tr>" +
		        "</table>" +
		        "</div>" +
		        "</html>";
		helloPane.setText(html);
    }
    
    private JComponent upRight(JEditorPane pane) {
    	JPanel panel = new JPanel(new MigLayout("", "[][]"));
    	JLabel label = new JLabel();
    	AvatarIcon icon = new AvatarIcon(getClass().getResource("/image/user-img.png"), 40, 40, 999);
    	label.setIcon(icon);
    	panel.add(label);
    	panel.add(pane);
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
    
    private JComponent right() {
    	JEditorPane pane1 = new JEditorPane("text/html", "Từ đồng nghĩa");
		JEditorPane pane2 = new JEditorPane("text/html", "Từ trái nghĩa");
		JPanel panel = new JPanel(new MigLayout("", "[grow]", "[grow]20[grow]"));
		
		
		pane1.setFocusable(false);
		pane2.setFocusable(false);
		
		JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(pane1);
        scroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:null");
        JScrollBar vscroll = scroll.getVerticalScrollBar();
        vscroll.setUnitIncrement(10);
        vscroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "width:$Menu.scroll.width;"
                + "trackInsets:$Menu.scroll.trackInsets;"
                + "thumbInsets:$Menu.scroll.thumbInsets;"
                + "background:$Menu.ScrollBar.background;"
                + "thumb:$Menu.ScrollBar.thumb");
		
		panel.add(scroll, "wrap, width 250!, height 270!");
		panel.add(pane2, "width 250!, height 270!");
		
		return panel;
    }
    
    private JComponent searchPanel() {
    	JPanel panel = new JPanel(new MigLayout());
    	JPanel panel1 = new JPanel(new MigLayout("fill", "[][][]"));
    	JTextField text = new JTextField();
    	JButton btnLeft = new JButton("<");
    	JButton btnRight = new JButton(">");
    	JButton btnAV = new JButton("Anh -> Việt");
    	JButton btnVA = new JButton("Việt -> Anh");
    	JButton btnRd = new JButton("Từ ngẫu nhiên");
    	text.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
    	text.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("image\\search1.svg"));
    	text.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;"
                + "background:$Toast.background");
//    	btnLeft = sytle(btnLeft);
//    	btnRight = sytle(btnRight);
//    	btnAV = sytle(btnAV);
//    	btnVA = sytle(btnVA);
//    	btnRd = sytle(btnRd);
    	panel.add(text, "height 40!, width 400!");
    	panel.add(btnLeft, "height 20!, width 30!");
    	panel.add(btnRight, "height 20!, width 30!, wrap");
    	
    	panel1.add(btnAV, "left, height 30!, width 100!");
    	panel1.add(btnVA, "center, height 30!, width 100!");
    	panel1.add(btnRd, "right, height 30!, width 100!");
    	
    	panel.add(panel1, "grow, span, wrap");   	
    	
    	return panel;
    }
    private JComponent down() {
    	JPanel panel = new JPanel(new MigLayout());
    	JPanel panel2 = new JPanel(new MigLayout("fill"));
    	JLabel label = new JLabel();
    	JButton btn1 = new JButton();
    	JButton btn2 = new JButton();
    	JButton btn3 = new JButton();
    	JButton btn4 = new JButton();
    	JButton btn5 = new JButton();
    	btn2 = sytle(btn2);
    	btn3 = sytle(btn3);
    	AvatarIcon icon = new AvatarIcon(getClass().getResource("/image/staroutline_81125 (22).png"), 25, 25, 0);
    	btn3.setIcon(icon);
    	icon = new AvatarIcon(getClass().getResource("/image/copy1.png"), 25, 25, 0);
    	btn1.setIcon(icon);
    	icon = new AvatarIcon(getClass().getResource("/image/flag22.png"), 25, 25, 0);
    	btn2.setIcon(icon);
    	btn1 = sytle(btn1);
    	btn2 = sytle(btn2);
    	btn3 = sytle(btn3);
    	btn4 = sytle(btn4);
    	btn5 = sytle(btn5);
    	icon = new AvatarIcon(getClass().getResource("/image/speaker.png"), 25, 25, 999);
    	btn4.setIcon(icon);
    	btn5.setIcon(icon);
    	JPanel us = new JPanel(new MigLayout());
    	JPanel sp = new JPanel(new MigLayout());
    	final String size = "height 30!, width 30!";
    	JLabel label4 = new JLabel("US");
    	us.add(btn4, size);
    	us.add(label4, size);
    	us.add(btn5, size);
    	us.add(new JLabel("UK"), size);
    	sp.add(btn2, size);
    	sp.add(new JLabel(""), size);
    	sp.add(btn3, size);
    	sp.add(new JLabel(""), size);
    	panel2.add(label, "span 2 2, height 100!, width 100! ");
    	panel2.add(btn1, "left, cell 1 0, " + size);
    	panel2.add(sp, "cell 4 0,right, wrap");
    	panel2.add(us, "cell 4 1, right");
    	panel.add(panel2, "width 850:1400:1500"); 
    	return panel;
    }
    
    
    
}

