package org.multithreading.exceptions;

import org.multithreading.enums.ExceptionCode;

/**
 * The type Database exception.
 */
public class DatabaseException extends BaseApplicationException {
  /**
   * Instantiates a new Database exception.
   *
   * @param message the message
   */
  public DatabaseException(final String message) {
    super(message, ExceptionCode.DB_ERROR);
  }
}
