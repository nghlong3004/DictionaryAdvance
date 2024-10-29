package repository.dictionary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import configuration.DatabaseConfiguration;
import model.dictionary.Word;
import repository.DatabaseRepository;
import repository.DictionaryRepository;

public class DictionaryDatabaseRepository extends DatabaseRepository
    implements DictionaryRepository {

  public DictionaryDatabaseRepository(DatabaseConfiguration databaseConfiguration) {
    super(databaseConfiguration);
    // TODO Auto-generated constructor stub
  }

  @Override
  public List<Word> getListWordsBy(String starting, String languageFrom, String languageTo) {
    String query = "SELECT * FROM dictionary WHERE word LIKE ? AND languaged = ? LIMIT 5";
    List<Object> params = new ArrayList<Object>();
    params.add(starting + '%');
    params.add(languageFrom);
    List<Word> words = new ArrayList<Word>();
    for (Object word : databaseExecute(query, params, Word.class)) {
      words.add((Word) word);
    }
    return words.isEmpty() ? null : words;
  }

  @Override
  public List<Word> getTableWordBySpecialized(String specialized) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Word> getHistoryByDate(LocalDate date) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Word> getLovelyByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }



}
