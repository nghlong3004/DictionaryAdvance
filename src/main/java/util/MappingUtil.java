package util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import configuration.MappingConfiguration;

public class MappingUtil {

  private static final Map<Class<?>, MappingConfiguration> MAPPINGS = new HashMap<>();

  public static void registerMapping(Class<?> clazz, String tableName) {
    MappingConfiguration entityMapping = new MappingConfiguration(tableName);

    Field[] fields = clazz.getDeclaredFields();

    for (Field field : fields) {
      String fieldName = field.getName();
      String columnName = convertFieldToColumn(fieldName);
      entityMapping.addFieldMapping(fieldName, columnName);
    }
    entityMapping.finalizeMappings();
    register(clazz, entityMapping);
  }

  public static MappingConfiguration getMapping(Class<?> clazz) {
    return MAPPINGS.get(clazz);
  }

  private static void register(Class<?> clazz, MappingConfiguration entityMapping) {
    MAPPINGS.put(clazz, entityMapping);
  }

  private static String convertFieldToColumn(String fieldName) {
    return fieldName;
  }
}
