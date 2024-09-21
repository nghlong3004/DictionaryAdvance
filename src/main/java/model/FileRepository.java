package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class FileRepository implements Repository<String>{
	private String startPath;
	public FileRepository(String startPath) {
		this.startPath = startPath;
	}
	
	@Override
	public void save(String data, Account account) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(startPath + data), 1 << 16)) {
			for (Map.Entry<String, String> entry : account.getId().entrySet()) {
	            StringBuilder sb = new StringBuilder();
	            sb.append(entry.getKey() + " <").append(entry.getValue()).append('\n');
	            writer.write(sb.toString());
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
	@Override
	public void save(String data, String idUser) {
		try (FileWriter writer = new FileWriter(startPath + data)) {
			writer.write(idUser);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public TreeMap<String, String> load() {
		TreeMap<String, String> dataFile = new TreeMap<String, String>();
		try (Stream<String> lines = Files.lines(Paths.get(startPath + "account.txt"))) {
	        lines.forEach(line -> {
	            int splitIndex = line.indexOf('<');
	            if (splitIndex != -1) {
	                String key = line.substring(0, splitIndex).trim();
	                key = key.substring(0, 1) + key.substring(1).toLowerCase();
	                String value = line.substring(splitIndex + 1).trim();
	                dataFile.put(key, value);
	            }
 
	        });
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return dataFile;
	}
	@Override
	public String loadUser() {
		String idUser = null;
		ArrayList<String> array = new ArrayList<String>();
		try (Stream<String> lines = Files.lines(Paths.get(startPath + "user.txt"))) {
	        lines.forEach(line -> {
	            array.add(line);
	        });
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		if(!array.isEmpty()) {
			idUser = array.get(0);
		}
		return idUser;
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	

}
