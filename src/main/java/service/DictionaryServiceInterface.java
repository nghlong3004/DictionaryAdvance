package service;

import java.time.LocalDate;
import java.util.List;

import model.dictionary.Word;

public interface DictionaryServiceInterface {
  public List<Word> getListWordsBy(String key, String languageForm, String languageTo);

  public String textTranslator(String key, String languageForm, String languageTo);

  public List<Word> getTableWord(String specialized);

  public List<Word> getLovelyByEmail(String email);

  public List<Word> getHistoryByDate(LocalDate data);

}
