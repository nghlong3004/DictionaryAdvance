package controller;

import java.time.LocalDate;
import java.util.List;
import model.dictionary.Word;
import service.DictionaryService;

public class DictionaryController {
  private final DictionaryService dictionaryService;

  public DictionaryController(DictionaryService dictionary) {
    this.dictionaryService = dictionary;
  }

  public List<Word> searchWordStartWithKey(String key, String languageForm, String languageTo) {
    return dictionaryService.searchWordStartWithKey(key, languageForm, languageTo);
  }

  public String textTranslator(String key, String languageForm, String languageTo) {
    return dictionaryService.textTranslator(key, languageForm, languageTo);
  }

  public List<Word> getSpecializedWord(String specialized) {
    return dictionaryService.getSpecializedWord(specialized);
  }

  public List<Word> getLovelyByEmail(String email) {
    return dictionaryService.getLovelyByEmail(email);
  }

  public List<Word> getHistoryByDate(LocalDate data) {
    return dictionaryService.getHistoryByDate(data);
  }

  public DictionaryService getDictionaryService() {
    return dictionaryService;
  }
}
