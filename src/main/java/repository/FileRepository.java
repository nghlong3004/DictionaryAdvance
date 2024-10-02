package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

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

  public JsonElement load() {
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
      JsonElement datas = gson.fromJson(reader, JsonElement.class);
      if (datas == null) {
        return null;
      }
      return datas;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

  }


  public void save(String datas) {
    Path path = getPath();
    // create file if file is null
    try {
      Files.createDirectories(path.getParent());
    } catch (IOException e) {
      e.printStackTrace();
    }
    // write users in file
    try (FileWriter writer = new FileWriter(path.toFile())) {
      gson.toJson(datas, writer);
      System.out.println("Complete!: " + path);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public void delete() {
    // TODO Auto-generated method stub

  }
}
