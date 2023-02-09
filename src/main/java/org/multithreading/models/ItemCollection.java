package org.multithreading.models;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemCollection is the collection of items.
 */
public class ItemCollection {
  /**
   * stores item collection.
   */
  private final List<Item> collection = new ArrayList<>();

  /**
   * Add item.
   *
   * @param item the item
   */
  public void addItem(final Item item) {
    collection.add(item);
  }

  /**
   * Gets item collection.
   *
   * @return the item collection
   */
  public List<Item> getItemCollection() {
    return collection;
  }

}
