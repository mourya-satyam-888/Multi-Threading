package org.multithreading.handler.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.multithreading.baseclasses.Item;
import org.multithreading.constants.DatabaseQuery;
import org.multithreading.constants.ExceptionMessage;
import org.multithreading.dbconfig.DatabaseConfig;
import org.multithreading.encryptions.EncryptionUtils;
import org.multithreading.enums.ItemType;
import org.multithreading.exceptions.DatabaseException;
import org.multithreading.handler.DatabaseHandler;

/**
 * The type Database handler.
 */
public class DatabaseHandlerImpl implements DatabaseHandler {
  /**
   * The Database config.
   */
  private final DatabaseConfig databaseConfig;
  /**
   * The Con.
   */
  private Connection con;
  /**
   * The Result set.
   */
  private ResultSet resultSet;

  /**
   * Instantiates a new Database handler.
   *
   * @param dbConfig the db config
   */
  public DatabaseHandlerImpl(final DatabaseConfig dbConfig) {
    this.databaseConfig = dbConfig;
    try {
      con = DriverManager.getConnection(databaseConfig.getUrl(),
          databaseConfig.getUsername(), databaseConfig.getPassword());
      final PreparedStatement ps = con.prepareStatement(DatabaseQuery.SELECT_ITEM);
      resultSet = ps.executeQuery();
    } catch (Exception e) {
      throw new DatabaseException(ExceptionMessage.CONNECTION_ERROR);
    }
  }

  @Override
  public Item getNextItem() {
    try {
      if (resultSet.next()) {
        final String itemName = EncryptionUtils.decryptString(resultSet.getString("name"));
        final ItemType itemType = ItemType.valueOf(resultSet.getString("type"));
        final double itemPrice = resultSet.getDouble("price");
        final int itemQuantity = resultSet.getInt("quantity");
        return new Item(itemName, itemType, itemPrice, itemQuantity);
      } else {
        throw new DatabaseException(ExceptionMessage.ALL_RECORD_FETCHED);
      }
    } catch (Exception e) {
      throw new DatabaseException(e.getMessage());
    }
  }
}
