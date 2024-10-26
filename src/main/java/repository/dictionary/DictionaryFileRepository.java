package repository.dictionary;

import java.time.LocalDate;
import java.util.List;
import configuration.FileConfiguration;
import model.dictionary.Word;
import repository.DictionaryRepository;
import repository.FileRepository;

public class DictionaryFileRepository extends FileRepository implements DictionaryRepository {

  public DictionaryFileRepository(FileConfiguration fileConfiguration) {
    super(fileConfiguration);

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
