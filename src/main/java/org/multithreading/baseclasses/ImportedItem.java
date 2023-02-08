package org.multithreading.baseclasses;

import lombok.ToString;
import org.multithreading.constants.TaxConstants;
import org.multithreading.enums.ItemType;

/**
 * Class for ImportedItem Type.
 */
@ToString
public class ImportedItem extends Item {
  /**
   * The Item tax.
   */
  private final double itemTax;

  /**
   * Instantiates a new Imported item.
   *
   * @param itemName     the item name
   * @param itemType     the item type
   * @param itemPrice    the item price
   * @param itemQuantity the item quantity
   */
  public ImportedItem(final String itemName, final ItemType itemType,
                      final double itemPrice, final int itemQuantity) {
    super(itemName, itemType, itemPrice, itemQuantity);
    itemTax = this.calculateTax();
  }

  /**
   * Used to calculate tax to imported items.
   *
   * @return return tax
   */
  protected double calculateTax() {
    double tax;
    //10% import duty + tax 5rs if price under 100 after tax and import duty on cost or
    //+ 10rs if 100 to 200 else + 5%
    tax = TaxConstants.IMPORT_DUTY_PERCENTAGE * itemPrice;
    tax /= 100.0;
    if (tax + itemPrice <= TaxConstants.LIMIT_100) {
      tax += TaxConstants.SURCHARGE_TAX_COST_BELOW_100;
    } else if (tax + itemPrice <= TaxConstants.LIMIT_200) {
      tax += TaxConstants.SURCHARGE_TAX_COST_ABOVE_100_BELOW_200;
    } else {
      tax += (TaxConstants.SURCHARGE_PERCENTAGE * (itemPrice + tax)) / 100.0;
    }
    return tax;
  }
}
