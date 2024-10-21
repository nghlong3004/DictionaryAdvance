package util;

public class Constants {
  public static final String REGULAR_EXPRESSIONS = "\\w{2,15}";
  public static final String REGULAR_PASS_EXPRESSIONS = "[\\w+\\-*&^%$#@!~():?<>';=`]{6,}";
  public static final String REGULAR_EMAIL_EXPRESSIONS =
      "^[\\w._%+-]{2,15}@[\\w.-]{1,8}\\.[a-zA-Z]{2,6}$";

  public static final String[] KEY_USER = {"updated", "created", "gender", "fullname", "email",
      "birthday", "username", "password", "token"};
  public static final String[] KEY_WORD =
      {"word", "meaning", "pronounce", "description", "languaged", "part_of_speech"};

  public static class ColorApp {
    public static final String[] COLORS = {"#b91c1c", "#3f3f46", "#b91c1c", "#431407", "#92400e",
        "#f59e0b", "#fcd34d", "#ef4444", "#65a30d", "#22c55e", "#15803d", "#10b981", "#14b8a6",
        "#0891b2", "#7dd3fc", "#2563eb", "#3b82f6", "#818cf8", "#4338ca", "#312e81", "#7c3aed",
        "#d946ef", "#a21caf", "#ec4899", "#e11d48", "#fecdd3", "#fff1f2"};
  }
  public static class View {
    public static final int TILE_DEFAULT_SIZE = 32;
    public static final int TILES_IN_WIDTH = 26;
    public static final int TILES_IN_HEIGHT = 14;

    public static final float SCALE = 1.5f;

    public static final int TILES_SIZE = (int) (TILE_DEFAULT_SIZE * SCALE);
    public static final int WINDOW_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public static final int WINDOW_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    public static final int CONTAINER_X_CHOOSE = WINDOW_WIDTH * 1 / 100;
    public static final int CONTAINER_Y_CHOOSE = WINDOW_HEIGHT * 4 / 100;
    public static final int CONTAINER_WIDTH_CHOOSE = WINDOW_WIDTH * 18 / 100;
    public static final int CONTAINER_HEIGHT_CHOOSE = WINDOW_HEIGHT * 88 / 100;

    public static final int CONTAINER_X_MAIN = CONTAINER_X_CHOOSE * 4 + CONTAINER_WIDTH_CHOOSE;
    public static final int CONTAINER_Y_MAIN = WINDOW_HEIGHT * 4 / 100;
    public static final int CONTAINER_WIDTH_MAIN = WINDOW_WIDTH * 75 / 100;
    public static final int CONTAINER_HEIGHT_MAIN = WINDOW_HEIGHT * 88 / 100;

  }
  public static class Image {
    public static final String IMAGE_LOGIN_PATH = "login.png";
    public static final String IMAGE_LOGO_LOGIN_PATH = "logo_login.png";
    public static final String IMAGE_LOGIN_BACKGROUND = "login-bg1.png";
    public static final String IMAGE_ID_USER = "id_user.png";
    public static final String IMAGE_EXIT_LOGIN = "exit_login.png";
    public static final String IMAGE_USER = "user.png";
    public static final String IMAGE_PASS = "pass.png";
    public static final String IMAGE_HIDE = "hide.png";
    public static final String IMAGE_FACEBOOK = "fb.png";
    public static final String IMAGE_GOOGLE = "gg.png";
    public static final String IMAGE_PATH = "src/main/resources/image/";
  }

  public static class File {
    public static final String INPUT_FILE_PATH = "account.txt";
    public static final String OUTPUT_FILE_PATH = "account.txt";

    public static final String ACCOUNT_PATH_ID = "account.txt";

    public static final String USER_PATH_ID = "user.txt";
  }

}
