package service;

import java.util.List;

import model.dictionary.Word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import model.dictionary.Dictionary;
import repository.DataRepository;
import repository.DataRepositoryFactory;
import util.PropertyHelper;
import util.repository.Google;

public class DictionaryService implements DictionaryServiceInterface {
  private static DictionaryService instance;
  
  public final String[] keys = {"word", "meaning", "pronunciation", "description",
      "example", "synonym", "antonym", "specialized", "language"};
  
  private Dictionary dictionary;
  private DataRepository<?> dataRepository;

  public static synchronized DictionaryService getInstance(PropertyHelper dataSource) {
    if (instance == null) {
      instance = new DictionaryService(dataSource);
    }
    return instance;
  }

  @SuppressWarnings("unchecked")
  private DictionaryService(PropertyHelper dataSource) {
    dictionary = new Dictionary();
    DataRepositoryFactory dataRepositoryFactory = new DataRepositoryFactory(dataSource);
    dataRepository = dataRepositoryFactory.creatRepository();
    dictionary.setMapWordL((List<Word>) dataRepository.read());

  }

  @Override
  public Word lookup(String key, String languageForm, String languageTo) {

    return dictionary.getMapWord().get(key);
  }
  
  public void saveWord(Word data) {
    String[] value = {"'" + data.getKey() + "'", "'" + data.getMeaning() + "'", "'" + "'",
        "'" + data.getDescription() + "'", "'" + data.getExample() + "'",
        "'" + data.getSynonyms() + "'", "'" + data.getAntonym() + "'",
        "'" + data.getSpecialized() + "'"/* , "'" + data.getLanguage() + "'" */};
    dataRepository.save(keys, value);
  }
  
  @Override
  public String textTranslator(String key, String languageForm, String languageTo) {
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
  public List<Word> getTableWord(String specialized) {
    List<Word> list = new ArrayList<Word>();
    for (Entry<String, Word> entry : dictionary.getMapWord().entrySet()) {
      if (entry.getValue().getSpecialized().equals(specialized)) {
        list.add(entry.getValue());
      }
    }

    return null;
  }

  @Override
  public List<String> getHotPick() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<String> getHistory() {
    List<String> list = new ArrayList<String>();
    for (Entry<String, Word> entry : dictionary.getMapWord().entrySet()) {
      if (!entry.getValue().getSearchTime().isEmpty()) {
        list.add(entry.getKey());
      }
    }
    return list;
  }



}

