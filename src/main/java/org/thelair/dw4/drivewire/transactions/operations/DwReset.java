package org.thelair.dw4.drivewire.transactions.operations;

/**
 * Perform reset of statistics and flush drive caches.
 */
public class DwReset extends BaseOp implements Operation {
  /**
   * Process operation.
   *
   * @param data operation message data
   */
  @Override
  public void process(final int[] data) {
    final int code = data[0];

  }
}
