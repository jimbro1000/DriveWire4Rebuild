package org.thelair.dw4.drivewire.transactions.operations;

/**
 * Interface for registering operations.
 */
public interface Operation {
  /**
   * Process operation.
   * @param data operation message data
   */
  void process(int[] data);
}
