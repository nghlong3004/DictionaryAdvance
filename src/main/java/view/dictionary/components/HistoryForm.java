package view.dictionary.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import util.ObjectContainer;
import util.view.ImageUtil;
import view.notifications.Notification;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controller.DictionaryController;
import javax.swing.SwingConstants;

public class HistoryForm extends JPanel {

  private static final long serialVersionUID = 1L;

  private final DictionaryController dictionaryController =
      ObjectContainer.getDictionaryController();

  private JTextField txtSearch;
  private Map<String[], Boolean> mapHistory;
  private HistoryPanel historyPanel;
  private List<String[]> items;

  public HistoryForm() {
    setLayout(new MigLayout("fill", "", ""));
    JPanel panel2 = new JPanel();
    JPanel panel1 = new JPanel();
    panel1.setLayout(new BorderLayout());
    mapHistory = new HashMap<String[], Boolean>();
    items = new ArrayList<>();
    items = dictionaryController.getHistoryByDate(LocalDate.EPOCH);
    Collections.sort(items, (o1, o2) -> {
      if (o1.length != 3) {
        return 0;
      }
      for (int i = 0; i < o1[2].length(); ++i) {
        int a = (int) (o1[2].charAt(i));
        int b = (int) (o2[2].charAt(i));
        if (a - b != 0) {
          return b - a;
        }
      }
      return 0;
    });
    items.forEach(item -> {
      mapHistory.put(item, true);
    });
    historyPanel = new HistoryPanel(items);
    JScrollPane scrollPane = new JScrollPane(historyPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.putClientProperty(FlatClientProperties.STYLE, "" + "arc:25;");

    add(PanelHistory(scrollPane), "gapy 30, grow");

    add(panel2, "east, width 270!");

    txtSearch = new JTextField();
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

    JButton cmdAll = new MyButton("Tất cả", LocalDate.EPOCH);

    JButton cmdToday = new MyButton("Hôm nay", LocalDate.now());

    JButton cmdYesterday = new MyButton("Hôm qua", LocalDate.now().minusDays(1));

    JButton cmdThisWeek = new MyButton("Tuần này",
        LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)));

    JButton cmdThisMonth = new MyButton("Tháng này", LocalDate.now().withDayOfMonth(1));

    JButton cmdOlder = new MyButton("Cũ hơn", LocalDate.EPOCH);

    JLabel lblDescription = new JLabel();
    lblDescription.setIcon(new ImageUtil().getAvatarIcon("catcatcat.png", 230, 200, 0));
    lblDescription.putClientProperty(FlatClientProperties.STYLE, "background:null");

    GroupLayout gl_panel2 = new GroupLayout(panel2);
    gl_panel2.setHorizontalGroup(gl_panel2.createParallelGroup(Alignment.TRAILING)
        .addGroup(gl_panel2.createSequentialGroup().addGap(30).addGroup(gl_panel2
            .createParallelGroup(Alignment.TRAILING)
            .addComponent(cmdToday, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 127,
                Short.MAX_VALUE)
            .addComponent(cmdOlder, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 127,
                Short.MAX_VALUE)
            .addComponent(cmdThisMonth, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 127,
                Short.MAX_VALUE)
            .addComponent(cmdThisWeek, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 127,
                Short.MAX_VALUE)
            .addComponent(cmdYesterday, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 127,
                Short.MAX_VALUE)
            .addComponent(cmdAll, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
            .addComponent(txtSearch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 127,
                Short.MAX_VALUE))
            .addGap(30))
        .addGroup(Alignment.LEADING,
            gl_panel2.createSequentialGroup().addContainerGap()
                .addComponent(lblDescription, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap()));
    gl_panel2.setVerticalGroup(gl_panel2.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panel2.createSequentialGroup().addGap(40)
            .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE)
            .addGap(20).addComponent(cmdAll).addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(cmdToday).addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(cmdYesterday).addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(cmdThisWeek).addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(cmdThisMonth).addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(cmdOlder).addGap(18)
            .addComponent(lblDescription, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
            .addContainerGap()));
    panel2.setLayout(gl_panel2);
  }

  private void repaintStartWithText(String text) {
    List<String[]> items = new ArrayList<String[]>();
    for (Map.Entry<String[], Boolean> entry : mapHistory.entrySet()) {
      if (entry.getKey()[0].contains(text)) {
        items.add(entry.getKey());
      }
    }
    historyPanel.repaintItem(items);
  }

  private JLabel lblCmd;

  private String textLblCmd(int size, String message) {
    String htmlText = "<html>" + "<div style='text-align: center;'>" + "<table>"
        + "<tr><td style='text-align: left; font-size:" + size + "px; color: " + "#9333ea" + ";'>"
        + message + "</td></tr>" + "</table>" + "</div>" + "</html>";
    return htmlText;
  }

