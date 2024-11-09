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
  public List<Word> getHistoryByDate(LocalDate date) {

    return null;
  }

  @Override
  public List<Word> getLovelyByEmail(String email) {

    return null;
  }

  @Override
  public List<Word> searchWordStartWithKey(String key, String languageFrom, String languageTo) {

    return null;
  }

  @Override
  public List<Word> getSpecializedWord(String specialized) {

    return null;
  }

  @Override
  public void updateWord(Word word, String currentKey) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteWordByWord(String word) {
    // TODO Auto-generated method stub

  }



}
