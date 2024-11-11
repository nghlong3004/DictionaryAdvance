package view.dictionary.components;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controller.DictionaryController;
import model.dictionary.Word;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import util.ObjectContainer;
import util.view.ImageUtil;
import view.notifications.Notification;

public class FavoriteForm extends JPanel {

  private static final long serialVersionUID = 1L;
  
  private final DictionaryController dictionaryController = ObjectContainer.getDictionaryController();

  private FavouritePanel favouritePanel;
  
  private JLabel lblNumber;
  
  private List<Word> items;

  public FavoriteForm() {

    JPanel panel = new JPanel();
    JPanel panel_1 = new JPanel();
    
    panel_1.setLayout(new MigLayout("fill"));
    items = new ArrayList<>();
    items = dictionaryController.getFavouriteByEmail();

    lblNumber = new JLabel();

    favouritePanel = new FavouritePanel(items);
    JScrollPane scrollPane = new JScrollPane(favouritePanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.putClientProperty(FlatClientProperties.STYLE, "" + "arc:25;");
    panel_1.add(scrollPane, "grow");
    panelHeader(panel);
    GroupLayout groupLayout = new GroupLayout(this);
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup().addContainerGap()
            .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                .addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 692,
                    Short.MAX_VALUE))
            .addGap(16)));
    groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup().addGap(30)
            .addComponent(panel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE).addGap(17)));
    setLayout(groupLayout);

  }

  private void panelHeader(JPanel panel) {
    panel.setLayout(new MigLayout("fill"));
    lblNumber.setHorizontalAlignment(SwingConstants.CENTER);
    lblNumber.setHorizontalTextPosition(SwingConstants.CENTER);
    JLabel lblDescription = new JLabel("Mục yêu thích");
    JTextField txtSearch = new JTextField();
    txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
    txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON,
        new FlatSVGIcon("image\\search1.svg"));
    txtSearch.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:15;" + "borderWidth:0;" + "focusWidth:0;" + "innerFocusWidth:0;"
            + "margin:5,20,5,20;" + "background:$Toast.background");
    txtSearch.getDocument().addDocumentListener(new DocumentListener() {

      @Override
      public void insertUpdate(DocumentEvent e) {
        repaintStartWithText(txtSearch.getText());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        repaintStartWithText(txtSearch.getText());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {}
    });
    JButton cmdDelete = new JButton();
    cmdDelete.setContentAreaFilled(false);
    cmdDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
    cmdDelete.addActionListener(actionEven -> {
      int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn Xoá tất cả các từ yêu thích?",
          "Xoá từ yêu thích", JOptionPane.YES_NO_OPTION);
      if(choice == JOptionPane.YES_OPTION) {
        items.forEach(item -> {
          dictionaryController.processWord(item.getWord(), false);
        });
        items = new ArrayList<Word>();
        favouritePanel.repaintItem(new ArrayList<Word>());
        
        Notification.getInstance().show(Notification.Type.SUCCESS, "Đã xoá tất cả từ yêu thích");
      }
    });
    cmdDelete.setIcon(new ImageUtil().getAvatarIcon("remove_icon.png", 25, 25, 0));
    panel.add(lblNumber, "dock west, width 35!, gapx 5");
    JPanel smallPanel = new JPanel();
    smallPanel.setLayout(new MigLayout("fill"));
    smallPanel.add(lblDescription, "dock west");
    smallPanel.add(txtSearch, "grow, center, height 30!, gapx 10");
    panel.add(smallPanel, "grow");
    panel.add(cmdDelete, "east, width 30!, height 30!, center, gapx 5");
  }

  protected void repaintStartWithText(String text) {
    List<Word> newItems = new ArrayList<Word>();
    items.forEach(item -> {
      if(item.getWord().startsWith(text)) {
        newItems.add(item);
      }
    });
    favouritePanel.repaintItem(newItems);
  }

  private class FavouritePanel extends JPanel {

    private static final long serialVersionUID = 8577439679825743429L;

    public FavouritePanel(List<Word> items) {
      putClientProperty(FlatClientProperties.STYLE,
          "" + "arc:25;" + "[light]foreground:darken(@background, 3%);"
              + "[dark]foreground:lighten(@background, 3%);"
              + "[light]background:darken(@background, 3%);"
              + "[dark]background:lighten(@background, 3%);");
      setLayout(new MigLayout("wrap 6", ""));
      repaintItem(items);
    }

    public void repaintItem(List<Word> items) {
      int i = 0;
      if(items == null) {
        items = new ArrayList<Word>();
      }
      Component[] componentList = getComponents();
      for (Component component : componentList) {
        remove(component);
      }
      for (Word item : items) {
        ++i;
        String constraints = "grow, gapleft 5, gapright 10, width 200!, height 150!" ;
        add(createItemPanel(i, item.getWord(), item.getMeaning()), constraints);
      }
      lblNumber.setText("<html>" + "<div style='text-align: center;'>" + "<table>"
          + "<tr><td style='text-align: left; font-size:" + 17 + "px; color: " + "#22c55e" + ";'>"
          + items.size() + "</td></tr>" + "</table>" + "</div>" + "</html>");
      revalidate();
      repaint();
    }

    private String htmlText(String text) {
      String html = "<html>" + "<div style='text-align: center;'>" + "<table>"
          + "<tr><td style='text-align: left; font-size:" + 14 + "px; color: " + "#f59e0b" + ";'>"
          + text + "</td></tr>" + "</table>" + "</div>" + "</html>";
      return html;
    }

    private JPanel createItemPanel(int i, String message, String meaning) {
      JPanel itemPanel = new JPanel();
      itemPanel.setLayout(new MigLayout("fill"));
      itemPanel.putClientProperty(FlatClientProperties.STYLE,
          "" + "arc:25;");
      JLabel lblNo = new JLabel(htmlText("No." + i));
      lblNo.setHorizontalTextPosition(SwingConstants.CENTER);
      lblNo.setVerticalTextPosition(SwingConstants.TOP);
      JLabel label = new JLabel("<html>"
          + "<span style='font-size:16px; color:blue;'>" + message + "</span><br>" // item[0] lớn hơn và có màu xanh
          + "<span style='font-size:16px; color:green;'>" + meaning + "</span>"    // item[1] lớn hơn và có màu xanh lá
          + "</html>");
      JScrollPane scrollPane = new JScrollPane(label);
      scrollPane.putClientProperty(FlatClientProperties.STYLE, 
          "border:null;");
      itemPanel.add(lblNo, "gap 5, dock west, width 60!");
      itemPanel.add(scrollPane, "grow");


      return itemPanel;
    }
  }



}

