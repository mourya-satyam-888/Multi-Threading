package org.multithreading.repo.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.multithreading.constants.DatabaseQueryConstants;
import org.multithreading.constants.ExceptionMessageConstants;
import org.multithreading.exceptions.DatabaseException;
import org.multithreading.handler.DatabaseHandler;
import org.multithreading.models.Item;
import org.multithreading.repo.ItemRepoService;
import org.multithreading.util.EncryptionUtils;

/**
 * The type Database handler.
 */
public class ItemRepoServiceImpl implements ItemRepoService {
  /**
   * The Database handler.
   */
  private final DatabaseHandler databaseHandler;

  /**
   * The Result set.
   */
  private ResultSet resultSet;

  /**
   * Instantiates a new Database handler.
   *
   * @param dbHandler the db handler
   */
  public ItemRepoServiceImpl(final DatabaseHandler dbHandler) {
    this.databaseHandler = dbHandler;
    try {
      final PreparedStatement ps = databaseHandler.getConnection()
          .prepareStatement(DatabaseQueryConstants.SELECT_ITEM);
      resultSet = ps.executeQuery();
    } catch (Exception e) {
      throw new DatabaseException(ExceptionMessageConstants.CONNECTION_ERROR);
    }
  }

  @Override
  public Item getNextItem() {
    try {
      if (resultSet.next()) {
        final String itemName = EncryptionUtils.decryptString(resultSet.getString("name"));
        final String itemType = resultSet.getString("type");
        final double itemPrice = resultSet.getDouble("price");
        final int itemQuantity = resultSet.getInt("quantity");
        return Item.createItem(itemName, itemType, itemPrice, itemQuantity);
      } else {
        throw new DatabaseException(ExceptionMessageConstants.ALL_RECORD_FETCHED);
      }
    } catch (Exception e) {
      throw new DatabaseException(e.getMessage());
    }
  }
}
