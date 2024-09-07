package util;

public class Constants {
	public static final String REGULAR_EXPRESSIONS = "\\w{4,20}";
	public static class View{
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
	public static class Image{
		public static final String IMAGE_LOGIN_PATH = "target/image/login.png";
		public static final String IMAGE_LOGO_LOGIN_PATH = "target/image/logo_login.png";
		public static final String IMAGE_LOGIN = "target/image/login-bg1.png";
		public static final String IMAGE_ID_USER = "target/image/id_user.png";
		public static final String IMAGE_EXIT_LOGIN = "target/image/exit_login.png";
		public static final String IMAGE_USER = "target/image/user.png";
		public static final String IMAGE_PASS = "target/image/pass.png";
		public static final String IMAGE_HIDE = "target/image/hide.png";
	}
	
	public static class File{
		public static final String INPUT_FILE_PATH = "target/datastoge/data.txt";
		public static final String OUTPUT_FILE_PATH = "target/datastoge/newData.txt";
		
		public static final String FILE_PATH_ID = "target/datastoge/dataAccount.txt";
	}
	
}
