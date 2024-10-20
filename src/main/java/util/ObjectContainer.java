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

  private static DictionaryServiceInterface dictionaryServiceInterface;
  private static DictionaryController dictionaryController;

  public static void loadDictionary() {
    if (dictionaryServiceInterface == null) {
      dictionaryServiceInterface = DictionaryService.getInstance(PROPERTY_HELPER);
    }
    if (dictionaryController == null) {
      dictionaryController = new DictionaryController(dictionaryServiceInterface);
    }
  }

  public static UserController getControllerInstance() {
    return USER_CONTROLLER;
  }

  public static DictionaryController getAppController() {
    return dictionaryController;
  }

}
