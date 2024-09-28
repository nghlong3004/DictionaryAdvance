package model.dictionary;

public class Word {
	// từ vựng
	private String key;
	// nghĩa của từ
	private String meaning;
	// phiên âm
	private String transcription;
	// Từ loại
	private String wordType;
	// mô tả từ đó
	private String description;
	// ví dụ về từ đó
	private String example;
	// từ đồng nghĩa
	private String Synonyms;
	// từ trái nghĩa
	private String Antonym;
	// thời gian tìm kiếm
	private String searchTime;
	// chuyen nganh
	private String specialized;
	
	@Override
	public String toString() {
		return this.key;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String vocabulary) {
		this.key = vocabulary;
	}
	public String getTranscription() {
		return transcription;
	}
	public void setTranscription(String transcription) {
		this.transcription = transcription;
	}
	public String getWordType() {
		return wordType;
	}
	public void setWordType(String wordType) {
		this.wordType = wordType;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	public String getSynonyms() {
		return Synonyms;
	}
	public void setSynonyms(String synonyms) {
		Synonyms = synonyms;
	}
	public String getAntonym() {
		return Antonym;
	}
	public void setAntonym(String antonym) {
		Antonym = antonym;
	}

	public String getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}

	public String getSpecialized() {
		return specialized;
	}

	public void setSpecialized(String specialized) {
		this.specialized = specialized;
	}
	
	
}
