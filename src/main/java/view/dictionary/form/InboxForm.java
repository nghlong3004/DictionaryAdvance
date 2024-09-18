package view.dictionary.form;

public class InboxForm extends SimpleForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1271187672212626877L;

	public InboxForm() {
        initComponents();
    }

    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        jButton1.setText("Show Another Form");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("ok");
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(660, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(88, 88, 88))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jButton1)
                .addContainerGap(310, Short.MAX_VALUE))
        );
    }

    private javax.swing.JButton jButton1;
}
