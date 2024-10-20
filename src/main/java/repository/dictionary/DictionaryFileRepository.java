package repository.dictionary;

import java.util.List;
import model.dictionary.Word;
import repository.DataRepository;
import repository.FileRepository;

public class DictionaryFileRepository extends FileRepository implements DataRepository<Word> {

  public DictionaryFileRepository(String fileName) {
    super(fileName);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void save(String[] key, String[] value) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete(String[] key, String[] value) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void update(String[] key, String[] value) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<Word> read() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Word target(String[] data) {
    // TODO Auto-generated method stub
    return null;
  }


}
