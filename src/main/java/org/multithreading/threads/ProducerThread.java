package org.multithreading.threads;

import lombok.extern.log4j.Log4j2;
import org.multithreading.constants.ExceptionMessageConstants;
import org.multithreading.exceptions.DatabaseException;
import org.multithreading.models.Item;
import org.multithreading.repo.ItemRepoService;
import org.multithreading.services.SharedMemoryService;

/**
 * The type Producer thread.
 */
@Log4j2
public class ProducerThread extends Thread {
  /**
   * The Shared memory service.
   */
  private final SharedMemoryService sharedMemoryService;
  /**
   * The Item repo service.
   */
  private final ItemRepoService itemRepoService;

  /**
   * Instantiates a new Producer thread.
   *
   * @param sharedMemoryService the shared memory service
   * @param itemRepoService     the item repo service
   */
  public ProducerThread(final SharedMemoryService sharedMemoryService,
                        final ItemRepoService itemRepoService) {
    super();
    this.sharedMemoryService = sharedMemoryService;
    this.itemRepoService = itemRepoService;
  }

  @Override
  public void run() {
    try {
      while (true) {
        Item item;
        synchronized (itemRepoService) {
          item = itemRepoService.getNextItem();
        }
        synchronized (sharedMemoryService) {
          sharedMemoryService.put(item);
          log.info(item);
        }
      }
    } catch (DatabaseException e) {
      if (ExceptionMessageConstants.ALL_RECORD_FETCHED.equals(e.getMessage())) {
        synchronized (sharedMemoryService) {
          sharedMemoryService.setCompleted();
          log.info("All Produced");
        }
        return;
      }
      log.error(e);
    } catch (Exception e) {
      log.error(e);
    }
  }
}
