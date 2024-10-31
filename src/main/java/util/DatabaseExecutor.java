package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import configuration.DatabaseConfiguration;

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

  public List<List<Object>> execute(String query) {
    LOGGER.info("DatabaseExecutor::Execute " + query);
    try (Connection c = getConnection()) {
      if (c != null) {
        try (Statement statement = c.createStatement()) {
          try (ResultSet resultSet = statement.executeQuery(query)) {
            ResultSetMetaData md = resultSet.getMetaData();
            int numCols = md.getColumnCount();
            List<String> colNames = IntStream.range(0, numCols).mapToObj(i -> {
              try {
                return md.getColumnName(i + 1);
              } catch (SQLException e) {
                LOGGER.error("DatabaseExecutor::Error ResultSetMetaData get column name!", e);
                return "?";
              }
            }).collect(Collectors.toList());
            List<List<Object>> result = new ArrayList<List<Object>>();
            List<Object> nameCol = new ArrayList<Object>();
            colNames.forEach(value -> {
              nameCol.add(toCamelCase(value));
            });
            result.add(nameCol);
            while (resultSet.next()) {
              List<Object> row = new ArrayList<Object>();
              colNames.forEach(cn -> {
                try {
                  row.add(resultSet.getObject(cn));
                } catch (SQLException e) {
                  e.printStackTrace();
                }
              });
              LOGGER.info(String.format("DatabaseExecutor::%s It's been successful!",
                  query.substring(0, query.indexOf(' '))));
              result.add(row);
            }
            return result;
          } catch (Exception e) {
            LOGGER.error("DatabaseExecutor::Error result statement executing query", e);
          }
        } catch (Exception e) {
          LOGGER.error("DatabaseExecutor::Error statement executing query", e);
        }
      }
    } catch (Exception e) {
      LOGGER.error("DatabaseExecutor::Error executing query", e);
    }

    return new ArrayList<List<Object>>();
  }

  private String toCamelCase(String phrase) {
    while (phrase.contains("_")) {
      phrase = phrase.replaceFirst("_[a-z]",
          String.valueOf(Character.toUpperCase(phrase.charAt(phrase.indexOf("_") + 1))));
    }
    return phrase;
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
