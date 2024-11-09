package view.dictionary.components.home.specialized;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controller.DictionaryController;
import model.dictionary.Word;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import util.ObjectContainer;
import view.dictionary.swing.MyButtonAction;
import view.notifications.Notification;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;


public class WordSpecialized extends JPanel {

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
  private Map<String, List<Word>> mapWords;
  private String chooseComboBox;
  private JPopupMenu suggestionPopup;
  private int selectedIndex = 0;


  public WordSpecialized() {
    initComponents();
    init();
  }

  private String convertMeaning(String wordMeaning) {
    String meaning = wordMeaning.substring(wordMeaning.indexOf(';') + 10);
    int idx = meaning.indexOf('<');
    return meaning.substring(0, Math.max(0, idx == -1 ? meaning.length() : idx)).replace(">", "");
  }

  private void highlightItem() {
    for (int i = 0; i < suggestionPopup.getComponentCount(); i++) {
      JMenuItem item = (JMenuItem) suggestionPopup.getComponent(i);
      item.setArmed(i == selectedIndex);
    }
  }

  private void showSuggestions(final String input) {
    suggestionPopup.removeAll();
    if (input.isEmpty()) {
      suggestionPopup.setVisible(false);
      return;
    }
    List<Word> keyWords = mapWords.get(chooseComboBox);
    List<Word> valueWords = new ArrayList<Word>();
    keyWords.forEach(word -> {
      if (word.getWord().startsWith(input)) {
        valueWords.add(word);
      }
    });
    if (!valueWords.isEmpty()) {
      for (Word match : valueWords) {
        JMenuItem menuItem = new JMenuItem(match.getWord());
        menuItem.addActionListener(e -> {
          txtSearch.setText(match.getWord());
          String meaning = match.getMeaning();
          detailSearch(meaning);
          suggestionPopup.setVisible(false);
        });

        suggestionPopup.add(menuItem);
        if (suggestionPopup.getComponentCount() > 5) {
          break;
        }
      }
      suggestionPopup.setPopupSize(new Dimension(txtSearch.getWidth(),
          txtSearch.getHeight() * suggestionPopup.getComponentCount()));
      suggestionPopup.show(txtSearch, 0, txtSearch.getHeight());
      suggestionPopup.setVisible(true);
      suggestionPopup.revalidate();
      suggestionPopup.repaint();
      highlightItem();
    } else {
      suggestionPopup.setVisible(false);
    }
  }

