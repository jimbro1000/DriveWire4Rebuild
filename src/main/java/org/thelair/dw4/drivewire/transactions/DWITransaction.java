package org.thelair.dw4.drivewire.transactions;

/**
 * Process a single transaction type.
 */
public interface DWITransaction {
  /**
   * Handle request from client.
   *
   * @param data byte array of request data
   * @return expect async response
   */
  boolean handleRequest(int[] data);
}
