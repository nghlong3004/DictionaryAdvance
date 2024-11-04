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

  private final String tableName = "dictionary";

  public DictionaryDatabaseRepository(DatabaseConfiguration databaseConfiguration) {
    super(databaseConfiguration);
  }

  @Override
  public List<Word> searchWordStartWithKey(String starting, String languageFrom,
      String languageTo) {
    String query =
        String.format("SELECT * FROM %s WHERE word LIKE '%s' AND language = '%s' LIMIT 5",
            tableName, starting + '%', languageFrom);
    List<Word> words = new ArrayList<Word>();
    List<List<Object>> data = databaseExecute(query);
    for (int i = 1; i < data.size(); ++i) {
      words.add(mapperDictionary(data.get(0), data.get(i)));
    }
    return words.isEmpty() ? null : words;
  }

  @Override
  public List<Word> getSpecializedWord(String specialized) {

    return null;
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


}
