package repository;

import java.time.LocalDate;
import java.util.List;
import model.dictionary.Word;

public interface DictionaryRepository {

  public List<Word> getListWordsBy(String starting, String languageFrom, String languageTo);

  public List<Word> getTableWordBySpecialized(String specialized);

  public List<Word> getHistoryByDate(LocalDate date);

  public List<Word> getLovelyByEmail(String email);

}
