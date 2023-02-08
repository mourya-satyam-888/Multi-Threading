package org.multithreading.handler;

import org.multithreading.baseclasses.Item;

/**
 * The interface Database handler.
 */
public interface DatabaseHandler {
  /**
   * Gets next item.
   *
   * @return the next item
   */
  Item getNextItem();
}
