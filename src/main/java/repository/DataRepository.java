package repository;

import java.util.List;

public interface DataRepository<T> {

  public void save(String[] key, String[] value);

  public void delete(String[] key, String[] value);

  public void update(String[] key, String[] value);

  public List<T> read();
  
  public T target(String[] datas);

}
