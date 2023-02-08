package org.multithreading.models;

import lombok.Getter;
import lombok.ToString;
import org.multithreading.enums.ItemType;

/**
 * This class is used to represent an item.
 */
@ToString
@Getter
public abstract class Item {
  /**
   * The Item name.
   */
  protected String itemName;
  /**
   * The Item price.
   */
  protected double itemPrice;
  /**
   * The Item tax.
   */
  protected double itemTax;
  /**
   * The Item quantity.
   */
  protected int itemQuantity;
  /**
   * The Item type.
   */
  protected ItemType itemType;

  /**
   * Calculate tax.
   */
  public abstract void calculateTax();

  /**
   * This Constructor is used to create item.
   *
   * @param itemName     Name of the item
   * @param itemType     Type of the item
   * @param itemPrice    Price of the item
   * @param itemQuantity Quantity of the item
   */
  protected Item(final String itemName, final ItemType itemType,
                 final double itemPrice, final int itemQuantity) {
    this.itemName = itemName;
    this.itemType = itemType;
    this.itemPrice = itemPrice;
    this.itemQuantity = itemQuantity;
    this.itemTax = 0;
  }

  /**
   * This method is used to create item according to Type.
   *
   * @param itemName     item name
   * @param itemType     item type
   * @param itemPrice    item price
   * @param itemQuantity item quantity
   * @return return instance of subclass according to class
   */
  public static Item createItem(final String itemName, final String itemType,
                                final double itemPrice, final int itemQuantity) {
    final ItemType type = ItemType.valueOf(itemType);
    switch (type) {
      case RAW:
        return new RawItem(itemName, type, itemPrice, itemQuantity);
      case MANUFACTURED:
        return new ManufacturedItem(itemName, type, itemPrice, itemQuantity);
      case IMPORTED:
        return new ImportedItem(itemName, type, itemPrice, itemQuantity);
      default:
        break;
    }
    return null;
  }
}
