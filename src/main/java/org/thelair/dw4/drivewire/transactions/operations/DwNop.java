package org.thelair.dw4.drivewire.transactions.operations;

/**
 * No operation (NOP).
 */
public class DwNop extends BaseOp implements Operation {
  /**
   * Process operation.
   *
   * @param data operation message data
   */
  @Override
  public void process(final int[] data) {
    // no operation
  }
}