  private JComponent PanelHistory(JComponent items) {

    JPanel panelHistory = new JPanel();
    lblCmd = new JLabel(textLblCmd(17, "Tất cả"));
    lblCmd.setHorizontalAlignment(SwingConstants.CENTER);

    JButton cmdDelete = new JButton("Xoá Lịch sử");
    cmdDelete.setIcon(new ImageUtil().getAvatarIcon("remove_icon.png", 25, 25, 0));
    cmdDelete.setContentAreaFilled(false);
    cmdDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
    cmdDelete.addActionListener(actionEven -> {
      if (isSelectData()) {
        int total = 0;
        for (Map.Entry<String[], Boolean> entry : mapHistory.entrySet()) {
          if (!entry.getValue()) {
            ++total;
          }
        }
        int choose = JOptionPane.showConfirmDialog(this,
            "Bạn có chắc chắn xoá " + total + " từ trong lịch sử không ?", "Xoá lịch sử",
            JOptionPane.YES_NO_OPTION);
        if (choose == JOptionPane.YES_OPTION) {
          List<String[]> keys = new ArrayList<String[]>();
          for (Map.Entry<String[], Boolean> entry : mapHistory.entrySet()) {
            if (entry.getValue()) {
              keys.add(entry.getKey());
            } else {
              dictionaryController.deleteWordHistoryByEmail(entry.getKey()[0]);
            }
          }
          historyPanel.repaintItem(keys);
          Notification.getInstance().show(Notification.Type.SUCCESS, "Xoá thành công");
        }
      } else {
        Notification.getInstance().show(Notification.Type.ERROR, "Vui lòng chọn từ để xoá");
      }
    });
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout("fill"));
    panel.add(items, "grow");
    GroupLayout groupLayout = new GroupLayout(panelHistory);
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
        Alignment.TRAILING,
        groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
            .createParallelGroup(Alignment.TRAILING)
            .addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
            .addGroup(groupLayout.createSequentialGroup()
                .addComponent(lblCmd, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 546, Short.MAX_VALUE)
                .addComponent(cmdDelete)))
            .addContainerGap()));
    groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup().addContainerGap()
            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(cmdDelete)
                .addComponent(lblCmd, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
            .addGap(30).addComponent(panel, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
            .addContainerGap()));
    panelHistory.setLayout(groupLayout);
    return panelHistory;
  }

  private boolean isSelectData() {
    for (Map.Entry<String[], Boolean> entry : mapHistory.entrySet()) {
      if (!entry.getValue()) {
        return true;
      }
    }
    return false;
  }

  private class MyButton extends JButton {
    private static final long serialVersionUID = 6976188127267587025L;

    public MyButton(String title, LocalDate localDate) {
      super(title);
      addActionListener(actionEven -> {
        lblCmd.setText(textLblCmd(17, title));
        items = dictionaryController.getHistoryByDate(localDate);
        if (!mapHistory.isEmpty()) {
          mapHistory.clear();
        }
        items.forEach(item -> {
          mapHistory.put(item, true);
        });
        historyPanel.repaintItem(items);
        Notification.getInstance().show(Notification.Type.INFO, title);
      });
      putClientProperty(FlatClientProperties.STYLE, "arc:100;");
    }

  }

  private class HistoryPanel extends JPanel {

    private static final long serialVersionUID = 8577439679825743429L;

    public HistoryPanel(List<String[]> items) {
      putClientProperty(FlatClientProperties.STYLE,
          "" + "arc:25;" + "[light]foreground:darken(@background, 3%);"
              + "[dark]foreground:lighten(@background, 3%);"
              + "[light]background:darken(@background, 3%);"
              + "[dark]background:lighten(@background, 3%);");
      setLayout(new MigLayout("wrap, height 200, fillx", "[]"));
      repaintItem(items);
    }

    public void repaintItem(List<String[]> items) {
      Component[] componentList = getComponents();
      for (Component component : componentList) {
        remove(component);
      }
      for (String[] item : items) {
        add(createItemPanel(item), "growx, gapleft 5, gapright 10, height 150!, width 700:750");
      }
      revalidate();
      repaint();
    }

    private JPanel createItemPanel(String[] item) {
      JPanel itemPanel = new JPanel();
      itemPanel.putClientProperty(FlatClientProperties.STYLE, "" + "arc:25;");
      itemPanel.setLayout(new MigLayout("height 60!, fill"));
      JCheckBox checkBox = new JCheckBox();
      checkBox.addActionListener(actioneEven -> {
        mapHistory.put(item, !mapHistory.get(item));
      });
      JLabel label = new JLabel("<html>" + "<span style='font-size:12px; color:black;'>"
          + item[2].substring(0, item[2].indexOf(".")) + "</span><br>"
          + "<span style='font-size:16px; color:blue;'>" + item[0] + "</span><br>" // item[0] lớn
                                                                                   // hơn và có màu
                                                                                   // xanh
          + "<span style='font-size:16px; color:green;'>" + item[1] + "</span>" // item[1] lớn hơn
                                                                                // và có màu xanh lá
          + "</html>");

      itemPanel.add(checkBox, "dock west, center");
      itemPanel.add(label, "grow, wrap");

      return itemPanel;
    }
  }

}


