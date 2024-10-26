package service;

import java.util.List;

import model.dictionary.Word;

import java.io.IOException;
import java.time.LocalDate;
import configuration.MappingConfiguration;

import repository.DictionaryRepository;
import repository.DictionaryRepositoryFactory;
import util.repository.Google;

public class DictionaryService implements DictionaryServiceInterface {

  private DictionaryRepository dictionaryRepository;

  public DictionaryService() {
    MappingConfiguration.registerMapping(Word.class, "dictionary");
    DictionaryRepositoryFactory dataRepositoryFactory = new DictionaryRepositoryFactory();
    dictionaryRepository = dataRepositoryFactory.createDictionaryRepository();
  }

  @Override
  public String textTranslator(String key, String languageForm, String languageTo) {
    String value = null;
    try {
      value = Google.translate(key, languageForm, languageTo);
    } catch (IOException e) {

      e.printStackTrace();
    }
    return value;
  }

  @Override
  public List<Word> getListWordsBy(String key, String languageForm, String languageTo) {
    return dictionaryRepository.getListWordsBy(key, languageForm, languageTo);
  }

  @Override
  public List<Word> getTableWord(String specialized) {
    return dictionaryRepository.getTableWordBySpecialized(specialized);
  }

  @Override
  public List<Word> getLovelyByEmail(String email) {
    return dictionaryRepository.getLovelyByEmail(email);
  }

  @Override
  public List<Word> getHistoryByDate(LocalDate data) {
    return dictionaryRepository.getHistoryByDate(data);
  }



}

