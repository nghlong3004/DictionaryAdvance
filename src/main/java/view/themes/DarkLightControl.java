package view.themes;

import java.awt.EventQueue;

import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import net.miginfocom.swing.MigLayout;

import javax.swing.JToggleButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DarkLightControl extends JPanel {

  private static final long serialVersionUID = 1L;
  private DarkLightSwitchIcon darkLightSwitchIcon;
  private JToggleButton swIcon;

  // check themes
  private void changeThemes(boolean dark) {
    if (dark != FlatLaf.isLafDark()) {
      if (dark) {
        EventQueue.invokeLater(() -> {
          FlatAnimatedLafChange.showSnapshot();
          FlatMacDarkLaf.setup();
          FlatLaf.updateUI();

          FlatAnimatedLafChange.hideSnapshotWithAnimation();
        });
      } else {
        EventQueue.invokeLater(() -> {
          FlatAnimatedLafChange.showSnapshot();
          FlatMacLightLaf.setup();
          FlatLaf.updateUI();

          FlatAnimatedLafChange.hideSnapshotWithAnimation();
        });
      }
    }
  }

  // build UI switch
  public DarkLightControl() {
    darkLightSwitchIcon = new DarkLightSwitchIcon();
    darkLightSwitchIcon.setCenterSpace(50);
    putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
    JPanel panel = new JPanel(new MigLayout("fillx", "[fill]", "fill"));
    panel.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:999;" + "background:darken($Drawer.background,5%)");
    swIcon = new JToggleButton("");
    swIcon.putClientProperty(FlatClientProperties.STYLE,
        "" + "arc:999;" + "[dark]background:$Drawer.background;"
            + "[light]background:$DrawerLight.background;" + "borderWidth:0;" + "focusWidth:0;"
            + "innerFocusWidth:0;");
    swIcon.setIcon(darkLightSwitchIcon);
    swIcon.addActionListener(new ActionListener() {

      private final ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
      private ScheduledFuture<?> scheduledFuture;

      @Override
      public void actionPerformed(ActionEvent e) {
        if (scheduledFuture != null) {
          scheduledFuture.cancel(true);
        }
        scheduledFuture = scheduled.schedule(() -> {
          changeThemes(swIcon.isSelected());
        }, 200, TimeUnit.MILLISECONDS);
      }
    });
    swIcon.setSelected(true);
    panel.add(swIcon);
    add(panel);
  }

  public void setMenuFull(boolean menuFull) {
    swIcon.setVisible(menuFull);
  }

}

