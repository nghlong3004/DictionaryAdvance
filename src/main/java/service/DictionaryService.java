package service;

import java.util.List;

import model.dictionary.DictionaryInterface;
import model.dictionary.Word;
import repository.DictionaryRepository;

public class DictionaryService implements DictionaryInterface{
	
	private final DictionaryRepository dictionary;
	private static DictionaryService instance;
	
	public static synchronized DictionaryService getInstance(DictionaryRepository dictionary) {
		if(instance == null) {
			instance = new DictionaryService(dictionary);
		}
		return instance;
	}
	private DictionaryService(DictionaryRepository dictionary) {
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
	public String textTranslation(String key, String languageForm, String languageTo) {
		if(key == null) {
			throw new NullPointerException("KEY NOT VALUE");
		}
		return dictionary.textTranslation(key, languageForm, languageTo);
	}
	@Override
	public List<Word> tableWord(String specialized) {
		// TODO Auto-generated method stub
		return dictionary.tableWord(specialized);
	}
	@Override
	public List<String> hotPick() {
		// TODO Auto-generated method stub
		return dictionary.hotPick();
	}
	@Override
	public List<String> history() {
		// TODO Auto-generated method stub
		return dictionary.history();
	}
	
	
}
