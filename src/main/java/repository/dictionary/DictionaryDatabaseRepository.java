package repository.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import configuration.DatabaseConfiguration;
import model.dictionary.Word;
import repository.DataRepository;
import repository.DatabaseRepository;
import util.Constants;
import util.repository.Utils;

public class DictionaryDatabaseRepository extends DatabaseRepository implements DataRepository {

  public DictionaryDatabaseRepository(DatabaseConfiguration databaseConfiguration) {
    super(databaseConfiguration);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void save(String[] keys, String[] values) {
    Word data = new Word();
    Utils.setValues(data, keys, values);

    String columnsString = String.join(", ", keys);
    String valuesString = String.join(", ", values);

    super.saveData("dictionary", columnsString, valuesString);

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
  public List<String[]> read() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<String[]> target(String[] datas) {
    if (datas.length < 2 || datas.length < 3) {
      return null;
    }
    return getWord(super.targetData(datas[0], datas[1], datas[2]));
  }

  private List<String[]> getWord(Map<String, String> mp) {
    List<String[]> word = new ArrayList<String[]>();
    String[] values = new String[Constants.KEY_WORD.length];
    for (int i = 0; i < Constants.KEY_WORD.length; ++i) {
      values[i] = mp.get(Constants.KEY_WORD[i]);
    }
    word.add(Constants.KEY_WORD);
    word.add(values);
    return word;
  }


}
