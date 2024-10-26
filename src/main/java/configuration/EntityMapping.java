package configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EntityMapping {

  private String tableName;
  private Map<String, String> fieldToColumnMapping = new HashMap<>();

  public EntityMapping(String tableName) {
    this.tableName = tableName;
  }

  public void addFieldMapping(String fieldName, String columnName) {
    fieldToColumnMapping.put(fieldName, columnName);
  }

  public String getTableName() {
    return tableName;
  }

  public String getColumnForField(String fieldName) {
    return fieldToColumnMapping.get(fieldName);
  }

  public Map<String, String> getFieldToColumnMapping() {
    return fieldToColumnMapping;
  }

  public void finalizeMappings() {
    fieldToColumnMapping = Collections.unmodifiableMap(fieldToColumnMapping);
  }

}
