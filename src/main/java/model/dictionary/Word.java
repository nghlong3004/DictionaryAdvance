package model.dictionary;

import java.util.ArrayList;
import java.util.List;

public class Word {
  private String word;
  private String meaning;
  private String antonym;
  private String synonym;
  private String description;
  private String pronounce;
  private String part_of_speech;
  private String languaged;
  private String history;

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getMeaning() {
    return meaning;
  }

  public void setMeaning(String meaning) {
    this.meaning = meaning;
  }

  public String getAntonym() {
    return antonym;
  }

  public void setAntonym(String antonym) {
    this.antonym = antonym;
  }

  public String getSynonym() {
    return synonym;
  }

  public void setSynonym(String synonym) {
    this.synonym = synonym;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPronounce() {
    return pronounce;
  }

  public void setPronounce(String pronounce) {
    this.pronounce = pronounce;
  }

  public String getPartOfSpeech() {
    return part_of_speech;
  }

  public void setPartOfSpeech(String partOfSpeech) {
    this.part_of_speech = partOfSpeech;
  }

  public String getLanguaged() {
    return languaged;
  }

  public void setLanguaged(String languaged) {
    this.languaged = languaged;
  }

  public String getHistory() {
    return history;
  }

  public void setHistory(String history) {
    this.history = history;
  }
  public List<Object> getAttributesAsList() {
    List<Object> attributes = new ArrayList<>();
    attributes.add(getWord());
    attributes.add(getMeaning());
    attributes.add(getAntonym());
    attributes.add(getSynonym());
    attributes.add(getDescription());
    attributes.add(getPronounce());
    attributes.add(getPartOfSpeech());
    attributes.add(getLanguaged());
    attributes.add(getHistory());
    return attributes;
  }



}
