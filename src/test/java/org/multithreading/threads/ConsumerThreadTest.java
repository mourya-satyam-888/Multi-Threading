package org.multithreading.threads;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.multithreading.constants.ExceptionMessageConstants;
import org.multithreading.exceptions.ConsumerException;
import org.multithreading.models.Item;
import org.multithreading.models.ItemCollection;
import org.multithreading.services.SharedMemoryService;

@ExtendWith(MockitoExtension.class)
class ConsumerThreadTest {
  @InjectMocks
  ConsumerThread consumerThread;
  @Mock
  SharedMemoryService sharedMemoryService;

  @Mock
  ItemCollection itemCollection;
  @Mock
  Item item;

  @Test
  void consumerTest() throws InterruptedException {
    Mockito.when(sharedMemoryService.get()).thenReturn(item)
        .thenThrow(new ConsumerException(ExceptionMessageConstants.ALL_RECORD_CONSUMED));
    Mockito.doNothing().when(item).calculateTax();
    Mockito.doNothing().when(itemCollection).addItem(Mockito.any());
    consumerThread.start();
    consumerThread.join();
    Mockito.verify(sharedMemoryService, Mockito.times(2)).get();
    Mockito.verify(item).calculateTax();
    Mockito.verify(itemCollection).addItem(Mockito.any());
  }
}