  private void loadData(String specialized) {
    try {
      DefaultTableModel model = (DefaultTableModel) table.getModel();
      if (table.isEditing()) {
        table.getCellEditor().stopCellEditing();
      }
      model.setRowCount(0);
      chooseComboBox = specialized;
      List<Word> list = mapWords.get(specialized);
      if (list == null) {
        list = dictionaryController.getSpecializedWord(specialized);
        mapWords.put(specialized, list);
      }
      for (Word word : list) {
        if (word.getPartOfSpeech() == null) {
          word.setPartOfSpeech("Chưa cập nhật");
        }
        if (word.getDescription() == null) {
          word.setDescription("Chưa cập nhật");
        }
        word.setMeaning(convertMeaning(word.getMeaning()));
        model.addRow(new Object[] {false, model.getRowCount() + 1, word.getWord(),
            word.getPronounce(), word.getPartOfSpeech(), word.getMeaning(), word.getDescription(),
            "Chưa cập nhật", word});
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void detailSearch(String value) {
    JOptionPane.showMessageDialog(null, value, "Chi tiết", JOptionPane.INFORMATION_MESSAGE);
  }

  private void cmdEditActionPerformed(ActionEvent e) {
    List<Word> words = getSelectedData();
    if (!words.isEmpty()) {
      if (words.size() == 1) {
        Word word = words.get(0);
        String currentKey = word.getWord();
        Create create = new Create();
        create.loadData(word);
        String action[] = new String[] {"Cancel", "Save"};
        GlassPanePopup.showPopup(new SimplePopupBorder(create, "Edit", action, (p, i) -> {
          if (i == 1) {
            create.addData(word);
            dictionaryController.editWord(word, currentKey);
            mapWords.put((String) comboPosition.getSelectedItem(), null);
            loadData((String) comboPosition.getSelectedItem());
            p.closePopup();
            Notification.getInstance().show(Notification.Type.SUCCESS, "Successfully!");
          } else {
            p.closePopup();
          }
        }));
      } else {
        Notification.getInstance().show(Notification.Type.WARNING,
            "Vui lòng chỉ chọn 1 giá trị để edit");
      }
    } else {
      Notification.getInstance().show(Notification.Type.WARNING, "Vui lòng chọn giá trị để edit");
    }
  }

  private List<Word> getSelectedData() {
    List<Word> words = new ArrayList<Word>();
    for (int i = 0; i < table.getRowCount(); ++i) {
      if ((boolean) table.getValueAt(i, 0)) {
        Word word = (Word) table.getModel().getValueAt(i, 8);
        words.add(word);
      }
    }

    return words;
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
    suggestionPopup = new JPopupMenu();
    mapWords = new TreeMap<String, List<Word>>();
    chooseComboBox = nameSpecialized[0];

    suggestionPopup.putClientProperty(FlatClientProperties.STYLE,
        "" + "[light]foreground:darken(@background, 3%);"
            + "[dark]foreground:lighten(@background, 3%);" + "background:null;"
            + "borderColor:null;" + "borderInsets:5, 5, 5, 5;");
    scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

    table.setModel(new DefaultTableModel(new Object[][] {

    }, new String[] {"SELECT", "#", "Từ vựng", "Phiên âm", "Từ loại", "Nghĩa", "Mô tả", "Ví dụ",
        ""}) {

      private static final long serialVersionUID = -4232228108642530784L;
      Class<?>[] types = new Class<?>[] {Boolean.class, Object.class, Object.class, Object.class,
          Object.class, Object.class, Object.class, Object.class, Object.class};

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
      table.removeColumn(table.getColumnModel().getColumn(8));
    }
    loadData(nameSpecialized[0]);
    comboPosition.setModel(new DefaultComboBoxModel<Object>(nameSpecialized));
    comboPosition.addActionListener(e -> {
      String item = (String) comboPosition.getSelectedItem();
      if (e.getModifiers() == 16) {
        loadData(item);
      }
    });

    suggestionPopup.setFocusable(false);
    txtSearch.getDocument().addDocumentListener(new DocumentListener() {

      @Override
      public void insertUpdate(DocumentEvent e) {
        showSuggestions(convertToInput());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        showSuggestions(convertToInput());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        showSuggestions(convertToInput());
      }
    });

    txtSearch.addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {}

      @Override
      public void keyReleased(KeyEvent e) {}

      @Override
      public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER) {

          if (suggestionPopup.isVisible() && selectedIndex != -1) {
            JMenuItem selectedItem = (JMenuItem) suggestionPopup.getComponent(selectedIndex);
            txtSearch.setText(selectedItem.getText());
            detailSearch(convertToInput());
            suggestionPopup.setVisible(false);
          }
        }
        if (keyCode == KeyEvent.VK_UP) {
          if (suggestionPopup.isVisible()) {
            selectedIndex = (selectedIndex - 1 + suggestionPopup.getComponentCount())
                % suggestionPopup.getComponentCount();
            highlightItem();
          }
        }

        else if (keyCode == KeyEvent.VK_DOWN) {
          if (suggestionPopup.isVisible()) {
            selectedIndex = (selectedIndex + 1) % suggestionPopup.getComponentCount();
            highlightItem();
          }

        }

      }
    });

    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int row = table.rowAtPoint(e.getPoint());
        int column = table.columnAtPoint(e.getPoint());
        Object value = table.getValueAt(row, column);
        if (value != null && value.toString().length() > 18) {
          JOptionPane.showMessageDialog(null, value.toString(), "Chi tiết",
              JOptionPane.INFORMATION_MESSAGE);
        }
      }
    });
    lbTitle.setText("Từ vựng");
    btnDelete.setText("Delete");
    btnEdit.setText("Edit");
    btnDelete.addActionListener(actionEven -> {
      cmdDeleteActionPerformed(actionEven);
    });
    btnEdit.addActionListener(actionEven -> {
      cmdEditActionPerformed(actionEven);
    });



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

  private void cmdDeleteActionPerformed(ActionEvent actionEven) {
    List<Word> words = getSelectedData();
    if (words != null) {
      words.forEach(word -> {
        dictionaryController.deleteWord(word.getWord());
      });
      mapWords.put((String) comboPosition.getSelectedItem(), null);
      loadData((String) comboPosition.getSelectedItem());
    }

  }

  private String convertToInput() {
    if (txtSearch.getText().length() == 1) {
      return txtSearch.getText().substring(0).toUpperCase();
    }
    if (txtSearch.getText().isEmpty()) {
      return "?";
    }
    return txtSearch.getText().substring(0, 1).toUpperCase()
        + txtSearch.getText().substring(1).toLowerCase();
  }

}
