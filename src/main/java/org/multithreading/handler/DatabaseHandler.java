package org.multithreading.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import org.multithreading.config.DatabaseConfig;
import org.multithreading.constants.ExceptionMessage;
import org.multithreading.exceptions.DatabaseException;

/**
 * The type Database handler.
 */
public class DatabaseHandler {
  /**
   * The Database config.
   */
  private final DatabaseConfig databaseConfig;
  /**
   * The Con.
   */
  private Connection con;

  /**
   * Instantiates a new Database handler.
   *
   * @param dbConfig the db config
   */
  public DatabaseHandler(final DatabaseConfig dbConfig) {
    this.databaseConfig = dbConfig;
    try {
      con = DriverManager.getConnection(databaseConfig.getUrl(),
          databaseConfig.getUsername(), databaseConfig.getPassword());
    } catch (Exception e) {
      throw new DatabaseException(ExceptionMessage.CONNECTION_ERROR);
    }
  }

  /**
   * Gets connection.
   *
   * @return the connection
   */
  public Connection getConnection() {
    return con;
  }
}
