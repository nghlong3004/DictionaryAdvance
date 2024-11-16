package util;

import configuration.DatabaseConfiguration;
import configuration.FileConfiguration;

import controller.DictionaryController;
import controller.UserController;

import service.DictionaryService;
import service.UserService;

public class ObjectContainer {

  private static final PropertyHelper PROPERTY_HELPER = new PropertyHelper();

  private static final DatabaseConfiguration DATABASE_CONFIGURATION = new DatabaseConfiguration(
      PROPERTY_HELPER.getDbUrl(), PROPERTY_HELPER.getDbUsername(), PROPERTY_HELPER.getDbPassword());

  private static final FileConfiguration FILE_CONFIGURATION = new FileConfiguration(
      PROPERTY_HELPER.getFilePathUser(), PROPERTY_HELPER.getFilePathDictionary());

  private static final UserService USER_SERVICE = new UserService();

  private static final UserController USER_CONTROLLER = new UserController(USER_SERVICE);

  private static DictionaryService dictionaryService;
  private static DictionaryController dictionaryController;

  public static void loadDictionary() {
    if (dictionaryService == null) {
      dictionaryService = new DictionaryService();
    }
    if (dictionaryController == null) {
      dictionaryController = new DictionaryController(dictionaryService);
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
  
  public static PropertyHelper getPropertyHelper() {
    return PROPERTY_HELPER;
  }

}
