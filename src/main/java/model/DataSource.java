package model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataSource {
	private Properties properties;
	public DataSource(){
		properties = new Properties();
		try {
			InputStream input = getClass().getResourceAsStream("/config/config.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public String getType() {
		return properties.getProperty("data.source");
	}
	
	public String getDbUrl() {
		return properties.getProperty("db.url");
	}
	public String getDbUsername() {
		return properties.getProperty("db.username");
	}
	public String getDbPassword() {
		return properties.getProperty("db.password");
	}
	public String getFilePathUser() {
		return properties.getProperty("file.pathUser");
	}
	public String getFilePathDictionary() {
		return properties.getProperty("file.pathDictionary");
	}
	public String getImagePath() {
		return properties.getProperty("image.path");
	}
	
}
