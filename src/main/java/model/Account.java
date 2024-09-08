package model;

import static util.Constants.File.*;

import java.util.TreeMap;

import util.IOFile;

public class Account {
	private TreeMap<String, String> id;
	private User user;
	private IOFile ioFile;
	public Account() {
		id = new TreeMap<String, String>();
		user = new User();
		ioFile = new IOFile();
		ioFile.inputFromFile(FILE_PATH_ID, id);
		ioFile.inputFromFile(USER_PATH_ID, user);
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
	public void addUser(String username, String password) {
		this.id.put(username, password);
		ioFile.outputFromFile(FILE_PATH_ID, id);
	}
	public void saveDataUserCur(User user) {
		ioFile.outputFromFile(USER_PATH_ID, user);
	}
	public User getUser() {
		return this.user;
	}
}