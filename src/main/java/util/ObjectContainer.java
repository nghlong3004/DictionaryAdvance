package util;

import configuration.DatabaseConfiguration;
import configuration.FileConfiguration;

import controller.DictionaryController;
import controller.UserController;

import service.DictionaryServiceInterface;
import service.DictionaryService;
import service.UserServiceInterface;
import service.UserService;

public class ObjectContainer {

  private static final PropertyHelper PROPERTY_HELPER = new PropertyHelper();

  private static final DatabaseConfiguration DATABASE_CONFIGURATION = new DatabaseConfiguration(
      PROPERTY_HELPER.getDbUrl(), PROPERTY_HELPER.getDbUsername(), PROPERTY_HELPER.getDbPassword());

  private static final FileConfiguration FILE_CONFIGURATION = new FileConfiguration(
      PROPERTY_HELPER.getFilePathUser(), PROPERTY_HELPER.getFilePathDictionary());

  private static final UserServiceInterface USER_SERVICE_INTERFACE = new UserService();

  private static final UserController USER_CONTROLLER = new UserController(USER_SERVICE_INTERFACE);

  private static DictionaryServiceInterface dictionaryServiceInterface;
  private static DictionaryController dictionaryController;

  public static void loadDictionary() {
    if (dictionaryServiceInterface == null) {
      dictionaryServiceInterface = new DictionaryService();
    }
    if (dictionaryController == null) {
      dictionaryController = new DictionaryController(dictionaryServiceInterface);
    }
  }

  public static DatabaseConfiguration getDatabaseConfiguration() {
    return DATABASE_CONFIGURATION;
  }

  public static FileConfiguration getFileConfiguration() {
    return FILE_CONFIGURATION;
  }

  public static UserController getUserController() {
    return USER_CONTROLLER;
  }

  public static DictionaryController getDictionaryController() {
    return dictionaryController;
  }

}
