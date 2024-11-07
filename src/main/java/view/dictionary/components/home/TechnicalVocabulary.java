package view.dictionary.components.home;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controller.DictionaryController;
import model.dictionary.Word;
import util.ObjectContainer;
import view.dictionary.swing.MyButtonAction;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;


public class TechnicalVocabulary extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = -4246021878929953973L;

  private final DictionaryController dictionaryController =
      ObjectContainer.getDictionaryController();

  private final String[] nameSpecialized =
      {" xây dựng", " toán & tin", " vật lý", " y học", " điện lạnh", " hóa học & vật liệu",
          " cơ khí & công trình", " điện tử & viễn thông", " điện", " giao thông & vận tải",
          " đo lường & điều khiển", " ô tô", " môi trường", " dệt may", " thực phẩm"};

  private JSeparator jSeparator1;
  
  private JPanel panel;
  
  private JScrollPane scroll;
  
  private JTable table;
  
  private JLabel lbTitle;
  
  private JTextField txtSearch;
  
  private MyButtonAction btnDelete;
  private MyButtonAction btnEdit;
  
  private JComboBox<Object> comboPosition;
  

  public TechnicalVocabulary() {
    initComponents();
    init();
  }

  private void loadData(String specialized) {
    try {
      DefaultTableModel model = (DefaultTableModel) table.getModel();
      if (table.isEditing()) {
        table.getCellEditor().stopCellEditing();
      }
      model.setRowCount(0);
      List<Word> list = dictionaryController.getSpecializedWord(specialized);
      for (Word word : list) {
        String meaning = word.getMeaning().substring(word.getMeaning().indexOf(';') + 10);
        model.addRow(new Object[] {false, model.getRowCount() + 1, word.getWord(), word.getPronounce(),
            word.getPartOfSpeech() == null ? "Chưa cập nhật" : word.getPartOfSpeech(), meaning.substring(0, meaning.indexOf('<')).replace(">", ""),
            word.getDescription() == null ? "Chưa cập nhật" : word.getDescription(), "Chưa cập nhật"});
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void init() {
    panel.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:25;" + "background:$Table.background");

    table.getTableHeader().putClientProperty(FlatClientProperties.STYLE,
        "" + "height:30;" + "hoverBackground:null;" + "pressedBackground:null;"
            + "separatorColor:$TableHeader.background;" + "font:bold;");

    table.putClientProperty(FlatClientProperties.STYLE,
        "" + "rowHeight:30;" + "showHorizontalLines:true;" + "intercellSpacing:0,1;"
            + "cellFocusColor:$TableHeader.hoverBackground;"
            + "selectionBackground:$TableHeader.hoverBackground;"
            + "selectionForeground:$Table.foreground;");

    scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" + "trackArc:999;"
        + "trackInsets:3,3,3,3;" + "thumbInsets:3,3,3,3;" + "background:$Table.background;");
    lbTitle.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +5;");

    txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
    txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON,
        new FlatSVGIcon("image\\search1.svg"));
    txtSearch.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:15;" + "borderWidth:0;" + "focusWidth:0;" + "innerFocusWidth:0;"
            + "margin:5,20,5,20;" + "background:$Panel.background");
    table.getColumnModel().getColumn(0)
        .setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));
    table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table));

  }

  private void initComponents() {

    panel = new JPanel();
    scroll = new JScrollPane();
    table = new JTable();
    jSeparator1 = new JSeparator();
    btnDelete = new MyButtonAction();
    btnEdit = new MyButtonAction();
    comboPosition = new JComboBox<>();
    lbTitle = new JLabel();
    txtSearch = new JTextField();

    scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

    table.setModel(new DefaultTableModel(new Object[][] {

    }, new String[] {"SELECT", "#", "Từ vựng", "Phiên âm", "Từ loại", "Nghĩa", "Mô tả", "Ví dụ"}) {
      /**
       * 
       */
      private static final long serialVersionUID = -4232228108642530784L;
      Class<?>[] types = new Class<?>[] {Boolean.class, Object.class, Object.class, Object.class,
          Object.class, Object.class, Object.class, Object.class};

      boolean[] canEdit = new boolean[] {true, false, false, true, false, false, false, false};

      @Override
      public Class<?> getColumnClass(int columnIndex) {
        return types[columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
      }
    });
    table.getTableHeader().setReorderingAllowed(false);
    scroll.setViewportView(table);
    if (table.getColumnModel().getColumnCount() > 0) {
      table.getColumnModel().getColumn(0).setMaxWidth(50);
      table.getColumnModel().getColumn(1).setMaxWidth(80);
      table.getColumnModel().getColumn(2).setPreferredWidth(100);
      table.getColumnModel().getColumn(5).setPreferredWidth(50);
      table.getColumnModel().getColumn(6).setPreferredWidth(150);
      table.getColumnModel().getColumn(7).setPreferredWidth(150);
    }
    loadData(nameSpecialized[0]);
    comboPosition.setModel(new DefaultComboBoxModel<Object>(nameSpecialized));
    comboPosition.addActionListener(e -> {
      String item = (String) comboPosition.getSelectedItem();
      if (e.getModifiers() == 16) {
        loadData(item);
      }
    });
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
          int row = table.rowAtPoint(e.getPoint());
          int column = table.columnAtPoint(e.getPoint());
          Object value = table.getValueAt(row, column);
          if (value != null && value.toString().length() > 18) { 
              JOptionPane.showMessageDialog(null, value.toString(), "Chi tiết", JOptionPane.INFORMATION_MESSAGE);
          }
      }
  });
    lbTitle.setText("Từ vựng");
    btnDelete.setText("Delete");
    btnEdit.setText("Edit");
    // btnNew.setText("New");
    // btnEdit.addActionListener(new ActionListener() {
    // public void actionPerformed(ActionEvent evt) {
    // cmdNewActionPerformed(evt);
    // }
    // });

    GroupLayout panelLayout = new GroupLayout(panel);
    panel.setLayout(panelLayout);
    panelLayout.setHorizontalGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addComponent(scroll, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        .addComponent(jSeparator1)
        .addGroup(panelLayout.createSequentialGroup().addGap(20, 20, 20).addGroup(panelLayout
            .createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 150,
                    GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE)
                .addComponent(comboPosition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnDelete,
                    GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
            .addComponent(lbTitle)).addGap(20, 20, 20)));
    panelLayout.setVerticalGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(GroupLayout.Alignment.TRAILING,
            panelLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPosition, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addGap(10, 10, 10)));

    GroupLayout layout = new GroupLayout(this);
    setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout
            .createSequentialGroup().addGap(52, 52, 52).addComponent(panel,
                GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(38, 38, 38)));
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout
            .createSequentialGroup().addGap(47, 47, 47).addComponent(panel,
                GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(40, 40, 40)));

  }
  // private void cmdNewActionPerformed(ActionEvent evt) {
  // Create create = new Create();
  // create.loadData();
  // DefaultOption option = new DefaultOption() {
  // @Override
  // public boolean closeWhenClickOutside() {
  // return true;
  // }
  // };
  // String actions[] = new String[]{"Cancel", "Save"};
  // GlassPanePopup.showPopup(new SimplePopupBorder(create, "Create Employee", actions, (pc, i) -> {
  // if (i == 1) {
  // // save
  // try {
  // //service.create(create.getData());
  // pc.closePopup();
  // Notification.getInstance().show(Notification.Type.SUCCESS, "Employee has been created");
  // } catch (Exception e) {
  // e.printStackTrace();
  // }
  // } else {
  // pc.closePopup();
  // }
  // }), option);
  // }

}
