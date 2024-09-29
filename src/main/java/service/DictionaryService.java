package service;

import java.util.List;

import model.dictionary.Word;
import repository.DictionaryInterface;

public class DictionaryService implements DictionaryServiceInterface{
	
	private final DictionaryInterface dictionary;
	private static DictionaryService instance;
	
	public static synchronized DictionaryService getInstance(DictionaryInterface dictionary) {
		if(instance == null) {
			instance = new DictionaryService(dictionary);
		}
		return instance;
	}
	private DictionaryService(DictionaryInterface dictionary) {
		this.dictionary = dictionary;
	}
	@Override
	public Word lookup(String key, String languageForm, String languageTo) {
		if(key == null) {
			throw new NullPointerException("KEY NOT VALUE");
		}
		
		return dictionary.lookup(key, languageForm, languageTo);
	}
	@Override
	public String textTranslator(String key, String languageForm, String languageTo) {
		if(key == null) {
			throw new NullPointerException("KEY NOT VALUE");
		}
		return dictionary.textTranslator(key, languageForm, languageTo);
	}
	@Override
	public List<Word> getTableWord(String specialized) {
		// TODO Auto-generated method stub
		return dictionary.getTableWord(specialized);
	}
	@Override
	public List<String> getHotPick() {
		// TODO Auto-generated method stub
		return dictionary.getHotPick();
	}
	@Override
	public List<String> getHistory() {
		// TODO Auto-generated method stub
		return dictionary.getHistory();
	}
	
	
}
