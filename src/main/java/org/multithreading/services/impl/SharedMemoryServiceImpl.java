package org.multithreading.services.impl;

import lombok.extern.log4j.Log4j2;
import org.multithreading.constants.ExceptionMessageConstants;
import org.multithreading.constants.MemoryConstants;
import org.multithreading.exceptions.ConsumerException;
import org.multithreading.models.Item;
import org.multithreading.models.SharedMemory;
import org.multithreading.services.SharedMemoryService;

/**
 * The type Shared memory service.
 */
@Log4j2
public class SharedMemoryServiceImpl implements SharedMemoryService {
  /**
   * The Completed.
   */
  private boolean completed;
  /**
   * The Thread count.
   */
  private int threadCount;
  /**
   * The Shared memory.
   */
  private final SharedMemory sharedMemory;

  /**
   * Instantiates a new Shared memory service.
   *
   * @param sharedMemory the shared memory
   */
  public SharedMemoryServiceImpl(final SharedMemory sharedMemory) {
    this.sharedMemory = sharedMemory;
  }

  @Override
  public void setCompleted() {
    threadCount++;
    completed = threadCount == MemoryConstants.PRODUCER_THREAD_COUNT;
    notifyAll();
  }

  @Override
  public void put(final Item item) {
    try {
      while (sharedMemory.getItemBuffer().size() == MemoryConstants.BUFFER_SIZE) {
        wait();
      }
      sharedMemory.getItemBuffer().add(item);
    } catch (Exception e) {
      log.error(e);
    } finally {
      notifyAll();
    }
  }

  @Override
  public Item get() {
    try {
      while (!completed && sharedMemory.getItemBuffer().size() == 0) {
        wait();
      }
      if (sharedMemory.getItemBuffer().size() > 0) {
        return sharedMemory.getItemBuffer().remove(0);
      }
      throw new ConsumerException(ExceptionMessageConstants.ALL_RECORD_CONSUMED);
    } catch (Exception e) {
      throw new ConsumerException(ExceptionMessageConstants.ALL_RECORD_CONSUMED);
    } finally {
      notifyAll();
    }
  }
}
