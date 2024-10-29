package configuration;

public class FileConfiguration {
  private final String fileUserName;
  private final String fileDictionaryName;

  public FileConfiguration(String fileUserName, String fileDictionaryName) {
    this.fileUserName = fileUserName;
    this.fileDictionaryName = fileDictionaryName;
  }

  public String getFileUserName() {
    return fileUserName;
  }

  public String getFileDictionaryName() {
    return fileDictionaryName;
  }



}
