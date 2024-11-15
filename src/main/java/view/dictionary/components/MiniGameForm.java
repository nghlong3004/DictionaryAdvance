package view.dictionary.components;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import com.formdev.flatlaf.FlatClientProperties;
import controller.DictionaryController;
import model.dictionary.Word;
import net.miginfocom.swing.MigLayout;
import util.ObjectContainer;
import util.extral.AvatarIcon;

public class MiniGameForm extends JPanel {

  private static final long serialVersionUID = 8554949034694373332L;

  private final DictionaryController dictionaryController =
      ObjectContainer.getDictionaryController();

  private List<Word> listQuestion;
  private List<List<String>> listMeanings;
  private boolean[][] isClick;
  private int column;

  private LocalDateTime playGame;
  private LocalDateTime endGame;

  private Timer timer;

  private CardLayout card;

  public MiniGameForm() {
    card = new CardLayout();
    setLayout(card);
    add(panelWattingRoom(), "one");
    add(panelPlayGame(), "two");
  }

  private JComponent panelWattingRoom() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout("fill"));
    JLabel lbMainMinigame = new JLabel();
    AvatarIcon icon =
        new AvatarIcon(getClass().getResource("/image/mainminigame.png"), 500, 600, 0);
    lbMainMinigame.setIcon(icon);
    panel.add(lbMainMinigame, "split 2, gapx push n");
    panel.add(panelDescription(), "gapx n push, width 400:500");
    JButton cmdStart = new JButton("Bắt đầu");
    cmdStart.addActionListener(actionEven -> {
      card.next(this);
      setupPlayGame();
    });
    cmdStart.putClientProperty(FlatClientProperties.STYLE, "arc:999;");
    panel.add(cmdStart, "width 300!, gapy 0 20, center,dock south");
    return panel;
  }

  private JComponent panelDescription() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout("fill"));
    JLabel lbBook = new JLabel();
    JLabel lbCat = new JLabel();
    JLabel lbDes = new JLabel("Cách chơi", SwingConstants.CENTER);
    lbDes.setFont(new Font("Arial", Font.PLAIN, 16));
    lbDes.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:999;" + "[dark]foreground:darken(@background, 30%);"
            + "[light]foreground:lighten(@background, 30%);"
            + "[light]background:darken(@background, 3%);"
            + "[dark]background:lighten(@background, 3%);");
    AvatarIcon icon =
        new AvatarIcon(getClass().getResource("/image/bookminigame.png"), 100, 100, 0);
    lbBook.setIcon(icon);
    icon = new AvatarIcon(getClass().getResource("/image/catgame.png"), 100, 100, 0);
    lbCat.setIcon(icon);
    panel.add(lbBook, "split 3");
    panel.add(lbDes, "width 130!, height 80!, gapx push n");
    panel.add(lbCat, "gapx push n");
    panel.add(panelRule(), "grow, dock south");
    return panel;
  }

  private JComponent panelRule() {
    JPanel panel = new JPanel();
    panel.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:25;" + "[light]foreground:darken(@background, 3%);"
            + "[dark]foreground:lighten(@background, 3%);"
            + "[light]background:darken(@background, 3%);"
            + "[dark]background:lighten(@background, 3%);");

    panel.setLayout(new MigLayout("fill, wrap"));
    JLabel lbOne = new JLabel("<html><body style='width: 320px;'>"
        + "<p style='color: black;'>Minigame bao gồm các câu hỏi về nghĩa của những từ mà bạn đã tra gần đây.</p>"
        + "</body></html>");
    JLabel lbTwo = new JLabel("<html><body style='width: 320px;'>"
        + "<p style='color: black;'>Có tổng cộng 15 câu hỏi dạng trắc nghiệm và bạn có 2 phút để hoàn thành.</p>"
        + "</body></html>");
    JLabel lbThree = new JLabel("<html><body style='width: 320px;'>"
        + "<p style='color: black;'>Bạn nên sử dụng trí nhớ của mình, không nên sử dụng chức năng Tra từ và Dịch của ứng dụng khác để giải.</p>"
        + "</body></html>");
    AvatarIcon icon = new AvatarIcon(getClass().getResource("/image/one.png"), 50, 50, 0);
    lbOne.setIcon(icon);
    lbOne.setHorizontalAlignment(SwingConstants.CENTER);
    icon = new AvatarIcon(getClass().getResource("/image/two.png"), 50, 50, 0);
    lbTwo.setIcon(icon);
    lbTwo.setHorizontalAlignment(SwingConstants.CENTER);
    icon = new AvatarIcon(getClass().getResource("/image/three.png"), 50, 50, 0);
    lbThree.setIcon(icon);
    lbThree.setHorizontalAlignment(SwingConstants.CENTER);
    lbOne.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +6;");
    lbTwo.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +6;");
    lbThree.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +6;");
    panel.add(lbOne, "height 100!,grow");
    panel.add(lbTwo, "height 100!,grow");
    panel.add(lbThree, "height 100!,grow, center");
    return panel;
  }

  private JComponent panelPlayGame() {
    JPanel panel = new JPanel();
    initializedInPlay();
    panel.setLayout(new MigLayout("fill"));
    panel.add(panelQuestion(), "growy, gaptop 20, gapleft 50, gapright 20, split 2");
    panel.add(panelChooseQuestion(), "gaptop 20, gapbottom 20, center, gapx push n, width 350");
    return panel;
  }


  private JComponent panelQuestion() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout("fillx, wrap, width 600:800"));
    panel.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:25;" + "[light]foreground:darken(@background, 3%);"
            + "[dark]foreground:lighten(@background, 3%);"
            + "[light]background:darken(@background, 3%);"
            + "[dark]background:lighten(@background, 3%);");

    JPanel panelMeaning = new JPanel();

    lbPoser.putClientProperty(FlatClientProperties.STYLE, "" + "arc:25;" + "background:null");
    lbPoser.setOpaque(true);
    lbPoser.setHorizontalAlignment(SwingConstants.CENTER);
    lbPoser.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10;");
    lbQuestion.setHorizontalAlignment(SwingConstants.CENTER);
    lbQuestion.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +5;");
    lbProgress.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +3;");

    panelMeaning.setLayout(new MigLayout("fill, wrap"));
    panelMeaning.putClientProperty(FlatClientProperties.STYLE, "" + "arc:25;");

    panelMeaning.add(lbPoser, "height 50!, width 40!, gapx 20 20");
    panelMeaning.add(lbQuestion, "height 60!, growx, gapx 20 20, gapy 20 20");
    for (int i = 0; i < cmdMeanings.length; ++i) {
      cmdMeanings[i] = new JButton();
      final int j = i;
      cmdMeanings[i].addActionListener(actionEven -> {
        solveClickMeaning(cmdMeanings[j].getText());
      });
      panelMeaning.add(cmdMeanings[i], "growx, height 40!, gapx 20 20, gapy 20 10");
    }

    panel.add(lbProgress, "gapx push n, gapright 5");
    panel.add(panelMeaning, "grow");
    setupProgress();
    setupPoser();

    return panel;
  }

  private JComponent panelChooseQuestion() {
    JPanel panel = new JPanel();
    JPanel panelQuestion = new JPanel();
    panel.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:25;" + "[light]foreground:darken(@background, 3%);"
            + "[dark]foreground:lighten(@background, 3%);"
            + "[light]background:darken(@background, 3%);"
            + "[dark]background:lighten(@background, 3%);");
    panelQuestion.setLayout(new MigLayout("wrap 5"));
    panelQuestion.putClientProperty(FlatClientProperties.STYLE, "arc:25;");
    panel.setLayout(new MigLayout("fill, wrap"));

    cmdDone.addActionListener(actionEven -> {
      setUpEndGame();
    });
    cmdExit.addActionListener(actionEven -> {
      setUpEndGame();
    });

    AvatarIcon icon = new AvatarIcon(getClass().getResource("/image/quiz1.png"), 300, 50, 25);
    lbTitle.setIcon(icon);
    lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lbTitle.putClientProperty(FlatClientProperties.STYLE, "arc:25;" + "background:null");
    icon = new AvatarIcon(getClass().getResource("/image/countdown.png"), 25, 25, 25);
    lbTime.setIcon(icon);
    lbTime.setHorizontalTextPosition(SwingConstants.RIGHT);
    for (int i = 0; i < cmdMeanings.length; ++i) {
      cmdMeanings[i].putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +3;");
    }
    for (int i = 1; i < cmdQuestion.length; ++i) {
      String title = "";
      if (i < 10)
        title = "0";
      cmdQuestion[i] = new JButton(title + i);
      cmdQuestion[i].putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +8;");
      final int j = i;
      cmdQuestion[i].addActionListener(actionEven -> {
        solveClickinQuestion(j);
      });
      if (i % 3 == 1) {
        panelQuestion.add(cmdQuestion[i], "gapleft 2, width 50!, height 50!");
      } else
        panelQuestion.add(cmdQuestion[i], "width 50!, height 50!");
    }
    panel.add(lbTitle, "grow");
    panel.add(lbTime, "grow, center");
    panel.add(panelQuestion, "grow, center, height 300!, width 300!");
    panel.add(separator);
    panel.add(cmdDone, "growx, height 30!");
    panel.add(cmdExit, "growx, height 30!");

    return panel;
  }

  private void solveClickMeaning(String text) {
    AvatarIcon icon = new AvatarIcon(getClass().getResource("/image/success.png"), 25, 25, 25);
    cmdQuestion[column].setIcon(icon);
    cmdQuestion[column].setHorizontalTextPosition(SwingConstants.CENTER);
    cmdQuestion[column].setVerticalTextPosition(SwingConstants.CENTER);
    cmdQuestion[column].setText("");
    for (int i = 0; i < cmdMeanings.length; ++i) {
      if (isClick[column][i]) {
        cmdMeanings[i].setBackground(cmdMeanings[(i + 1) % cmdMeanings.length].getBackground());
      }
      isClick[column][i] = false;
      if (text.equals(cmdMeanings[i].getText())) {
        isClick[column][i] = true;
      }
    }
    setupColorinMeaning();
    setupProgress();
  }

  private void setupPlayGame() {
    listQuestion = dictionaryController.getQuestionMinigame();
    listMeanings = new ArrayList<List<String>>();
    cmdQuestion[0] = new JButton();
    cmdQuestion[1].setBackground(Color.decode("#ef4444"));
    playGame = LocalDateTime.now();
    endGame = playGame.plus(Duration.ofMinutes(2));
    timer = new Timer(100, e -> {
      Duration duration = Duration.between(LocalDateTime.now(), endGame);
      long second = duration.getSeconds();
      int seconds = (int) second % 60;
      int minutes = (int) (second / 60) % 60;
      String zero = ":";
      if (seconds < 10) {
        zero = ":0";
      }
      lbTime.setText("<html>Thời gian còn lại <span style='color: orange; font-weight: bold;'>"
          + minutes + zero + seconds + "</span></html>");
      if (minutes == seconds && minutes == 0) {
        setUpEndGame();
      }
    });
    timer.start();
    List<Word> allQuestions = dictionaryController.getWordMinigameByRandom();
    for (int i = 1; i < 16; ++i) {
      listMeanings.add(generateOptions(allQuestions, listQuestion.get(i - 1).getMeaning(), 4));
    }
    setupTextMeaning(0);
  }

  private void setupTextMeaning(int j) {
    lbQuestion.setText(String.format("Hãy chọn nghĩa đúng của từ %s",
        listQuestion.get(j).getWord().toUpperCase()));
    for (int i = 0; i < cmdMeanings.length; ++i) {
      cmdMeanings[i].setText(listMeanings.get(j).get(i));
    }
  }

  private void setupColorinMeaning() {
    for (int i = 0; i < cmdMeanings.length; ++i) {
      cmdMeanings[i].setBackground(cmdDone.getBackground());
      if (isClick[column][i]) {
        cmdMeanings[i].setBackground(Color.decode("#4caf50"));
      }
    }
  }

  private void setupProgress() {
    int total = 0;
    for (int i = 1; i < cmdQuestion.length; ++i) {
      for (int j = 0; j < cmdMeanings.length; ++j) {
        if (isClick[i][j]) {
          ++total;
        }
      }
    }
    lbProgress.setText("<html>Bạn đã hoàn thành <span style='color: orange; font-weight: bold;'>"
        + total + "/" + (cmdQuestion.length - 1) + "</span> Câu</html>");
  }

  private void setupPoser() {
    String title = "";
    if (column < 10)
      title += "0";
    lbPoser.setText(title + column);
  }

  private void setUpEndGame() {
    card.previous(this);
    for (int i = 0; i < isClick.length; ++i) {
      cmdQuestion[i].setBackground(cmdQuestion[0].getBackground());
      for (int j = 0; j < isClick[i].length; ++j) {
        isClick[i][j] = false;
      }
    }
    column = 1;
    if (timer != null) {
      timer.stop();
    }
    setupColorinMeaning();
    setupProgress();
    setupPoser();
  }

  private void solveClickinQuestion(int j) {
    cmdQuestion[column].setBackground(cmdQuestion[j].getBackground());
    cmdQuestion[j].setBackground(Color.decode("#ef4444"));
    column = j;
    setupTextMeaning(j - 1);
    setupColorinMeaning();
    setupPoser();
  }

  private List<String> generateOptions(List<Word> allQuestions, String correctAnswer,
      int numOptions) {
    HashSet<String> options = new HashSet<>();
    options.add(correctAnswer);

    Random random = new Random();
    while (options.size() < numOptions) {
      String randomAnswer = allQuestions.get(random.nextInt(allQuestions.size())).getMeaning();
      options.add(randomAnswer);
    }

    List<String> optionList = new ArrayList<>(options);
    Collections.shuffle(optionList);
    return optionList;
  }

  private void initializedInPlay() {
    isClick = new boolean[16][4];
    column = 1;

    cmdMeanings = new JButton[4];
    cmdQuestion = new JButton[16];
    cmdDone = new JButton("Hoàn thành");
    cmdExit = new JButton("Thoát");

    lbTitle = new JLabel();
    lbTime = new JLabel();
    lbQuestion = new JLabel();
    lbPoser = new JLabel();
    lbProgress = new JLabel();


    separator = new JSeparator();
  }

  private JLabel lbTitle;
  private JLabel lbTime;
  private JLabel lbQuestion;
  private JLabel lbPoser;
  private JLabel lbProgress;

  private JButton[] cmdMeanings;
  private JButton[] cmdQuestion;

  private JButton cmdDone;
  private JButton cmdExit;

  private JSeparator separator;

}
