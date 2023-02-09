package org.multithreading.models;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * The type Shared memory.
 */
@Getter
public class SharedMemory {
  /**
   * The Item buffer.
   */
  private List<Item> itemBuffer = new ArrayList<>();
}
