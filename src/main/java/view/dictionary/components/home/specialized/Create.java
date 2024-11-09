package view.dictionary.components.home.specialized;

import model.dictionary.Word;


public class Create extends javax.swing.JPanel {

  private static final long serialVersionUID = 6041900298370110509L;

  public Create() {
    initComponents();
  }

  public void loadData(Word data) {

    if (data != null) {
      txtWord.setText(data.getWord());
      txtPronounce.setText(data.getPronounce());
      txtDescription.setText(data.getDescription());
      txtMeaning.setText(data.getMeaning());
      txtPartOfSpeech.setText(data.getPartOfSpeech());
      txtExample.setText("Chưa cập nhật");
    }
  }

  public void addData(Word data) {
    data.setWord(txtWord.getText());
    data.setPronounce(txtPronounce.getText());
    data.setDescription(txtDescription.getText());
    data.setMeaning(txtMeaning.getText());
    data.setPartOfSpeech(txtPartOfSpeech.getText());
  }

  private void initComponents() {
    jLabel1 = new javax.swing.JLabel();
    txtWord = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    txtPronounce = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    txtPartOfSpeech = new javax.swing.JFormattedTextField();
    jLabel4 = new javax.swing.JLabel();
    txtMeaning = new javax.swing.JTextField();
    jLabel5 = new javax.swing.JLabel();
    txtDescription = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    txtExample = new javax.swing.JTextArea();
    jToolBar1 = new javax.swing.JToolBar();
    cmdBrowse = new javax.swing.JButton();
    cmdDelete = new javax.swing.JButton();

    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel1.setText("Từ vựng");

    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel2.setText("Phiên âm");

    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel3.setText("Từ loại");

    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel4.setText("Nghĩa");

    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel5.setText("Mô tả");

    jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel6.setText("Ví dụ");

    txtExample.setColumns(20);
    txtExample.setLineWrap(true);
    txtExample.setRows(5);
    txtExample.setWrapStyleWord(true);
    jScrollPane1.setViewportView(txtExample);

    jToolBar1.setRollover(true);
    jToolBar1.setOpaque(false);

    cmdBrowse.setText("Browse");
    cmdBrowse.setFocusable(false);
    cmdBrowse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    cmdBrowse.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jToolBar1.add(cmdBrowse);

    cmdDelete.setForeground(new java.awt.Color(255, 0, 0));
    cmdDelete.setText("Delete");
    cmdDelete.setFocusable(false);
    cmdDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    cmdDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

    jToolBar1.add(cmdDelete);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup().addGap(10, 10, 10)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                    javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addComponent(txtDescription, 0, javax.swing.GroupLayout.DEFAULT_SIZE,
                            Short.MAX_VALUE)
                        .addComponent(txtMeaning).addComponent(txtPartOfSpeech)
                        .addComponent(txtPronounce).addComponent(txtWord)))
                .addGroup(layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
            .addGap(50, 50, 50)));
    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup().addGap(30, 30, 30)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1).addComponent(txtWord, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2).addComponent(txtPronounce,
                    javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtPartOfSpeech, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtMeaning, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel5))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                    javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(16, Short.MAX_VALUE)));
  }

  private javax.swing.JButton cmdBrowse;
  private javax.swing.JButton cmdDelete;
  private javax.swing.JTextField txtDescription;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JToolBar jToolBar1;
  private javax.swing.JTextField txtPartOfSpeech;
  private javax.swing.JTextArea txtExample;
  private javax.swing.JTextField txtPronounce;
  private javax.swing.JTextField txtWord;
  private javax.swing.JTextField txtMeaning;

}
