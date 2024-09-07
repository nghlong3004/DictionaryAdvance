package model;

import java.util.TreeMap;

import util.Constants;
import util.IOFile;

public class Account {
	private TreeMap<String, String> id;
	private IOFile ioFile;
	public Account() {
		id = new TreeMap<String, String>();
		ioFile = new IOFile();
		ioFile.inputFromFile(Constants.File.FILE_PATH_ID, id);
	}
	public boolean isId(String username) {
		if(id.containsKey(username)) {
			return true;
		}
		return false;
	}
	public String getPassword(String username) {
		return new String(id.get(username));
	}
	public void addAccount(String username, String password) {
		this.id.put(username, password);
	}
}