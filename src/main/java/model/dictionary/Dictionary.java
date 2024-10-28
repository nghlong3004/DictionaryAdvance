package model.dictionary;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

  private Map<String, Word> mapWord;

  public Dictionary() {
    mapWord = new HashMap<String, Word>();
  }

  public Map<String, Word> getMapWord() {
    return mapWord;
  }

  public void setMapWord(Map<String, Word> mapWord) {
    this.mapWord = mapWord;
  }

}
