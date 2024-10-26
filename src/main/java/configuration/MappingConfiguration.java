package configuration;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MappingConfiguration {

  private static final Map<Class<?>, EntityMapping> MAPPINGS = new HashMap<>();

  public static void registerMapping(Class<?> clazz, String tableName) {
    EntityMapping entityMapping = new EntityMapping(tableName);

    Field[] fields = clazz.getDeclaredFields();

    for (Field field : fields) {
      String fieldName = field.getName();
      String columnName = convertFieldToColumn(fieldName);
      entityMapping.addFieldMapping(fieldName, columnName);
    }
    entityMapping.finalizeMappings();
    register(clazz, entityMapping);
  }

  public static EntityMapping getMapping(Class<?> clazz) {
    return MAPPINGS.get(clazz);
  }

  private static void register(Class<?> clazz, EntityMapping entityMapping) {
    MAPPINGS.put(clazz, entityMapping);
  }

  private static String convertFieldToColumn(String fieldName) {
    return fieldName;
  }
}
