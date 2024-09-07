package model;

import java.util.TreeMap;

public class Dictionary {
	private TreeMap<String, String> words;
	
	public Dictionary() {
		super();
		this.words = new TreeMap<String, String>();
	}

	public TreeMap<String, String> getWords() {
		return words;
	}

	public void push(String key, String value){
		words.put(key, value);
	}
	public void remove(String key) {
		words.remove(key);
	}
	
}
