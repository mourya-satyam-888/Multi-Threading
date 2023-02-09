package org.multithreading.services;

import org.multithreading.models.Item;

/**
 * The interface Shared memory service.
 */
public interface SharedMemoryService {
  /**
   * Put.
   *
   * @param item the item
   */
  void put(Item item);

  /**
   * Get item.
   *
   * @return the item
   */
  Item get();

  /**
   * Sets completed.
   */
  void setCompleted();
}
