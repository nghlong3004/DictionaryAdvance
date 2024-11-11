package repository;

import java.time.LocalDate;
import java.util.List;
import model.dictionary.Word;

public interface DictionaryRepository {

  public List<Word> searchWordStartWithKey(String key, String languageFrom, String languageTo);

  public List<Word> getSpecializedWord(String specialized);

  public List<Word> getFavouriteByEmail(String email);
  
  public List<String[]> getHistoryByDate(String email, LocalDate date);
  
  public boolean isWordInHistory(String email, String word);

  public void updateWord(Word word, String currentKey);

  public void deleteWordByWord(String word);

  public void processWord(String email, String word);

  public void nonProcessWord(String email, String word);

  public void saveWordToHistory(String email, String word, String meaning);

  public void deleteWordHistoryByEmail(String email, String word);
  
  public void updateTimeHistory(String email, String word);

}
