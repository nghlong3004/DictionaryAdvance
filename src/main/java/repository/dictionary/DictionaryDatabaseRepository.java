package repository.dictionary;

import java.time.LocalDate;
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
    // TODO Auto-generated method stub
    return null;
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
