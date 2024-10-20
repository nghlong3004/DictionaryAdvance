package repository.dictionary;

import java.util.List;
import java.util.Map;
import configuration.DatabaseConfiguration;
import model.dictionary.Word;
import repository.DataRepository;
import repository.DatabaseRepository;
import util.repository.Utils;

public class DictionaryDatabaseRepository extends DatabaseRepository
    implements DataRepository<Word> {

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
  public List<Word> read() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Word target(String[] datas) {
    return getWord(super.targetData(datas[0], datas[1], datas[2]));
  }

  private Word getWord(Map<String, String> mp) {
    Word word = new Word();
    word.setKey(mp.get("word"));
    word.setMeaning(mp.get("meaning"));
    word.setDescription(mp.get("description"));
    word.setExample(mp.get("example"));
    word.setSynonyms(mp.get("synonym"));
    word.setAntonym(mp.get("antonym"));
    word.setSpecialized(mp.get("specialized"));

    return word;
  }


}
