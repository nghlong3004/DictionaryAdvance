package util.view;

import util.extral.AvatarIcon;

public class ImageUtil {
	public AvatarIcon getAvatarIcon(String image, int width, int height, float round) {
		AvatarIcon icon = new AvatarIcon(getClass().getResource("/image/" + image), width, height, round);
		return icon;
	}
}
