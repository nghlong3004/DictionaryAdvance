package controller;

import java.time.LocalDate;
import java.util.List;
import model.dictionary.Word;
import service.DictionaryServiceInterface;

public class DictionaryController {
  private final DictionaryServiceInterface dictionaryService;

  public DictionaryController(DictionaryServiceInterface dictionary) {
    this.dictionaryService = dictionary;
  }

  public List<Word> getListWordsBy(String key, String languageForm, String languageTo) {
    return dictionaryService.getListWordsBy(key, languageForm, languageTo);
  }

  public String textTranslator(String key, String languageForm, String languageTo) {
    return dictionaryService.textTranslator(key, languageForm, languageTo);
  }

  public List<Word> getTableWord(String specialized) {
    return dictionaryService.getTableWord(specialized);
  }

  public List<Word> getLovelyByEmail(String email) {
    return dictionaryService.getLovelyByEmail(email);
  }

  public List<Word> getHistoryByDate(LocalDate data) {
    return dictionaryService.getHistoryByDate(data);
  }

  public DictionaryServiceInterface getDictionaryService() {
    return dictionaryService;
  }
}
