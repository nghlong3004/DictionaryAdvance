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
  public List<String[]> getHistoryByDate(String email, LocalDate date) {

    return null;
  }

  @Override
  public List<Word> getFavouriteByEmail(String email) {

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

  @Override
  public void processWord(String email, String word) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void nonProcessWord(String email, String word) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void saveWordToHistory(String email, String word, String meaning) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteWordHistoryByEmail(String email, String word) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean isWordInHistory(String email, String word) {
    // TODO Auto-generated method stub
    return false;
  }
  
  @Override
  public void updateTimeHistory(String email, String word) {
    // TODO Auto-generated method stub
    
  }



}
