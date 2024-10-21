package repository;

import java.util.List;

public interface DataRepository {

  public void save(String[] key, String[] value);

  public void delete(String[] key, String[] value);

  public void update(String[] key, String[] value);

  public List<String[]> read();

  public List<String[]> target(String[] datas);

}
