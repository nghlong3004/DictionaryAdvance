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
import model.account.User;
import model.dictionary.Word;
import util.state.AppState;

public abstract class FileRepository {

  private String fileName;
  private Gson gson = new GsonBuilder().setPrettyPrinting().create();


  public FileRepository(String fileName) {
    this.fileName = fileName;
  }

  // path file in system
  private Path getPath() {
    return Paths.get("data", fileName);
  }

  public List<String[]> read() {
    Path path = getPath();
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
      switch (AppState.state) {
        case LOGIN:
          accountListType = new TypeToken<List<User>>() {}.getType();
          break;
        case IN_APP:
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


  public void save(List<String[]> data) {
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
