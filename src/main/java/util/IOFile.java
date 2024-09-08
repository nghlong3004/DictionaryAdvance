package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import model.User;

public class IOFile {
	
	public IOFile() {
		super();
	}
	public void outputFromFile(String outputFile, TreeMap<String, String> dataFile) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile), 1 << 16)) {
	        for (Map.Entry<String, String> entry : dataFile.entrySet()) {
	            StringBuilder sb = new StringBuilder();
	            sb.append(entry.getKey() + " <").append(entry.getValue()).append('\n');
	            writer.write(sb.toString());
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public void outputFromFile(String outputFile, User dataFile) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile), 1 << 16)) {
	        StringBuilder sb = new StringBuilder();
	        sb.append(dataFile.getName() + " <").append(dataFile.getPassword()).append('\n');
	        writer.write(sb.toString());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public void inputFromFile(String inputFile, TreeMap<String, String> dataFile) {
	    try (Stream<String> lines = Files.lines(Paths.get(inputFile))) {
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
	}
	public void inputFromFile(String inputFile, User dataFile) {
	    try (Stream<String> lines = Files.lines(Paths.get(inputFile))) {
	        lines.forEach(line -> {
	            int splitIndex = line.indexOf('<');
	            if (splitIndex != -1) {
	                String key = line.substring(0, splitIndex).trim();
	                key = key.substring(0, 1) + key.substring(1).toLowerCase();
	                String value = line.substring(splitIndex + 1).trim();
	                dataFile.name(key);
	                dataFile.password(value);
	            }
 
	        });
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
