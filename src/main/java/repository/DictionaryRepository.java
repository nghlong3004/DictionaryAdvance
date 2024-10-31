package repository;

import java.time.LocalDate;
import java.util.List;
import model.dictionary.Word;

public interface DictionaryRepository {

  public List<Word> searchWordStartWithKey(String key, String languageFrom, String languageTo);

  public List<Word> getSpecializedWord(String specialized);

  public List<Word> getHistoryByDate(LocalDate date);

  public List<Word> getLovelyByEmail(String email);

}
