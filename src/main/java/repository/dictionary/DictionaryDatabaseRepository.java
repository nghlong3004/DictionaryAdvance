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
  public List<Word> getHistoryByDate(LocalDate date) {

    return null;
  }

  @Override
  public List<Word> getLovelyByEmail(String email) {

    return null;
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


}
