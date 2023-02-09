package org.multithreading.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import lombok.Getter;
import org.multithreading.config.DatabaseConfig;
import org.multithreading.constants.ExceptionMessageConstants;
import org.multithreading.exceptions.DatabaseException;

/**
 * The type Database handler.
 */
@Getter
public class DatabaseHandler {
  /**
   * The Database config.
   */
  private final DatabaseConfig databaseConfig;
  /**
   * The Con.
   */
  private Connection connection;

  /**
   * Instantiates a new Database handler.
   *
   * @param dbConfig the db config
   */
  public DatabaseHandler(final DatabaseConfig dbConfig) {
    this.databaseConfig = dbConfig;
    try {
      connection = DriverManager.getConnection(databaseConfig.getUrl(),
          databaseConfig.getUsername(), databaseConfig.getPassword());
    } catch (Exception e) {
      throw new DatabaseException(ExceptionMessageConstants.CONNECTION_ERROR);
    }
  }
}
