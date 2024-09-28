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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class FileRepository<T> implements DataRepository<T>{
	
	private String fileUser, fileData, file;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Class<T> type;

    public FileRepository(String fileUser, String fileData, Class<T> type) {
    	this.fileUser = fileUser;
    	this.fileData = fileData;
    	this.type = type;
    	file = new String();
    }
    
    // path file in system
    private Path getPath() {
        return Paths.get("data", file);
    }

	@SuppressWarnings("hiding")
	@Override
	public <T, I> I load(Class <I> returnType) {
		List<T> datas = new ArrayList<T>();
		boolean isMap = true;
		file = fileData;
		if(returnType.isAssignableFrom(datas.getClass())) {
			isMap = false;
			file = fileUser;
		}
        Path path = getPath();
        if (!Files.exists(path)) {
            System.out.println("File not found! : " + path);
            datas = new ArrayList<>();
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(path)))) {
        	Type dataListType = TypeToken.getParameterized(List.class, type).getType();
            List<T> dataList = gson.fromJson(reader, dataListType);
            if(dataList == null) {
            	datas = new ArrayList<>();
            }
            else {
            	datas = dataList;
            }
        } catch (IOException e) {
            e.printStackTrace();
            datas = new ArrayList<>();
        }
        if(isMap) {
        	Map<String, T> map = new HashMap<String, T>();
        	for (T data : datas) {
				map.put(data.toString(), data);
			}
        	return returnType.cast(map);
        }
        else {
        	return returnType.cast(datas);
        }
    }

	@Override
	public void save(List<T> datas) {
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

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
}
