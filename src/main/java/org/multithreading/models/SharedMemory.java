package org.multithreading.models;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Shared memory.
 */
@Getter
@Setter
public class SharedMemory {
  /**
   * The Item buffer.
   */
  private List<Item> itemBuffer = new ArrayList<>();
}
