package org.multithreading.exceptions;

import org.multithreading.enums.ExceptionCode;

/**
 * The type Database exception.
 */
public class ConsumerException extends BaseApplicationException {
  /**
   * Instantiates a new Database exception.
   *
   * @param message the message
   */
  public ConsumerException(final String message) {
    super(message, ExceptionCode.CONSUMER_ERROR);
  }
}
