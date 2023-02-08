package org.multithreading.baseclasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.multithreading.enums.ItemType;

/**
 * This class is used to represent an item.
 */
@Getter
@ToString
@AllArgsConstructor
public class Item {
  /**
   * The Item name.
   */
  protected String itemName;
  /**
   * The Item type.
   */
  protected ItemType itemType;
  /**
   * The Item price.
   */
  protected double itemPrice;
  /**
   * The Item quantity.
   */
  protected int itemQuantity;

}
