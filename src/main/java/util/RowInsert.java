package util;

import java.lang.reflect.Field;
import configuration.EntityMapping;
import configuration.MappingConfiguration;

public class RowInsert {
  public static String generateInsertSQL(Object entity) throws IllegalAccessException {
    Class<?> clazz = entity.getClass();

    EntityMapping mapping = MappingConfiguration.getMapping(clazz);
    if (mapping == null) {
      throw new RuntimeException("No mapping found for class: " + clazz.getName());
    }

    String tableName = mapping.getTableName();
    StringBuilder columns = new StringBuilder();
    StringBuilder values = new StringBuilder();

    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);
      String columnName = mapping.getColumnForField(field.getName());
      if (columnName != null) {
        System.out.println(field.getName());
        columns.append(columnName).append(", ");
        values.append("?").append(", ");
      }
    }

    if (columns.length() > 0) {
      columns.setLength(columns.length() - 2);
    }
    if (values.length() > 0) {
      values.setLength(values.length() - 2);
    }

    return String.format("INSERT INTO %s (%s) VALUES (%s);", tableName, columns.toString(),
        values.toString());
  }

}
