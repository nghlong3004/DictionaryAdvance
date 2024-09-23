package repository;

import java.io.BufferedReader;
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

public class FileRepository<T> implements Repository<T>{
	
	private String FILE_NAME = "";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @SuppressWarnings("unused")
	private Class<T> type;

    public FileRepository(String filePath, Class<T> type) {
    	FILE_NAME = filePath;
        this.type = type;
    }
    
    // path file in system
    private Path getPath() {
        return Paths.get("data", FILE_NAME);
    }

	@Override
	public List<T> loadUsers() {
        Path path = getPath();
        if (!Files.exists(path)) {
            System.out.println("File not found! : " + path);
            return new ArrayList<>();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(path)))) {
            Type accountListType = new TypeToken<List<User>>() {}.getType();
            return gson.fromJson(reader, accountListType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

	@Override
	public void saveUsers(List<T> users) {
        Path path = getPath();
        // create file if file is null
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // write users in file
        try (FileWriter writer = new FileWriter(path.toFile())) {
            gson.toJson(users, writer);
            System.out.println("Complete!: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
}
