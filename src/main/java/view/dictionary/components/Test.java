package view.dictionary.components;

import static util.Constants.ColorApp.*;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import util.extral.AvatarIcon;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Test extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private AvatarIcon icon;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public Test() {
		putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		panel.putClientProperty(FlatClientProperties.STYLE, "" + 
				"arc:20;" + 
				"[light]background:darken(@background, 3%);" +
				"[dark]background:lighten(@background, 3%);");
		panel_1.putClientProperty(FlatClientProperties.STYLE, "" + 
				"arc:20;" + 
				"[light]background:darken(@background, 3%);" +
				"[dark]background:lighten(@background, 3%);");
		
		panel_2.putClientProperty(FlatClientProperties.STYLE, "" + 
				"arc:20;" + 
				"[light]background:darken(@background, 3%);" +
				"[dark]background:lighten(@background, 3%);");
		
		JButton btnNewButton = new JButton("Lưu thay đổi");
		btnNewButton.putClientProperty(FlatClientProperties.STYLE, "" + 
				"[light]foreground:darken(@foreground, 30%);" +
				"[dark]foreground:lighten(@foreground, 10%);");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(460)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
					.addGap(483))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1264, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 601, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 610, GroupLayout.PREFERRED_SIZE)))
					.addGap(57))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(59, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel_4 = new JLabel("Thông tin tài khoản"){
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				Random rnd = new Random();
				int index = Math.abs(rnd.nextInt() % COLORS.length);
				g.setColor(Color.decode(COLORS[index]));
		        g.drawRoundRect(this.getWidth() >> 2, 0, this.getWidth() >> 1, this.getHeight() - 5, 20, 20);
			}
		};
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_5 = new JLabel("Giới tính");
		
		ButtonGroup gr = new ButtonGroup();
		
		JRadioButton rdbtnNam = new JRadioButton("Nam");
		
		JRadioButton rdbtnN = new JRadioButton("Nữ");
		
		JRadioButton rdbtnKhc = new JRadioButton("Khác");
		
		gr.add(rdbtnNam);
		gr.add(rdbtnN);
		gr.add(rdbtnKhc);
		JLabel lblNewLabel_6 = new JLabel("Họ và tên");
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên người dùng");
		textField.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("image\\user.svg"));
		textField.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;"
                + "background:$Panel.background");
		
		JButton btnNewButton_3 = new JButton();
		
		btnNewButton_3.putClientProperty(FlatClientProperties.STYLE, "" + 
				"[light]foreground:darken(@foreground, 30%);" +
				"[dark]foreground:lighten(@foreground, 10%);");
		icon = new AvatarIcon(getClass().getResource("/image/icon-pen.png"), 20, 20, 0);
		btnNewButton_3.setIcon(icon);
		JLabel lblNewLabel_6_1 = new JLabel("Email");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập email");
		textField_1.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("image\\mail svg.svg"));
		textField_1.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;"
                + "background:$Panel.background");
		
		JButton btnNewButton_3_1 = new JButton();
		btnNewButton_3_1.setIcon(icon);
		btnNewButton_3_1.putClientProperty(FlatClientProperties.STYLE, "" + 
				"[light]foreground:darken(@foreground, 30%);" +
				"[dark]foreground:lighten(@foreground, 10%);");
		
		
		JLabel lblNewLabel_6_1_1 = new JLabel("Thay đổi mật khẩu");
		
		JButton btnNewButton_4 = new JButton("Đổi mật khẩu");
		btnNewButton_4.putClientProperty(FlatClientProperties.STYLE, "" + 
				"[light]foreground:darken(@foreground, 30%);" +
				"[dark]foreground:lighten(@foreground, 10%);");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_6_1_1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(415))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(437))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_6_1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(424))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
							.addGap(36)
							.addComponent(rdbtnNam, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
							.addGap(51)
							.addComponent(rdbtnN, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(rdbtnKhc, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
							.addGap(30))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addComponent(btnNewButton_4, GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnNewButton_3_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
							.addGap(73))))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(77)
					.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
					.addGap(91))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnKhc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(rdbtnN, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(rdbtnNam, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(lblNewLabel_6)
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(lblNewLabel_6_1, GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_6_1_1, GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnNewButton_3_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(56))
		);
		panel_1.setLayout(gl_panel_1);
		String htmlText = "<html>" +
                "<head><style>body {font-family: Arial, sans-serif;}</style></head>" +
                "<body>" +
                "<h2>Gỡ bỏ tài khoản</h2>" +
                "<p>Gỡ bỏ tài khoản là <strong>gỡ bỏ</strong> hoàn toàn nội dung có liên quan đến tài khoản này." +
                "Thì <strong>TÔI</strong> sẽ không giải quyết các vấn đề về dữ liệu khi bạn quyết định <em>gỡ tài khoản</em>.</p>" +
                "</body>" +
                "</html>";
		JLabel lblNewLabel_3 = new JLabel(htmlText);
		
		JButton btnNewButton_2 = new JButton("Gỡ bỏ tài khoản");
		btnNewButton_2.putClientProperty(FlatClientProperties.STYLE, "" + 
				"[light]foreground:darken(@foreground, 30%);" +
				"[dark]foreground:lighten(@foreground, 10%);");
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 737, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
					.addComponent(btnNewButton_2)
					.addGap(67))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addGap(32)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		htmlText = "<html>" +
                "<head><style>body {font-family: Arial, sans-serif;}</style></head>" +
                "<body>" +
                "<p>&nbsp;<span style=\"font-size:12px\"><strong>Ngày tạo tài khoản</strong></span>"
                + "</p>" +
                "<p>&nbsp;Bạn đã tạo tài khoản ngày " +
                "</p>" +
                "</body>" +
                "</html>";
		JLabel lblNewLabel = new JLabel(htmlText) {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				g.setColor(Color.decode("#facc15"));
		        g.drawRoundRect(0, 0, this.getWidth() - 5, this.getHeight() - 5, 20, 20);
			}
		};
		htmlText = "<html>" +
                "<head><style>body {font-family: Arial, sans-serif;}</style></head>" +
                "<body>" +
                "<p>&nbsp;<span style=\"font-size:12px\"><strong>Số mục yêu thích</strong></span>"
                + "</p>" +
                "<p>&nbsp;Hiện tại bạn có tất cả&nbsp;" +
                "&nbsp;mục yêu thích.</p>" +
                "</body>" +
                "</html>";
		
		JLabel lblSMcYu = new JLabel(htmlText) {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				g.setColor(Color.decode("#facc15"));
		        g.drawRoundRect(0, 0, this.getWidth() - 5, this.getHeight() - 5, 20, 20);
			}
		};
		
		htmlText = "<html>" +
                "<head><style>body {font-family: Arial, sans-serif;}</style></head>" +
                "<body>" +
                "<p>&nbsp;<span style=\"font-size:12px\"><strong>Thời gian sử dụng trong ngày</strong></span>"
                + "</p>" +
                "<p>&nbsp;Hôm nay bạn đã sử dụng từ điển trong&nbsp;" +
                "&nbsp;tiếng&nbsp;" +
                "&nbsp;phút.</p>" +
                "</body>" +
                "</html>";
		
		JLabel lblNewLabel_1 = new JLabel(htmlText) {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				g.setColor(Color.decode("#facc15"));
		        g.drawRoundRect(0, 0, this.getWidth() - 5, this.getHeight() - 5, 20, 20);
			}
		};
		
		JLabel lblNewLabel_2 = new JLabel("Ảnh đại diện");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnNewButton_1 = new JButton("Thay đổi");
		btnNewButton_1.putClientProperty(FlatClientProperties.STYLE, "" + 
				"[light]foreground:darken(@foreground, 30%);" +
				"[dark]foreground:lighten(@foreground, 10%);");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
						.addComponent(lblSMcYu, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(192)
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
					.addGap(183))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(245)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(247))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnNewButton_1)
					.addGap(27)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblSMcYu, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
					.addGap(36))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
}
