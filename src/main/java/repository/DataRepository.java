package repository;

import com.google.gson.JsonElement;

public interface DataRepository {

  public void save(String json);

  public void delete();

  public void update();

  public JsonElement read();

}
