package org.multithreading.models;

import org.multithreading.constants.TaxConstants;
import org.multithreading.enums.ItemType;

/**
 * Class for RawItem Type.
 */
public class RawItem extends Item {
  /**
   * Instantiates a new Raw item.
   *
   * @param itemName     the item name
   * @param itemType     the item type
   * @param itemPrice    the item price
   * @param itemQuantity the item quantity
   */
  public RawItem(final String itemName, final ItemType itemType,
                 final double itemPrice, final int itemQuantity) {
    super(itemName, itemType, itemPrice, itemQuantity);
  }
  
  @Override
  public void calculateTax() {
    double tax;
    //12.5% on cost
    tax = TaxConstants.RAW_TYPE_TAX_PERCENTAGE * itemPrice;
    //converting into price to percentage
    tax /= 100.0;
    itemTax = tax;
  }
}
