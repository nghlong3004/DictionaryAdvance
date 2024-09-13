package model;

import java.util.TreeMap;

public interface Repository <T>{
	void save(T data, Account account);
	void save(T data, T user);
	TreeMap<String, String> load();
	T loadUser();
	void delete();
}
