package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import configuration.FileConfiguration;
import model.dictionary.Word;
import model.user.User;
import util.EnumContainer;

public abstract class FileRepository {

  private FileConfiguration fileConfiguration;
  private Gson gson = new GsonBuilder().setPrettyPrinting().create();


  public FileRepository(FileConfiguration fileConfiguration) {
    this.fileConfiguration = fileConfiguration;
  }

  // path file in system
  private Path getPath() {
    String fileName = switch (EnumContainer.authenticationState) {
      case LOGIN -> fileConfiguration.getFileUserName();
      case LOGGED_IN -> fileConfiguration.getFileDictionaryName();
      default -> null;
    };
    return Paths.get("data", fileName);
  }

  public List<String[]> readFile() {
    Path path = getPath();
    String fileName = switch (EnumContainer.authenticationState) {
      case LOGIN -> fileConfiguration.getFileUserName();
      case LOGGED_IN -> fileConfiguration.getFileDictionaryName();
      default -> null;
    };
    if (!Files.exists(path)) {
      File newFile = new File("data\\" + fileName);
      try {
        newFile.createNewFile();
      } catch (IOException e) {
        System.out.println("File not creater!!");
        e.printStackTrace();
      }
      System.out.println("File not found! : " + path);
      return null;
    }
    System.out.println("Loading Complete : " + fileName);
    try (BufferedReader reader =
        new BufferedReader(new InputStreamReader(Files.newInputStream(path)))) {
      Type accountListType = null;
      switch (EnumContainer.authenticationState) {
        case LOGIN:
          accountListType = new TypeToken<List<User>>() {}.getType();
          break;
        case LOGGED_IN:
          accountListType = new TypeToken<List<Word>>() {}.getType();
          break;
        default:
          break;
      }
      List<String[]> account = gson.fromJson(reader, accountListType);
      if (account == null) {
        return new ArrayList<>();
      }
      return account;
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }

  }


  public void saveFile(List<String[]> data) {
    Path path = getPath();
    // create file if file is null
    try {
      Files.createDirectories(path.getParent());
    } catch (IOException e) {
      e.printStackTrace();
    }
    // write users in file
    try (FileWriter writer = new FileWriter(path.toFile())) {
      gson.toJson(data, writer);
      System.out.println("Complete!: " + path);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public void delete() {
    // TODO Auto-generated method stub

  }
}
