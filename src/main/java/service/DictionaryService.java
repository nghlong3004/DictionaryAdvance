package service;

import java.util.List;

import model.dictionary.Word;

import java.io.IOException;
import java.time.LocalDate;

import repository.DictionaryRepository;
import repository.DictionaryRepositoryFactory;
import util.ObjectContainer;
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

  public List<Word> getFavouriteByEmail() {
    return dictionaryRepository
        .getFavouriteByEmail(ObjectContainer.getUserController().getUser().getEmail());
  }

  public List<String[]> getHistoryByDate(LocalDate data) {
    return dictionaryRepository
        .getHistoryByDate(ObjectContainer.getUserController().getUser().getEmail(), data);
  }

  public void editWord(Word word, String currentWord) {
    List<Word> currentWords = searchWordStartWithKey(currentWord, "en", "vi");
    Word cuWord = currentWords.get(0);
    String meaning = cuWord.getMeaning().substring(cuWord.getMeaning().indexOf(';') + 10);
    meaning = meaning.substring(0, meaning.indexOf('<')).replace(">", "");
    word.setMeaning(
        cuWord.getMeaning().replace(meaning, word.getMeaning()).replaceAll("'", "\'\'"));
    word.setPronounce(word.getPronounce().replaceAll("'", "\'\'"));
    dictionaryRepository.updateWord(word, currentWord);
  }

  public void deleteWord(String word) {
    dictionaryRepository.deleteWordByWord(word);
  }

  public void processWord(String word, boolean isStarOrFlag) {
    if (isStarOrFlag) {
      dictionaryRepository.processWord(ObjectContainer.getUserController().getUser().getEmail(),
          word);
    } else {
      dictionaryRepository.nonProcessWord(ObjectContainer.getUserController().getUser().getEmail(),
          word);
    }
  }

  public void saveWordToHistory(String word, String meaning) {
    if (dictionaryRepository
        .isWordInHistory(ObjectContainer.getUserController().getUser().getEmail(), word)) {
      dictionaryRepository
          .updateTimeHistory(ObjectContainer.getUserController().getUser().getEmail(), word);
    } else {
      dictionaryRepository.saveWordToHistory(
          ObjectContainer.getUserController().getUser().getEmail(), word, meaning);
    }
  }

  public void deleteWordHistoryByEmail(String word) {
    dictionaryRepository
        .deleteWordHistoryByEmail(ObjectContainer.getUserController().getUser().getEmail(), word);
  }

}
