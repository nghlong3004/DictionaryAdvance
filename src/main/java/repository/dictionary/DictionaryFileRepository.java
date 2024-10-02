package repository.dictionary;

import com.google.gson.JsonElement;
import repository.DataRepository;
import repository.FileRepository;

public class DictionaryFileRepository extends FileRepository implements DataRepository {

  public DictionaryFileRepository(String fileName) {
    super(fileName);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void save(String json) {
    // TODO Auto-generated method stub

  }

  @Override
  public void update() {
    // TODO Auto-generated method stub

  }

  @Override
  public JsonElement read() {
    // TODO Auto-generated method stub
    return super.load();
  }

}
