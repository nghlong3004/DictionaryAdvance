package model;

import java.util.Map;
import java.util.TreeMap;

public class Dictionary {
	private Map<String, String> words;
	
	public Dictionary() {
		this.words = new TreeMap<String, String>();
	}

	public Map<String, String> getWords() {
		return words;
	}

	public void push(String key, String value){
		words.put(key, value);
	}
	public void remove(String key) {
		words.remove(key);
	}
	
}
