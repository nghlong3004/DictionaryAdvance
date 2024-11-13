package view.dictionary.components;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Test extends JPanel {

  private static final long serialVersionUID = 1L;

  /**
   * Create the panel.
   */
  public Test() {
    
    JLabel lblNewLabel = new JLabel("New label");
    lblNewLabel.setForeground(Color.RED);
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
    GroupLayout groupLayout = new GroupLayout(this);
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(137)
          .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
          .addContainerGap(176, Short.MAX_VALUE))
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(106)
          .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
          .addContainerGap(125, Short.MAX_VALUE))
    );
    setLayout(groupLayout);

  }
}
