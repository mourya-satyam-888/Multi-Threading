package org.multithreading;

import org.multithreading.controller.Application;

/**
 * The type Main.
 */
public class Main {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(final String[] args) {
    final Application application = new Application();
    application.run();
  }
}