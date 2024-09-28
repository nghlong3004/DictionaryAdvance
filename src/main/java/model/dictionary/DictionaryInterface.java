package model.dictionary;

import java.util.List;

public interface DictionaryInterface {
	// tra tu
	public Word lookup(String key, String languageForm, String languageTo);
	// dich 1 doan van ban
	public String textTranslation(String key, String languageForm, String languageTo);
	// tra ve 1 bang cac word
	public List<Word> tableWord(String specialized);
	// tu duoc tim kiem nhieu nhat
	public List<String> hotPick();
	// lich su
	public List<String> history();
	
}
