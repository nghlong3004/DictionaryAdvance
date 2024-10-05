package view.dictionary.components;

import java.awt.Component;
import java.awt.Graphics2D;
import com.formdev.flatlaf.icons.FlatAbstractIcon;
import util.extral.AvatarIcon;

public class AvatarBorderColorIcon extends FlatAbstractIcon {

  private AvatarIcon.BorderColor borderColor;

  public AvatarBorderColorIcon(AvatarIcon.BorderColor borderColor) {
      super(16, 16, null);
      this.borderColor = borderColor;
  }

  @Override
  protected void paintIcon(Component component, Graphics2D g) {
      borderColor.paint(g, 1, 1, width - 2, height - 2);
      g.fillRoundRect(1, 1, width - 2, height - 2, 5, 5);
      g.dispose();
  }
}
