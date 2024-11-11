package repository.dictionary;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import configuration.DatabaseConfiguration;
import model.dictionary.Word;
import repository.DatabaseRepository;
import repository.DictionaryRepository;

public class DictionaryDatabaseRepository extends DatabaseRepository
    implements DictionaryRepository {

  private final String tableName = "dictionary";

  public DictionaryDatabaseRepository(DatabaseConfiguration databaseConfiguration) {
    super(databaseConfiguration);
  }

  private List<Word> selectDatabase(String query) {
    List<Word> words = new ArrayList<Word>();
    List<List<Object>> data = databaseExecute(query);
    for (int i = 1; i < data.size(); ++i) {
      words.add(mapperDictionary(data.get(0), data.get(i)));
    }
    return words.isEmpty() ? null : words;
  }

  @Override
  public List<Word> searchWordStartWithKey(String starting, String languageFrom,
      String languageTo) {
    String query = String.format(
        "SELECT * FROM %s WHERE word LIKE '%s' AND language = '%s' ORDER BY word LIMIT 5",
        tableName, starting + '%', languageFrom);
    return selectDatabase(query);
  }

  @Override
  public List<Word> getSpecializedWord(String specialized) {

    String query = String.format(
        "SELECT * FROM dictionary WHERE specialized_id = (SELECT specialized_id FROM specialized WHERE name_specialized = '%s')",
        specialized);
    return selectDatabase(query);
  }

  @Override
  public List<String[]> getHistoryByDate(String email, LocalDate date) {
    String query = String.format(
        "SELECT word, meaning, updated FROM history WHERE updated >= '%s' AND user_id = (SELECT user_id FROM userinfo WHERE email = '%s')",
        date, email);
    List<String[]> historyList = new ArrayList<String[]>();
    List<List<Object>> data = databaseExecute(query);

    for (int i = 1; i < data.size(); ++i) {
      if (data.get(i).size() == 3) {
        historyList.add(new String[] {(String) data.get(i).get(0), (String) data.get(i).get(1),
            ((Timestamp) data.get(i).get(2)).toString()});
      }
    }

    return historyList;
  }

  @Override
  public List<Word> getFavouriteByEmail(String email) {
    String query = String.format("SELECT d.word, d.meaning " + "FROM dictionary d "
        + "JOIN favourite f ON d.dictionary_id = f.dictionary_id "
        + "JOIN userinfo u ON f.user_id = u.user_id " + "WHERE u.email = '%s'", email);
    return selectDatabase(query);
  }

  @Override
  public boolean isWordInHistory(String email, String word) {
    String query = String.format(
        "SELECT word FROM history WHERE user_id = (SELECT user_id FROM userinfo WHERE email = '%s') AND word = '%s'",
        email, word);
    List<List<Object>> data = databaseExecute(query);
    if (data.size() == 2) {
      if (!((String) data.get(1).get(0)).isEmpty()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void updateWord(Word word, String currentKey) {
    String query = String.format(
        "UPDATE dictionary SET word = '%s', meaning = '%s', description = '%s', pronounce = '%s', updated = '%s' WHERE word = '%s'",
        word.getWord(), word.getMeaning(), word.getDescription(), word.getPronounce(),
        Timestamp.valueOf(LocalDateTime.now()), currentKey);
    databaseExecute(query);
  }

  @Override
  public void deleteWordByWord(String word) {
    String query = String.format("DELETE FROM dictionary WHERE word = '%s'", word);
    databaseExecute(query);
  }

  @Override
  public void processWord(String email, String word) {
    String query = String.format(
        "INSERT INTO favourite(dictionary_id, user_id) VALUES((SELECT dictionary_id FROM dictionary WHERE word = '%s'), (SELECT user_id FROM userinfo WHERE email = '%s'))",
        word, email);
    databaseExecute(query);
  }

  @Override
  public void nonProcessWord(String email, String word) {
    String query = String.format(
        "DELETE FROM favourite WHERE dictionary_id = (SELECT dictionary_id FROM dictionary WHERE word = '%s') AND user_id = (SELECT user_id FROM userinfo WHERE email = '%s')",
        word, email);
    databaseExecute(query);
  }

  @Override
  public void saveWordToHistory(String email, String word, String meaning) {
    String query = String.format(
        "INSERT INTO history(user_id, word, meaning, updated) VALUES((SELECT user_id FROM userinfo WHERE email = '%s'), '%s', '%s', NOW())",
        email, word, meaning);
    databaseExecute(query);
  }

  @Override
  public void deleteWordHistoryByEmail(String email, String word) {
    String query = String.format(
        "DELETE FROM history WHERE user_id = (SELECT user_id FROM userinfo WHERE email = '%s') AND word = '%s'",
        email, word);
    databaseExecute(query);
  }

  @Override
  public void updateTimeHistory(String email, String word) {
    String query = String.format(
        "UPDATE history SET updated = NOW() WHERE user_id = (SELECT user_id FROM userinfo WHERE email = '%s') AND word = '%s'",
        email, word);
    databaseExecute(query);
  }

  private Word mapperDictionary(List<Object> column, List<Object> row) {
    Word word = new Word();
    for (int i = 0; i < column.size(); ++i) {
      String columnName = (String) column.get(i);
      switch (columnName) {
        case "word":
          word.setWord((String) row.get(i));
          break;
        case "meaning":
          word.setMeaning((String) row.get(i));
          break;
        case "antonym":
          word.setAntonym((String) row.get(i));
          break;
        case "synonym":
          word.setSynonym((String) row.get(i));
          break;
        case "description":
          word.setDescription((String) row.get(i));
          break;
        case "pronounce":
          word.setPronounce((String) row.get(i));
          break;
        case "partOfSpeech":
          word.setPartOfSpeech((String) row.get(i));
          break;
        case "language":
          word.setLanguaged((String) row.get(i));
          break;
        default:
          break;
      }

    }
    return word;
  }


}
