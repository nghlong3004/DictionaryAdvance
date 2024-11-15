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

  public List<Word> getSpecializedWord(String specialized) {
    return dictionaryService.getSpecializedWord(specialized);
  }

  public List<Word> getFavouriteByEmail() {
    return dictionaryService.getFavouriteByEmail();
  }

  public List<Word> getWordMinigameByRandom() {
    return dictionaryService.getWordMinigameByRandom();
  }

  public List<String[]> getHistoryByDate(LocalDate data) {
    return dictionaryService.getHistoryByDate(data);
  }

  public String textTranslator(String key, String languageForm, String languageTo) {
    return dictionaryService.textTranslator(key, languageForm, languageTo);
  }

  public void editWord(Word word, String currentWord) {
    dictionaryService.editWord(word, currentWord);
  }

  public void deleteWord(String word) {
    dictionaryService.deleteWord(word);
  }

  public void processWord(String word, boolean isStarOrFlag) {
    dictionaryService.processWord(word, isStarOrFlag);
  }

  public void saveWordToHistory(String word, String meaning) {
    dictionaryService.saveWordToHistory(word, meaning);
  }

  public void deleteWordHistoryByEmail(String word) {
    dictionaryService.deleteWordHistoryByEmail(word);
  }

  public List<Word> getQuestionMinigame() {
    return dictionaryService.getQuestionMinigame();
  }

  public List<String> getNameSpecialized() {
    return dictionaryService.getNameSpecialized();
  }

}
