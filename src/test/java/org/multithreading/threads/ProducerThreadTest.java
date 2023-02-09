package org.multithreading.threads;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.multithreading.constants.ExceptionMessageConstants;
import org.multithreading.exceptions.DatabaseException;
import org.multithreading.models.Item;
import org.multithreading.repo.ItemRepoService;
import org.multithreading.services.SharedMemoryService;

@ExtendWith(MockitoExtension.class)
class ProducerThreadTest {
  @InjectMocks
  ProducerThread producerThread;
  @Mock
  SharedMemoryService sharedMemoryService;
  @Mock
  ItemRepoService itemRepoService;
  @Mock
  Item item;

  @Test
  void producerTest() throws InterruptedException {
    Mockito.when(itemRepoService.getNextItem()).thenReturn(item)
        .thenThrow(new DatabaseException(ExceptionMessageConstants.ALL_RECORD_FETCHED));
    Mockito.doNothing().when(sharedMemoryService).put(Mockito.any());
    Mockito.doNothing().when(sharedMemoryService).setCompleted();
    producerThread.start();
    producerThread.join();
    Mockito.verify(itemRepoService, Mockito.times(2)).getNextItem();
    Mockito.verify(sharedMemoryService).put(Mockito.any());
    Mockito.verify(sharedMemoryService).setCompleted();
  }
}