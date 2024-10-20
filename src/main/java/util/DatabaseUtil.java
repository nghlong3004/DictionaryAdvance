package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import configuration.DatabaseConfiguration;

public class DatabaseUtil {

  private static final Logger LOGGER = Logger.getLogger(DatabaseUtil.class);
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
      LOGGER.info("DatabaseUtil::Log4j Setup ready");

    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.fatal("DatabaseUtil::Problem while setting up Log4j");
    }
  }

  public static Connection getConnection(DatabaseConfiguration dbConfig) {
    Connection c = null;
    try {
      Class.forName("org.postgresql.Driver");
      LOGGER.debug("DatabaseUtil::JDBC driver driver class loaded");
      c = DriverManager.getConnection(dbConfig.getDbUrl(), dbConfig.getDbUsername(),
          dbConfig.getDbPassword());
      if (c == null) {
        LOGGER.debug("DatabaseUtil::Connection false!");
      } else {
        LOGGER.info("DatabaseUtil::Connection Established!");
      }
    } catch (SQLException e) {
      LOGGER.debug("DatabaseUtil::error is getConnection in database " + e.getMessage());
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return c;
  }

  public static void update(DatabaseConfiguration dbConfig, String query) {
    Connection c = null;
    Statement st = null;
    try {
      c = getConnection(dbConfig);
      if (c != null) {
        st = c.createStatement();
        if (st != null) {
          st.executeUpdate(query);
        }
      }
    } catch (SQLException e) {
      LOGGER.debug("DatabaseUtil::" + query + e.getMessage());
    } finally {
      if (c != null)
        try {
          c.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      if (st != null)
        try {
          st.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }

  }

  public static Map<String, String> target(DatabaseConfiguration dbConfig, String query) {
    Connection c = null;
    Statement st = null;
    try {
      c = getConnection(dbConfig);
      if (c != null) {
        st = c.createStatement();
        if (st != null) {
          ResultSet rs = st.executeQuery(query);
          if (rs != null) {
            Map<String, String> mp = new HashMap<String, String>();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
              for (int i = 1; i <= columnCount; ++i) {
                mp.put(metaData.getColumnName(i), rs.getString(i));
              }
            }
            rs.close();
            return mp;
          }
        }
      }
    } catch (SQLException e) {
      LOGGER.debug("DatabaseUtil::" + query + e.getMessage());
    } finally {
      if (c != null)
        try {
          c.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      if (st != null)
        try {
          st.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }
    return null;
  }


  public static void close(Connection c) {
    try {
      if (c != null) {
        c.close();
        LOGGER.info("DatabaseUtil::" + "Database is close");
      }
    } catch (SQLException e) {
      LOGGER.debug("DatabaseUtil::" + "error close is class DatabaseUtil! " + e.getMessage());
    }
  }


}
