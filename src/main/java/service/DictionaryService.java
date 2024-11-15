package service;

import java.util.ArrayList;
import java.util.Collections;
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

  public List<Word> getQuestionMinigame() {
    List<String[]> listHistory = getHistoryByDate(LocalDate.EPOCH);
    if (listHistory.size() < 15) {
      int n = listHistory.size();
      dictionaryRepository.getWordMinigameRandom("en", 15 - listHistory.size()).forEach(word -> {
        boolean flag = true;
        for (int i = 0; i < n; ++i) {
          if (listHistory.get(i)[0].equals(word.getWord())) {
            flag = false;
            break;
          }
        }
        if (flag) {
          listHistory
              .add(new String[] {word.getWord(), word.getMeaning(), "", word.getPronounce()});
        }
      });
    }
    Collections.shuffle(listHistory);
    List<Word> listQuestion = new ArrayList<Word>();
    listHistory.forEach(data -> {
      Word word = new Word();
      word.setWord(data[0]);
      word.setMeaning(data[1]);
      word.setPronounce(data[3]);
      listQuestion.add(word);
    });
    return listQuestion;
  }

  public List<Word> getWordMinigameByRandom() {
    return dictionaryRepository.getWordMinigameRandom("en", 1 << 5);
  }

  public List<String> getNameSpecialized() {
    return dictionaryRepository.getNameSpecialized();
  }

}
