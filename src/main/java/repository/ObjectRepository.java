package repository;

import model.DataSource;

public class ObjectRepository {
	private static final DataSource DATASOURCE = new DataSource();
	private static final AccountRepository ACCOUNT = AccountRepository.getInstance(DATASOURCE);
	private DictionaryRepository dictionary;
	
	public void dictionaryOpen() {
		dictionary = DictionaryRepository.getInstace(DATASOURCE);
	}
	
	public AccountRepository getAccount() {
		return ACCOUNT;
	}
	public DictionaryRepository getDictionary() {
		return dictionary;
	}

	
	
}
