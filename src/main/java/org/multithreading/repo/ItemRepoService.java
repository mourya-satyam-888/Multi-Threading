package org.multithreading.repo;

import org.multithreading.models.Item;

/**
 * The interface Database handler.
 */
public interface ItemRepoService {
  /**
   * Gets next item.
   *
   * @return the next item
   */
  Item getNextItem();
}
