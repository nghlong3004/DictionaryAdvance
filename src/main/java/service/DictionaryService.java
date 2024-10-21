package service;

import java.util.List;

import model.dictionary.Word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import model.dictionary.Dictionary;
import repository.DataRepository;
import repository.DataRepositoryFactory;
import util.Constants;
import util.PropertyHelper;
import util.repository.Google;
import util.repository.Utils;
import util.state.StorageType;

public class DictionaryService implements DictionaryServiceInterface {
  private static DictionaryService instance;

  private Dictionary dictionary;
  private DataRepository repository;

  public static synchronized DictionaryService getInstance(PropertyHelper dataSource) {
    if (instance == null) {
      instance = new DictionaryService(dataSource);
    }
    return instance;
  }

  private DictionaryService(PropertyHelper dataSource) {
    dictionary = new Dictionary();
    DataRepositoryFactory dataRepositoryFactory = new DataRepositoryFactory(dataSource);
    repository = dataRepositoryFactory.creatRepository();
    List<Word> wordList = new ArrayList<Word>();
    List<String[]> stringList = repository.read();
    if (stringList != null) {
      for (int i = 0; i < stringList.size(); i += 2) {
        Word word = new Word();
        Utils.setValues(word, stringList.get(i), stringList.get(i + 1));
        wordList.add(word);

      }
    }
    dictionary.setMapWordList(wordList);

  }

  @Override
  public Word lookup(String key, String languageForm, String languageTo) {
    if (StorageType.state == StorageType.DATABASE) {

    }
    return dictionary.getMapWord().get(key);
  }

  public void saveWord(Word data) {
    String[] value = {"'" + data.getWord() + "'", "'" + data.getMeaning() + "'", "'" + "'",
        "'" + data.getDescription() + "'", "'" + data.getPronounce() + "'",
        "'" + data.getLanguaged() + "'", "''"
        // "'" + data.getSynonyms() + "'", "'" + data.getAntonym() + "'",
        // "'" + data.getSpecialized() + "'"
    };
    repository.save(Constants.KEY_WORD, value);
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
    // List<Word> list = new ArrayList<Word>();
    // for (Entry<String, Word> entry : dictionary.getMapWord().entrySet()) {
    // if (entry.getValue().getPartOfSpeech().equals(specialized)) {
    // list.add(entry.getValue());
    // }
    // }

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
      if (!entry.getValue().getHistory().isEmpty()) {
        list.add(entry.getKey());
      }
    }
    return list;
  }



}

