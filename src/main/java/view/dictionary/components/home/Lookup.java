package view.dictionary.components.home;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import static util.Constants.ColorApp.COLORS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import util.extral.AvatarIcon;
import util.repository.Google;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Lookup extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField text;
	private JPopupMenu suggestionPopup;
    private List<String> data;
    private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_1_1;
	private JPanel panel_1_2;
    private JEditorPane editorPane;
    JButton btnNewButton;
	JButton btnNewButton_1;
	JButton btnNewButton_1_1;
	JButton btnNewButton_1_2;
	JLabel lblNewLabel;
	JButton btnNewButton_2;
	JButton btnNewButton_2_1;
	JButton btnNewButton_2_2;
	JButton btnNewButton_2_3;
	JButton btnNewButton_2_4;
    private int selectedIndex = 0;

	/**
	 * Create the panel.
	 */
    private void highlightItem() {
        for (int i = 0; i < suggestionPopup.getComponentCount(); i++) {
            JMenuItem item = (JMenuItem) suggestionPopup.getComponent(i);
            item.setArmed(i == selectedIndex);
        }
    }
    private void showSuggestions(JTextField text) {
        String input = text.getText();
        suggestionPopup.removeAll();
        selectedIndex = 0;
        if (input.isEmpty()) {
            suggestionPopup.setVisible(false);
            return;
        }

        List<String> matches = new ArrayList<>();
        for (String item : data) {
            if (item.toLowerCase().startsWith(input.toLowerCase())) {
                matches.add(item);
            }
        }

        if (!matches.isEmpty()) {
            for (String match : matches) {
                JMenuItem menuItem = new JMenuItem(match);
                menuItem.addActionListener(e -> {
                	text.setText(match);
                    suggestionPopup.setVisible(false);
                });
                suggestionPopup.add(menuItem);
            }
            suggestionPopup.setPopupSize(new Dimension(text.getWidth(), text.getHeight() * matches.size()));
            suggestionPopup.show(text, 0, text.getHeight());
            suggestionPopup.setVisible(true);
            highlightItem();
        } else {
            suggestionPopup.setVisible(false);
        }
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
	
	private String convert(String s) {
		
		String html = "<html>" +
		        "<div style='text-align: center;'>" +
		        "<table>" +
		        "<tr><td style='text-align: left; font-size: 12px; color: " + 
		        rndColor() + 
		        ";'>" +
		        s +
		        "</td></tr>" +
		        "</table>" +
		        "</div>" +
		        "</html>";
		return html;
	}
	private String convert(String s, int size) {
		
		String html = "<html>" +
		        "<div style='text-align: center;'>" +
		        "<table>" +
		        "<tr><td style='text-align: left; font-size:" + 24 + "px; color: " + 
		        "white" + 
		        ";'>" +
		        s +
		        "</td></tr>" +
		        "</table>" +
		        "</div>" +
		        "</html>";
		return html;
	}
	public Lookup(String username) {
		putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:null");
		suggestionPopup = new JPopupMenu();
		suggestionPopup.putClientProperty(FlatClientProperties.STYLE, ""
                +
				"[light]foreground:darken(@background, 3%);" +
				"[dark]foreground:lighten(@background, 3%);" +
				"background:null;" +
				"borderColor:null;" +
				"borderInsets:5, 5, 5, 5;"
				);
        data = new ArrayList<String>();
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
		editorPane = new JEditorPane();
		editorPane.setOpaque(false);
		editorPane.setFocusable(false);
		
		
		panel = new JPanel() {
			private static final long serialVersionUID = 4799123527384112685L;
			private int cornerRadius = 30;
	    	

	        @Override
	        protected void paintBorder(Graphics g) {
	            Graphics2D g2 = (Graphics2D) g.create();
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            g2.setColor(Color.decode("#e7e5e4"));
	            //g2.setStroke(new BasicStroke(2));  
	            g2.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, cornerRadius, cornerRadius));

	            g2.dispose();
	        }
		};
		panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"+
				"[light]foreground:darken(@background, 3%);" +
				"[dark]foreground:lighten(@background, 3%);" + 
				"[light]background:darken(@background, 3%);" +
				"[dark]background:lighten(@background, 3%);"
		);
		panel_1 = new JPanel(){
			private static final long serialVersionUID = 4799123527384112685L;
			private int cornerRadius = 30;
	    	

	        @Override
	        protected void paintBorder(Graphics g) {
	            Graphics2D g2 = (Graphics2D) g.create();
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            g2.setColor(Color.decode("#e7e5e4"));
	            //g2.setStroke(new BasicStroke(2));  
	            g2.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, cornerRadius, cornerRadius));

	            g2.dispose();
	        }
		};
		panel_1.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"+
				"[light]foreground:darken(@background, 3%);" +
				"[dark]foreground:lighten(@background, 3%);" + 
				"[light]background:darken(@background, 3%);" +
				"[dark]background:lighten(@background, 3%);"
		);
		
		panel_1_1 = new JPanel(){
			private static final long serialVersionUID = 4799123527384112687L;
			private int cornerRadius = 30;
	    	

	        @Override
	        protected void paintBorder(Graphics g) {
	            Graphics2D g2 = (Graphics2D) g.create();
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            g2.setColor(Color.decode("#e7e5e4"));
	            //g2.setStroke(new BasicStroke(2));  
	            g2.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, cornerRadius, cornerRadius));

	            g2.dispose();
	        }
		};
		panel_1_1.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"+
				"[light]foreground:darken(@background, 3%);" +
				"[dark]foreground:lighten(@background, 3%);" + 
				"[light]background:darken(@background, 3%);" +
				"[dark]background:lighten(@background, 3%);"
		);
		panel_1_2 = new JPanel();
		panel_1_2.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
		);
		
		text = new JTextField();
		text.setColumns(10);
		text.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
    	text.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("image\\search1.svg"));
    	text.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;"
                + "background:$Toast.background");
    	suggestionPopup.setFocusable(false);
    	text.getDocument().addDocumentListener(new DocumentListener() {
			
    		@Override
            public void insertUpdate(DocumentEvent e) {
                showSuggestions(text);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                showSuggestions(text);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                showSuggestions(text);
            }
        });
    	text.addKeyListener( new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
    	    public void keyPressed(KeyEvent e) {
    	        int keyCode = e.getKeyCode();
    	        if(keyCode == KeyEvent.VK_ENTER) {
    	        	handleOK();
    	        }
    	        if (keyCode == KeyEvent.VK_UP) {
    	            if (suggestionPopup.isVisible()) {
    	            	selectedIndex = (selectedIndex - 1 + suggestionPopup.getComponentCount()) % suggestionPopup.getComponentCount();
    	                highlightItem();
    	            }
    	        }
    	        
    	        else if (keyCode == KeyEvent.VK_DOWN) {
    	            if (suggestionPopup.isVisible()) {
    	                selectedIndex = (selectedIndex + 1) % suggestionPopup.getComponentCount();
    	                highlightItem();
    	            }
    	            
    	        }

    	        else if (keyCode == KeyEvent.VK_ENTER) {
    	            if (suggestionPopup.isVisible() && selectedIndex != -1) {
    	                JMenuItem selectedItem = (JMenuItem) suggestionPopup.getComponent(selectedIndex);
    	                text.setText(selectedItem.getText());
    	                suggestionPopup.setVisible(false);
    	            }
    	        }
    	    }
		});
		
		btnNewButton = new JButton("OK");
		
		btnNewButton_1 = new JButton("Anh -> Việt");
		
		btnNewButton_1_1 = new JButton("Từ ngẫu nhiên");
		
		btnNewButton_1_2 = new JButton("Việt -> Anh");
		
		ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(e.getSource() == btnNewButton) {
            		handleOK();
            	}
                if (e.getSource() == btnNewButton_1) {
                	btnNewButton_1.setBackground(Color.decode("#22c55e"));
                	btnNewButton_1_1.setBackground(Color.decode("#4ade80"));
                	btnNewButton_1_2.setBackground(Color.decode("#4ade80"));
                } else if (e.getSource() == btnNewButton_1_1) {
                	btnNewButton_1.setBackground(Color.decode("#4ade80"));
                	btnNewButton_1_1.setBackground(Color.decode("#22c55e"));
                	btnNewButton_1_2.setBackground(Color.decode("#4ade80"));
                } else if (e.getSource() == btnNewButton_1_2) {
                	btnNewButton_1.setBackground(Color.decode("#4ade80"));
                	btnNewButton_1_1.setBackground(Color.decode("#4ade80"));
                	btnNewButton_1_2.setBackground(Color.decode("#22c55e"));
                }
            }
        };

        
        btnNewButton_1.putClientProperty(FlatClientProperties.STYLE, ""
               
                
                + "background:#22c55e;"
                + "focusWidth:0;"
                + "borderWidth:0;"
                + "innerFocusWidth:0");
        btnNewButton_1_1.putClientProperty(FlatClientProperties.STYLE, ""

                
                + "background:#22c55e;"
                + "focusWidth:0;"
                + "borderWidth:0;"
                + "innerFocusWidth:0");
        btnNewButton_1_2.putClientProperty(FlatClientProperties.STYLE, ""
                
                
                + "background:#22c55e;"
                + "focusWidth:0;"
                + "borderWidth:0;"
                + "innerFocusWidth:0");
        btnNewButton.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "focusWidth:0;"
                + "borderWidth:0;"
                + "innerFocusWidth:0");
        
        btnNewButton.addActionListener(actionListener);
        btnNewButton_1.addActionListener(actionListener);
        btnNewButton_1_1.addActionListener(actionListener);
        btnNewButton_1_2.addActionListener(actionListener);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(45))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addGap(48)
									.addComponent(btnNewButton_1_2, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(text, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)
									.addGap(22)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1_2, GroupLayout.PREFERRED_SIZE, 262, Short.MAX_VALUE)
						.addComponent(panel_1_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
					.addGap(22))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(text, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton))
							.addGap(24)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_1)
								.addComponent(btnNewButton_1_1)
								.addComponent(btnNewButton_1_2)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_1_2, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(panel_1_1, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		lblNewLabel = new JLabel();
		btnNewButton_2 = new JButton();
		btnNewButton_2_1 = new JButton();
		btnNewButton_2_2 = new JButton();
		btnNewButton_2_3 = new JButton("US");
		btnNewButton_2_4 = new JButton("UK");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				speek("en");
			}
		});
		btnNewButton_2_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				speek("vi");
			}
		});
		btnNewButton_2 = sytle(btnNewButton_2);
		btnNewButton_2_1 = sytle(btnNewButton_2_1);
		btnNewButton_2_2 = sytle(btnNewButton_2_2);
		btnNewButton_2_3 = sytle(btnNewButton_2_3);
		btnNewButton_2_4 = sytle(btnNewButton_2_4);
		AvatarIcon icon = new AvatarIcon(getClass().getResource("/image/copy1.png"), 25, 25, 0);
		btnNewButton_2.setIcon(icon);
		icon = new AvatarIcon(getClass().getResource("/image/staroutline_81125 (22).png"), 25, 25, 0);
		btnNewButton_2_2.setIcon(icon);
    	icon = new AvatarIcon(getClass().getResource("/image/flag22.png"), 25, 25, 0);
    	btnNewButton_2_1.setIcon(icon);
    	icon = new AvatarIcon(getClass().getResource("/image/speaker.png"), 25, 25, 999);
    	btnNewButton_2_3.setIcon(icon);
    	btnNewButton_2_4.setIcon(icon);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(editorPane, GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 526, Short.MAX_VALUE)
									.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 522, Short.MAX_VALUE)
									.addComponent(btnNewButton_2_3, GroupLayout.PREFERRED_SIZE, 37 << 1, GroupLayout.PREFERRED_SIZE)
									.addGap(2)))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_2_2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_2_4, GroupLayout.PREFERRED_SIZE, 37 << 1, GroupLayout.PREFERRED_SIZE))
							.addGap(59))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnNewButton_2)
									.addComponent(btnNewButton_2_1)
									.addComponent(btnNewButton_2_2))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(68)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_2_3)
								.addComponent(btnNewButton_2_4))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(editorPane, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JLabel lblNewLabel_4_1 = new JLabel(convert("Từ trái nghĩa"));
		JLabel lblNewLabel_4 = new JLabel(convert("Từ đồng nghĩa"));
		
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.putClientProperty(FlatClientProperties.STYLE, "" +
				"[light]foreground:darken(@background, 3%);" +
				"[dark]foreground:lighten(@background, 3%);" + 
				"[light]background:darken(@background, 3%);" +
				"[dark]background:lighten(@background, 3%);"
		);
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1.setHorizontalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea_1, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addComponent(lblNewLabel_4_1, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
							.addGap(131)))
					.addContainerGap())
		);
		gl_panel_1_1.setVerticalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_4_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textArea_1, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_1_1.setLayout(gl_panel_1_1);	
		JTextArea textArea = new JTextArea();
		textArea.putClientProperty(FlatClientProperties.STYLE, "" +
				"[light]foreground:darken(@background, 3%);" +
				"[dark]foreground:lighten(@background, 3%);" + 
				"[light]background:darken(@background, 3%);" +
				"[dark]background:lighten(@background, 3%);"
		);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
							.addGap(131)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel_2 = new JLabel();
		
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		icon = new AvatarIcon(getClass().getResource("/image/user-img.png"), 40, 40, 999);
		lblNewLabel_2.setIcon(icon);
		JLabel lblNewLabel_2_1 = new JLabel();
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		icon = new AvatarIcon(getClass().getResource("/image/cat.png"), 61, 61, 0);
		lblNewLabel_2_1.setIcon(icon);
		
		JLabel lblNewLabel_3 = new JLabel(html);
		GroupLayout gl_panel_1_2 = new GroupLayout(panel_1_2);
		gl_panel_1_2.setHorizontalGroup(
			gl_panel_1_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
		);
		gl_panel_1_2.setVerticalGroup(
			gl_panel_1_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_1_2.setLayout(gl_panel_1_2);
		setLayout(groupLayout);

	}
	
	protected void handleOK() {
		lblNewLabel.setText(convert(text.getText(), 20));
		
	}
	protected void speek(String language){
		new Thread(() -> {
			try {
				Google.speak(language, text.getText());
			} catch (IOException | URISyntaxException er) {
				// TODO Auto-generated catch block
				er.printStackTrace();
			}
		}).start();
		
	}
	private String rndColor() {
    	Random rnd = new Random();
    	int index = Math.abs(rnd.nextInt() % COLORS.length);
    	return COLORS[index];
    }
}
