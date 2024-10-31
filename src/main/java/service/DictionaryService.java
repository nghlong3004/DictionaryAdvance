package service;

import java.util.List;

import model.dictionary.Word;

import java.io.IOException;
import java.time.LocalDate;

import repository.DictionaryRepository;
import repository.DictionaryRepositoryFactory;

import util.repository.Google;

public class DictionaryService {

  private DictionaryRepository dictionaryRepository;

  public DictionaryService() {
    DictionaryRepositoryFactory dataRepositoryFactory = new DictionaryRepositoryFactory();
    dictionaryRepository = dataRepositoryFactory.createDictionaryRepository();
  }

  public String textTranslator(String key, String languageForm, String languageTo) {
    String value = null;
    try {
      value = Google.translate(key, languageForm, languageTo);
    } catch (IOException e) {

      e.printStackTrace();
    }
    return value;
  }

  public List<Word> searchWordStartWithKey(String key, String languageForm, String languageTo) {
    return dictionaryRepository.searchWordStartWithKey(key, languageForm, languageTo);
  }

  public List<Word> getSpecializedWord(String specialized) {
    return dictionaryRepository.getSpecializedWord(specialized);
  }

  public List<Word> getLovelyByEmail(String email) {
    return dictionaryRepository.getLovelyByEmail(email);
  }

  public List<Word> getHistoryByDate(LocalDate data) {
    return dictionaryRepository.getHistoryByDate(data);
  }



}
