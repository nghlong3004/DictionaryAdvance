package repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.DataSource;
import model.dictionary.Dictionary;
import model.dictionary.DictionaryInterface;
import model.dictionary.Word;
import util.repository.Google;

public class DictionaryRepository implements DictionaryInterface{
	private static DictionaryRepository instance;
	private Dictionary dictionary;
	private DataRepository<Word> dataRepository;
	
	public static synchronized DictionaryRepository getInstace(DataSource dataSource) {
		if(instance == null) {
			instance = new DictionaryRepository(dataSource);
		}
		return instance;
	}
	@SuppressWarnings("unchecked")
	private DictionaryRepository(DataSource dataSource) {
		dictionary = new Dictionary();
		DataRepositoryFactory<Word> dataRepositoryFactory = new DataRepositoryFactory<Word>(dataSource, Word.class);
		dataRepository = dataRepositoryFactory.creatRepository();
		dictionary.setMapWord(dataRepository.load(dictionary.getMapWord().getClass()));
	}
	@Override
	public Word lookup(String key, String languageForm, String languageTo) {
		
		return dictionary.getMapWord().get(key);
	}
	@Override
	public String textTranslation(String key, String languageForm, String languageTo) {
		String value = null;
		try {
			value = Google.translate(key, languageForm, languageTo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	@Override
	public List<Word> tableWord(String specialized) {
		List<Word> list = new ArrayList<Word>();
		for (var entry: dictionary.getMapWord().entrySet()) {
			if(entry.getValue().getSpecialized().equals(specialized)) {
				list.add(entry.getValue());
			}
		}
		
		return null;
	}
	@Override
	public List<String> hotPick() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> history() {
		List<String> list = new ArrayList<String>();
		for (var entry: dictionary.getMapWord().entrySet()) {
			if(!entry.getValue().getSearchTime().isEmpty()) {
				list.add(entry.getKey());
			}
		}
		return list;
	}
	
	
	
	
}
