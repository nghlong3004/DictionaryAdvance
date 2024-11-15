package view.dictionary.components.home;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import static util.Constants.ColorApp.COLORS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controller.DictionaryController;
import model.dictionary.Word;
import util.ObjectContainer;
import util.extral.AvatarIcon;
import util.repository.Google;
import view.notifications.Notification;
import view.notifications.Notification.Type;
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

  private DictionaryController dictionaryController = ObjectContainer.getDictionaryController();

  private JTextField txtSearch;

  private JPopupMenu suggestionPopup;

  private List<Word> data;

  private JPanel panel;
  private JPanel panel_1;
  private JPanel panel_1_1;
  private JPanel panel_1_2;

  private JEditorPane PaneExplain;

  private JButton btnOk;
  private JButton btnAnhViet;
  private JButton btnRandom;
  private JButton btnVietAnh;

  private JLabel lbWord;

  private JButton btnCoppy;
  private JButton btnFlag;
  private JButton btnStart;
  private JButton btnSpeakerUs;
  private JButton btnSpeakerVn;

  private JTextArea txtAntonym;
  private JTextArea txtSynonym;
  private Map<String, Boolean> mapFavourite;

  private int selectedIndex = 0;

  private String languageFrom;
  private String languageTo;

  private boolean isFlag = false;
  private boolean isStar = false;

  private void highlightItem() {
    for (int i = 0; i < suggestionPopup.getComponentCount(); i++) {
      JMenuItem item = (JMenuItem) suggestionPopup.getComponent(i);
      item.setArmed(i == selectedIndex);
    }
  }

  private void showSuggestions(JTextField text) {
    String input = text.getText();
    suggestionPopup.removeAll();
    if (input.isEmpty()) {
      suggestionPopup.setVisible(false);
      return;
    }

    input = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();

    data = dictionaryController.searchWordStartWithKey(input, languageFrom, languageTo);

    if (data != null && !data.isEmpty()) {
      for (Word match : data) {
        JMenuItem menuItem = new JMenuItem(match.getWord());
        menuItem.addActionListener(e -> {
          txtSearch.setText(match.getWord());
          handleOK();
          suggestionPopup.setVisible(false);
        });
        suggestionPopup.add(menuItem);
      }
      suggestionPopup.setPopupSize(new Dimension(text.getWidth(), text.getHeight() * data.size()));
      suggestionPopup.show(text, 0, text.getHeight());
      suggestionPopup.setVisible(true);
      suggestionPopup.revalidate();
      suggestionPopup.repaint();
      highlightItem();
    } else {
      suggestionPopup.setVisible(false);
    }
  }

  private JButton sytle(JButton btn) {
    btn.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:999;" + "background:null;" + "foreground:$Menu.foreground;" + "focusWidth:0;"
            + "borderWidth:0;" + "innerFocusWidth:0");
    return btn;
  }

  private String convert(String s) {

    String html = "<html>" + "<div style='text-align: center;'>" + "<table>"
        + "<tr><td style='text-align: left; font-size: 12px; color: " + rndColor() + ";'>" + s
        + "</td></tr>" + "</table>" + "</div>" + "</html>";
    return html;
  }

  private String convert(String s, int size) {

    String html = "<html>" + "<div style='text-align: center;'>" + "<table>"
        + "<tr><td style='text-align: left; font-size:" + 24 + "px; color: " + "#22c55e" + ";'>" + s
        + "</td></tr>" + "</table>" + "</div>" + "</html>";
    return html;
  }

  private String deconvert(String html) {
    String[] arr = html.split(">");
    String s = "";
    for (int i = 0; i < arr[5].length(); ++i) {
      if (arr[5].charAt(i) != '<') {
        s += arr[5].charAt(i);
      } else {
        break;
      }
    }
    return s;
  }

  public Lookup(String username) {
    putClientProperty(FlatClientProperties.STYLE, "" + "arc:25;" + "background:null");
    suggestionPopup = new JPopupMenu();
    suggestionPopup.putClientProperty(FlatClientProperties.STYLE,
        "" + "[light]foreground:darken(@background, 3%);"
            + "[dark]foreground:lighten(@background, 3%);" + "background:null;"
            + "borderColor:null;" + "borderInsets:5, 5, 5, 5;");
    languageFrom = "en";
    languageTo = "vi";
    mapFavourite = new HashMap<String, Boolean>();
    List<Word> words = dictionaryController.getFavouriteByEmail();
    if (words != null) {
      words.forEach(word -> {
        mapFavourite.put(word.getWord(), true);
      });
    }

    String html = "<html>" + "<div style='text-align: center;'>" + "<table>"
        + "<tr><td style='text-align: left;'>Xin chào</td></tr>"
        + "<tr><td style='text-align: left; font-size: 15px; color: " + rndColor() + ";'>"
        + username.substring(0, username.indexOf('@')) + "</td></tr>" + "</table>" + "</div>"
        + "</html>";
    PaneExplain = new JEditorPane("text/html", "");
    PaneExplain.setOpaque(false);
    PaneExplain.setFocusable(false);


    panel = new MyPanel();
    panel_1 = new MyPanel();
    panel_1_1 = new MyPanel();

    panel_1_2 = new JPanel();
    panel_1_2.putClientProperty(FlatClientProperties.STYLE, "" + "arc:25;");

    txtSearch = new JTextField();
    txtSearch.setColumns(10);
    txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
    txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON,
        new FlatSVGIcon("image\\search1.svg"));
    txtSearch.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:15;" + "borderWidth:0;" + "focusWidth:0;" + "innerFocusWidth:0;"
            + "margin:5,20,5,20;" + "background:$Toast.background");
    suggestionPopup.setFocusable(false);
    txtSearch.getDocument().addDocumentListener(new DocumentListener() {

      @Override
      public void insertUpdate(DocumentEvent e) {
        showSuggestions(txtSearch);
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        showSuggestions(txtSearch);
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        showSuggestions(txtSearch);
      }
    });
    txtSearch.addKeyListener(new KeyListener() {

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
        if (keyCode == KeyEvent.VK_ENTER) {
          handleOK();
          if (suggestionPopup.isVisible() && selectedIndex != -1) {
            JMenuItem selectedItem = (JMenuItem) suggestionPopup.getComponent(selectedIndex);
            txtSearch.setText(selectedItem.getText());
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

    btnOk = new JButton("OK");
    // chuyen che do tu Anh sang Viet
    btnAnhViet = new JButton("Anh -> Việt");
    // click se random 1 tu ngau nhien
    btnRandom = new JButton("Từ ngẫu nhiên");
    // chuyen che do tu Viet sang Anh
    btnVietAnh = new JButton("Việt -> Anh");

    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOk) {
          handleOK();
        }
        if (e.getSource() == btnAnhViet) {
          languageFrom = "en";
          languageTo = "vi";
          btnAnhViet.setBackground(Color.decode("#22c55e"));
          btnRandom.setBackground(Color.decode("#4ade80"));
          btnVietAnh.setBackground(Color.decode("#4ade80"));
        } else if (e.getSource() == btnVietAnh) {
          languageFrom = "vi";
          languageTo = "en";
          btnAnhViet.setBackground(Color.decode("#4ade80"));
          btnVietAnh.setBackground(Color.decode("#22c55e"));
          btnRandom.setBackground(Color.decode("#4ade80"));
        } else if (e.getSource() == btnRandom) {
          List<Word> words =
              dictionaryController.searchWordStartWithKey(rndAlphabet(), languageFrom, languageTo);
          while (words == null) {
            words = dictionaryController.searchWordStartWithKey(rndAlphabet(), languageFrom,
                languageTo);
          }
          Word word = words.get(rndRange(words.size()));
          settingWord(word);
          handleWord(convert(word.getWord(), 20),
              "<html>" + word.getMeaning() + "<br>" + word.getDescription() + "</html>",
              word.getSynonym(), word.getAntonym());
        }
      }
    };


    btnAnhViet.putClientProperty(FlatClientProperties.STYLE, ""


        + "background:#22c55e;" + "focusWidth:0;" + "borderWidth:0;" + "innerFocusWidth:0");
    btnRandom.putClientProperty(FlatClientProperties.STYLE, ""


        + "background:#4ade80;" + "focusWidth:0;" + "borderWidth:0;" + "innerFocusWidth:0");
    btnVietAnh.putClientProperty(FlatClientProperties.STYLE, ""


        + "background:#4ade80;" + "focusWidth:0;" + "borderWidth:0;" + "innerFocusWidth:0");
    btnOk.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:999;" + "focusWidth:0;" + "borderWidth:0;" + "innerFocusWidth:0");

    btnOk.addActionListener(actionListener);
    btnAnhViet.addActionListener(actionListener);
    btnRandom.addActionListener(actionListener);
    btnVietAnh.addActionListener(actionListener);

    GroupLayout groupLayout = new GroupLayout(this);
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup().addGap(19)
            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addGap(45))
                .addGroup(groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(btnAnhViet, GroupLayout.PREFERRED_SIZE, 135,
                                GroupLayout.PREFERRED_SIZE)
                            .addGap(48)
                            .addComponent(btnVietAnh, GroupLayout.PREFERRED_SIZE, 135,
                                GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                            .addComponent(btnRandom, GroupLayout.PREFERRED_SIZE, 135,
                                GroupLayout.PREFERRED_SIZE))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 436,
                                GroupLayout.PREFERRED_SIZE)
                            .addGap(22).addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 45,
                                GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(ComponentPlacement.RELATED)))
            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(panel_1_2, GroupLayout.PREFERRED_SIZE, 262, Short.MAX_VALUE)
                .addComponent(panel_1_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 262,
                    Short.MAX_VALUE)
                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
            .addGap(22)));
    groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup().addGap(20)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 38,
                            GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnOk))
                    .addGap(24)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnAnhViet).addComponent(btnRandom).addComponent(btnVietAnh)))
                .addGroup(groupLayout.createSequentialGroup().addContainerGap()
                    .addComponent(panel_1_2, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)))
            .addPreferredGap(ComponentPlacement.RELATED)
            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                .addGroup(groupLayout.createSequentialGroup()
                    .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addGap(18)
                    .addComponent(panel_1_1, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)))
            .addContainerGap()));

    lbWord = new JLabel();
    btnCoppy = new JButton();
    btnFlag = new JButton();
    btnStart = new JButton();
    btnSpeakerUs = new JButton("US");
    btnSpeakerVn = new JButton("VN");
    btnSpeakerUs.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        speak("en");
      }
    });
    btnSpeakerVn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        speak("vi");
      }
    });
    btnCoppy = sytle(btnCoppy);
    btnFlag = sytle(btnFlag);
    btnStart = sytle(btnStart);
    btnSpeakerUs = sytle(btnSpeakerUs);
    btnSpeakerVn = sytle(btnSpeakerVn);
    AvatarIcon icon = new AvatarIcon(getClass().getResource("/image/copy1.png"), 25, 25, 0);
    btnCoppy.setIcon(icon);
    btnCoppy.addActionListener(actionEvent -> {
      if (lbWord.getText() != null && !lbWord.getText().isEmpty()) {
        Notification.getInstance().show(Type.SUCCESS, "Đã Coppy");
        String myString = deconvert(lbWord.getText());
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
      } else {
        Notification.getInstance().show(Type.ERROR, "không có từ để Coppy");
      }
    });
    icon = new AvatarIcon(getClass().getResource("/image/star.png"), 25, 25, 0);
    btnStart.setIcon(icon);
    btnStart.addActionListener(actionEvent -> {
      if (!lbWord.getText().isEmpty()) {
        processStar();
        scheduler.schedule(() -> {
          dictionaryController.processWord(deconvert(lbWord.getText()), isStar);
        }, delayMillis, TimeUnit.MILLISECONDS);
        if (isStar) {
          Notification.getInstance().show(Type.SUCCESS, "Thêm từ vào mục yêu thích");
        } else {
          Notification.getInstance().show(Type.INFO, "Xoá từ khỏi mục yêu thích");
        }
      } else {
        Notification.getInstance().show(Type.ERROR, "Không có từ để thêm");
      }
    });
    icon = new AvatarIcon(getClass().getResource("/image/flag.png"), 30, 30, 0);
    btnFlag.setIcon(icon);
    btnFlag.addActionListener(actionEvent -> {
      if (!lbWord.getText().isEmpty()) {
        processFlag();
        scheduler.schedule(() -> {
          dictionaryController.processWord(deconvert(lbWord.getText()), isFlag);
        }, delayMillis, TimeUnit.MILLISECONDS);
      } else {
        Notification.getInstance().show(Notification.Type.ERROR, "Không có từ để gắn cờ");
      }

    });
    icon = new AvatarIcon(getClass().getResource("/image/speaker.png"), 25, 25, 999);
    btnSpeakerUs.setIcon(icon);
    btnSpeakerVn.setIcon(icon);
    GroupLayout gl_panel = new GroupLayout(panel);
    gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panel.createSequentialGroup().addGap(21)
            .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                .addGroup(
                    gl_panel.createSequentialGroup()
                        .addComponent(PaneExplain, GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                        .addContainerGap())
                .addGroup(
                    gl_panel.createSequentialGroup()
                        .addComponent(lbWord, GroupLayout.PREFERRED_SIZE, 150,
                            GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
                            .addGroup(gl_panel.createSequentialGroup()
                                .addComponent(btnCoppy, GroupLayout.PREFERRED_SIZE, 37,
                                    GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 526, Short.MAX_VALUE)
                                .addComponent(btnFlag, GroupLayout.PREFERRED_SIZE, 37,
                                    GroupLayout.PREFERRED_SIZE))
                            .addGroup(gl_panel.createSequentialGroup()
                                .addPreferredGap(ComponentPlacement.RELATED, 522, Short.MAX_VALUE)
                                .addComponent(btnSpeakerUs, GroupLayout.PREFERRED_SIZE, 37 << 1,
                                    GroupLayout.PREFERRED_SIZE)
                                .addGap(2)))
                        .addGap(18)
                        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                            .addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 37,
                                GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSpeakerVn, GroupLayout.PREFERRED_SIZE, 37 << 1,
                                GroupLayout.PREFERRED_SIZE))
                        .addGap(59)))));
    gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
        .createSequentialGroup()
        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
            .createSequentialGroup().addGap(27)
            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                .addComponent(lbWord, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnCoppy)
                    .addComponent(btnFlag).addComponent(btnStart))))
            .addGroup(gl_panel.createSequentialGroup().addGap(68)
                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                    .addComponent(btnSpeakerUs).addComponent(btnSpeakerVn))))
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addComponent(PaneExplain, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        .addContainerGap()));
    panel.setLayout(gl_panel);

    JLabel lbAntonym = new JLabel(convert("Từ trái nghĩa"));
    JLabel lbSynonym = new JLabel(convert("Từ đồng nghĩa"));

    txtAntonym = new JTextArea();
    txtAntonym.setEditable(false);
    txtAntonym.setFocusable(false);
    txtAntonym.putClientProperty(FlatClientProperties.STYLE,
        "" + "[light]background:darken(@background, 3%);"
            + "[dark]background:lighten(@background, 3%);");
    GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
    gl_panel_1_1.setHorizontalGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panel_1_1.createSequentialGroup().addContainerGap()
            .addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
                .addComponent(txtAntonym, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addGroup(gl_panel_1_1.createSequentialGroup()
                    .addComponent(lbAntonym, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addGap(131)))
            .addContainerGap()));
    gl_panel_1_1.setVerticalGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panel_1_1.createSequentialGroup().addContainerGap()
            .addComponent(lbAntonym, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addComponent(txtAntonym, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
            .addContainerGap()));
    panel_1_1.setLayout(gl_panel_1_1);
    txtSynonym = new JTextArea();
    txtSynonym.setEditable(false);
    txtSynonym.setFocusable(false);
    txtSynonym.putClientProperty(FlatClientProperties.STYLE,
        "" + "[light]background:darken(@background, 3%);"
            + "[dark]background:lighten(@background, 3%);");
    GroupLayout gl_panel_1 = new GroupLayout(panel_1);
    gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
            .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addComponent(txtSynonym, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addComponent(lbSynonym, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addGap(131)))
            .addContainerGap()));
    gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
            .addComponent(lbSynonym, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addComponent(txtSynonym, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
            .addContainerGap()));
    panel_1.setLayout(gl_panel_1);

    JLabel lbUser = new JLabel();

    lbUser.setHorizontalAlignment(SwingConstants.CENTER);
    icon = new AvatarIcon(getClass().getResource("/image/user-img.png"), 40, 40, 999);
    lbUser.setIcon(icon);
    JLabel lbCat = new JLabel();
    lbCat.setHorizontalAlignment(SwingConstants.CENTER);
    icon = new AvatarIcon(getClass().getResource("/image/cat.png"), 61, 61, 0);
    lbCat.setIcon(icon);

    JLabel lbUsename = new JLabel(html);
    GroupLayout gl_panel_1_2 = new GroupLayout(panel_1_2);
    gl_panel_1_2.setHorizontalGroup(gl_panel_1_2.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panel_1_2.createSequentialGroup().addContainerGap().addComponent(lbUser)
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addComponent(lbUsename, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbCat, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
            .addGap(31)));
    gl_panel_1_2.setVerticalGroup(gl_panel_1_2.createParallelGroup(Alignment.TRAILING)
        .addGroup(gl_panel_1_2.createSequentialGroup().addContainerGap().addGroup(gl_panel_1_2
            .createParallelGroup(Alignment.TRAILING)
            .addComponent(lbCat, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
            .addComponent(lbUsename, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 61,
                Short.MAX_VALUE)
            .addComponent(lbUser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
            .addContainerGap()));
    panel_1_2.setLayout(gl_panel_1_2);
    setLayout(groupLayout);

  }

  private void processFlag() {
    if (lbWord.getText().isEmpty()) {
      return;
    }
    String bland = "";
    isFlag = !isFlag;
    if (isFlag) {
      bland = "1";
    }
    final AvatarIcon iconFlag =
        new AvatarIcon(getClass().getResource("/image/flag" + bland + ".png"), 25, 25, 0);
    btnFlag.setIcon(iconFlag);
  }

  private void processStar() {
    if (lbWord.getText().isEmpty()) {
      return;
    }
    String bland = "";
    isStar = !isStar;
    if (isStar) {
      bland = "1";
    }
    final AvatarIcon iconFlag =
        new AvatarIcon(getClass().getResource("/image/star" + bland + ".png"), 25, 25, 0);
    btnStart.setIcon(iconFlag);
  }

  protected void handleOK() {
    if (data != null) {
      for (Word word : data) {
        if (word.getWord().equals(txtSearch.getText())) {
          settingWord(word);
          handleWord(convert(word.getWord(), 20),
              "<html>" + word.getMeaning() + "<br>" + word.getDescription() + "</html>",
              word.getSynonym(), word.getAntonym());
        }
      }
    }
  }

  private void settingWord(Word word) {
    scheduler.schedule(() -> {
      if (mapFavourite.get(word.getWord()) != null && mapFavourite.get(word.getWord())) {
        isStar = false;
      } else {
        isStar = true;
      }
      processStar();

      dictionaryController.saveWordToHistory(deconvert(lbWord.getText()), word.getMeaning());

    }, delayMillis, TimeUnit.MILLISECONDS);

  }

  private void handleWord(String stringWord, String stringExplain, String stringSynonym,
      String stringAntonym) {
    lbWord.setText(stringWord);
    PaneExplain.setText(stringExplain);
    if (stringAntonym == null || stringAntonym.isEmpty()) {
      stringAntonym = "unknow";
    }
    if (stringSynonym == null || stringSynonym.isEmpty()) {
      stringSynonym = "Unknow";
    }
    txtAntonym.setText(stringAntonym);
    txtSynonym.setText(stringSynonym);
  }

  private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
  private long delayMillis = 100;

  protected void speak(String language) {
    scheduler.schedule(() -> {
      try {
        Google.speak(language, deconvert(lbWord.getText()));
      } catch (IOException | URISyntaxException er) {
        er.printStackTrace();
      }
    }, delayMillis, TimeUnit.MILLISECONDS);

  }

  private int rndRange(int range) {
    Random rnd = new Random();
    int index = Math.abs(rnd.nextInt() % range);
    return index;
  }

  private String rndAlphabet() {
    char value = (char) ((int) ('A') + rndRange(26));
    return "" + value;
  }


  private String rndColor() {
    return COLORS[rndRange(COLORS.length)];
  }

  public class MyPanel extends JPanel {
    private static final long serialVersionUID = 4799123527384112687L;

    public MyPanel() {
      putClientProperty(FlatClientProperties.STYLE,
          "" + "arc:25;" + "[light]foreground:darken(@background, 3%);"
              + "[dark]foreground:lighten(@background, 3%);"
              + "[light]background:darken(@background, 3%);"
              + "[dark]background:lighten(@background, 3%);");
    }

  };


}
