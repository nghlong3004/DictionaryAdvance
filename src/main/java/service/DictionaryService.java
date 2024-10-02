package service;

import java.util.List;

import model.dictionary.Word;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map.Entry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.DataSource;
import model.dictionary.Dictionary;
import repository.DataRepository;
import repository.DataRepositoryFactory;
import util.repository.Google;

public class DictionaryService implements DictionaryServiceInterface {
  private static DictionaryService instance;
  private Dictionary dictionary;
  private DataRepository dataRepository;
  private Gson gson = new GsonBuilder().setPrettyPrinting().create();

  public static synchronized DictionaryService getInstance(DataSource dataSource) {
    if (instance == null) {
      instance = new DictionaryService(dataSource);
    }
    return instance;
  }

  private DictionaryService(DataSource dataSource) {
    dictionary = new Dictionary();
    DataRepositoryFactory dataRepositoryFactory =
        new DataRepositoryFactory(dataSource, "Dictionary");
    dataRepository = dataRepositoryFactory.creatRepository();
    Type userListType = new TypeToken<List<Word>>() {}.getType();
    dictionary.setMapWordL(gson.fromJson(dataRepository.read(), userListType));

  }

  @Override
  public Word lookup(String key, String languageForm, String languageTo) {

    return dictionary.getMapWord().get(key);
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

