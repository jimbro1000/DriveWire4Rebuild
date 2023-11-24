package org.thelair.dw4.drivewire.transactions.operations;

/**
 * Initialise operation.
 * Requires client to announce capabilities and driver version
 */
public class DwInit extends BaseOp implements Operation {
  /**
   * Process operation.
   *
   * @param data operation message data
   */
  @Override
  public void process(final int[] data) {

  }
}
