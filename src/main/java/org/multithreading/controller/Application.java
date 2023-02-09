package org.multithreading.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.multithreading.config.DatabaseConfig;
import org.multithreading.constants.MemoryConstants;
import org.multithreading.handler.DatabaseHandler;
import org.multithreading.models.ItemCollection;
import org.multithreading.models.SharedMemory;
import org.multithreading.repo.ItemRepoService;
import org.multithreading.repo.impl.ItemRepoServiceImpl;
import org.multithreading.services.SharedMemoryService;
import org.multithreading.services.impl.SharedMemoryServiceImpl;
import org.multithreading.threads.ConsumerThread;
import org.multithreading.threads.ProducerThread;

/**
 * The type Application.
 */
@Log4j2
public class Application {
  /**
   * The Shared memory service.
   */
  private final SharedMemoryService sharedMemoryService;
  /**
   * The Shared memory.
   */
  private final SharedMemory sharedMemory;
  /**
   * The Database handler.
   */
  private final DatabaseHandler databaseHandler;
  /**
   * The Database config.
   */
  private final DatabaseConfig databaseConfig;
  /**
   * The Item repo service.
   */
  private final ItemRepoService itemRepoService;
  /**
   * The Item collection.
   */
  private final ItemCollection itemCollection;

  /**
   * Instantiates a new Application.
   */
  public Application() {
    sharedMemory = new SharedMemory();
    sharedMemoryService = new SharedMemoryServiceImpl(sharedMemory);
    databaseConfig = new DatabaseConfig();
    databaseHandler = new DatabaseHandler(databaseConfig);
    itemRepoService = new ItemRepoServiceImpl(databaseHandler);
    itemCollection = new ItemCollection();
  }

  /**
   * Run.
   */
  public void run() throws InterruptedException {
    final List<Thread> consumer = new ArrayList<>();
    final List<Thread> producer = new ArrayList<>();
    for (int i = 0; i < MemoryConstants.PRODUCER_THREAD_COUNT; i++) {
      producer.add(new ProducerThread(sharedMemoryService, itemRepoService));
      producer.get(i).start();
    }
    for (int i = 0; i < MemoryConstants.CONSUMER_THREAD_COUNT; i++) {
      consumer.add(new ConsumerThread(sharedMemoryService, itemCollection));
      consumer.get(i).start();
    }
    for (final Thread producerThread : producer) {
      producerThread.join();
    }
    for (final Thread consumerThread : consumer) {
      consumerThread.join();
    }
    log.info(itemCollection.getItemCollection().size());
    log.info(itemCollection.getItemCollection());
  }
}
