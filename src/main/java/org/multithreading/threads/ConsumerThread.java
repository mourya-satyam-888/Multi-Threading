package org.multithreading.threads;

import lombok.extern.log4j.Log4j2;
import org.multithreading.exceptions.ConsumerException;
import org.multithreading.models.Item;
import org.multithreading.models.ItemCollection;
import org.multithreading.services.SharedMemoryService;

/**
 * The type Consumer thread.
 */
@Log4j2
public class ConsumerThread extends Thread {
  /**
   * The Shared memory service.
   */
  private final SharedMemoryService sharedMemoryService;
  /**
   * The Item collection.
   */
  private final ItemCollection itemCollection;

  /**
   * Instantiates a new Consumer thread.
   *
   * @param sharedMemoryService the shared memory service
   * @param itemCollection      the item collection
   */
  public ConsumerThread(final SharedMemoryService sharedMemoryService,
                        final ItemCollection itemCollection) {
    super();
    this.sharedMemoryService = sharedMemoryService;
    this.itemCollection = itemCollection;
  }

  @Override
  public void run() {
    try {
      while (true) {
        Item item;
        synchronized (sharedMemoryService) {
          item = sharedMemoryService.get();
        }
        item.calculateTax();
        synchronized (itemCollection) {
          itemCollection.addItem(item);
        }
        log.info(item);
      }
    } catch (ConsumerException e) {
      log.info("All Consumed");
    } catch (Exception e) {
      log.error(e);
    }
  }
}
