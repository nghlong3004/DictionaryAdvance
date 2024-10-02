package util;

import controller.DictionaryController;
import controller.UserController;
import service.DictionaryServiceInterface;
import service.DictionaryService;
import service.AccountServiceInterface;
import service.AccountService;

public class ObjectContainer {

  private static final PropertyHelper PROPERTY_HELPER = new PropertyHelper();

  private final static AccountServiceInterface USER_SERVICE_INTERFACE =
      AccountService.getInstance(PROPERTY_HELPER);
  private final static UserController USER_CONTROLLER = new UserController(USER_SERVICE_INTERFACE);

  private final static DictionaryServiceInterface DICTIONARY_SERVICE_INTERFACE =
      DictionaryService.getInstance(PROPERTY_HELPER);
  private final static DictionaryController DICTIONARY_CONTROLLER =
      new DictionaryController(DICTIONARY_SERVICE_INTERFACE);

  public UserController getControllerInstance() {
    return USER_CONTROLLER;
  }

  public DictionaryController getAppController() {
    return DICTIONARY_CONTROLLER;
  }

}
