package util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import configuration.DatabaseConfiguration;
import configuration.MappingConfiguration;

public class DatabaseExecutor {

  private static final Logger LOGGER = Logger.getLogger(DatabaseExecutor.class);
  // Setting logger
  static {
    try {
      // Create Layout out object
      PatternLayout layout = new PatternLayout();
      layout.setConversionPattern("[%-5l] %d{yyyy-MM-dd HH:mm:ss.SSS} %c{1}:%L - %m%n");
      // Create Appender object having layout object
      ConsoleAppender appender = new ConsoleAppender(layout);
      appender.setName("STDOUT");
      // Add Appender object to Logger object
      LOGGER.addAppender(appender);
      // Logger level to retrieve log message
      LOGGER.setLevel(Level.DEBUG);
      // Logger info
      LOGGER.info("DatabaseExecutor::Log4j Setup ready");

    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.fatal("DatabaseExecutor::Problem while setting up Log4j");
    }
  }

  private DatabaseConfiguration dbConfig;

  public DatabaseExecutor(DatabaseConfiguration dbConfig) {
    this.dbConfig = dbConfig;
  }

  private Connection getConnection() {
    Connection c = null;
    try {
      Class.forName("org.postgresql.Driver");
      LOGGER.info("DatabaseExecutor::JDBC driver driver class loaded");
      c = DriverManager.getConnection(dbConfig.getDbUrl(), dbConfig.getDbUsername(),
          dbConfig.getDbPassword());
      if (c == null) {
        LOGGER.debug("DatabaseExecutor::Connection false!");
      } else {
        LOGGER.info("DatabaseExecutor::Connection Established!");
      }
    } catch (SQLException e) {
      LOGGER.debug("DatabaseExecutor::Connection " + e.getMessage());
    } catch (ClassNotFoundException e) {
      LOGGER.debug("DatabaseExecutor::Connection " + e.getMessage());
    }
    return c;
  }

  public List<Object> execute(String query, final List<Object> params, Class<?> clazz) {
    LOGGER.info("DatabaseExecutor::Execute " + query);
    if (params == null) {
      LOGGER.debug("DatabaseExecutor::Params is null");
      return null;
    }
    try (Connection c = getConnection()) {
      if (c != null) {
        try (PreparedStatement statement = prepareStatementWithParams(c, query, params)) {
          if (query.trim().toUpperCase().startsWith("SELECT")) {
            return processSelectQuery(statement, clazz);
          } else {
            statement.executeUpdate();
            LOGGER.info("DatabaseExecutor::Non-SELECT query executed successfully");
          }
        }
      }
    } catch (Exception e) {
      LOGGER.error("DatabaseExecutor::Error executing query", e);
    }

    return Collections.emptyList();
  }

  private PreparedStatement prepareStatementWithParams(Connection connection, String query,
      List<Object> params) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(query);
    for (int i = 0; i < params.size(); ++i) {
      statement.setObject(i + 1, params.get(i));
    }
    return statement;
  }

  private List<Object> processSelectQuery(PreparedStatement statement, Class<?> clazz) {
    List<Object> rows = new ArrayList<>();
    MappingConfiguration mapping = MappingUtil.getMapping(clazz);

    if (mapping == null) {
      LOGGER.error("No mapping found for class: " + clazz.getName());
      return rows;
    }

    try (ResultSet rs = statement.executeQuery()) {
      while (rs.next()) {
        Object instance = mapResultSetToEntity(rs, clazz, mapping);
        rows.add(instance);
      }
    } catch (Exception e) {
      LOGGER.error("DatabaseExecutor::Select error", e);
    }
    return rows;
  }

  private Object mapResultSetToEntity(ResultSet rs, Class<?> clazz, MappingConfiguration mapping)
      throws Exception {
    Object instance = clazz.getDeclaredConstructor().newInstance();
    for (Map.Entry<String, String> entry : mapping.getFieldToColumnMapping().entrySet()) {
      String fieldName = entry.getKey();
      String columnName = entry.getValue();
      Field field = clazz.getDeclaredField(fieldName);
      field.setAccessible(true);
      Object value = rs.getObject(columnName);
      if (field.getType().equals(LocalDateTime.class) && value instanceof Timestamp) {
        value = ((Timestamp) value).toLocalDateTime();
      }
      field.set(instance, value);
    }
    return instance;
  }


  public void close(Connection c) {
    try {
      if (c != null) {
        c.close();
        LOGGER.info("DatabaseExecutor::Close " + "Database is close");
      }
    } catch (SQLException e) {
      LOGGER.debug(
          "DatabaseExecutor::Close" + " error close is class DatabaseUtil! " + e.getMessage());
    }
  }


}
