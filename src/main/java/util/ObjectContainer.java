package util;

import controller.DictionaryController;
import controller.UserController;
import model.DataSource;
import repository.AccountInterface;
import repository.AccountRepository;
import repository.DictionaryInterface;
import repository.DictionaryRepository;
import service.DictionaryServiceInterface;
import service.DictionaryService;
import service.UserServiceInterface;
import service.UserService;

public class ObjectContainer {
	
	private static final DataSource DATASOURCE = new DataSource();
	private static final AccountInterface ACCOUNT_INTERFACE = AccountRepository.getInstance(DATASOURCE);
	private static final DictionaryInterface DICTIONARY_INTERFACE = DictionaryRepository.getInstace(DATASOURCE);
	//User
	private final static UserServiceInterface USER_SERVICE_INTERFACE = UserService.getInstance(ACCOUNT_INTERFACE);
	private final static UserController USER_CONTROLLER = new UserController(USER_SERVICE_INTERFACE);
	//Dictionary
	private final static DictionaryServiceInterface DICTIONARY_SERVICE_INTERFACE = DictionaryService.getInstance(DICTIONARY_INTERFACE);
	private final static DictionaryController DICTIONARY_CONTROLLER = new DictionaryController(DICTIONARY_SERVICE_INTERFACE);
	
	public UserController getControllerInstance() {
		return USER_CONTROLLER;
	}
	public DictionaryController getAppController() {
		return DICTIONARY_CONTROLLER;
	}
	
}
