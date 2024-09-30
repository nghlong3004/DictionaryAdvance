package service;

import java.util.List;

import model.dictionary.Word;

public interface DictionaryServiceInterface {
	public Word lookup(String key, String languageForm, String languageTo);
	
	public String textTranslator(String key, String languageForm, String languageTo);
	
	public List<Word> getTableWord(String specialized);
	
	public List<String> getHotPick();
	
	public List<String> getHistory();
	
}
