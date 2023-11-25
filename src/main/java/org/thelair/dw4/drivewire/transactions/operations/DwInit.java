package org.thelair.dw4.drivewire.transactions.operations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.thelair.dw4.drivewire.DWMessageEvent;

import static org.thelair.dw4.drivewire.transactions.Transaction.OP_INIT;

/**
 * Initialise operation.
 * Requires client to announce capabilities and driver version
 */
@Component
public class DwInit extends BaseOp implements Operation {
  /**
   * Log appender.
   */
  private static final Logger LOGGER = LogManager.getLogger(DwInit.class);
  /**
   * Process operation.
   *
   * @param data operation message data
   */
  @Override
  public void process(final int[] data) {
    if (data[0] == OP_INIT.getOpCode()) {
      LOGGER.info("DW3 client init");
      getController().setClientVersion(0);
      return;
    }
    LOGGER.info("DW4 client version byte: " + data[1]);
    getController().setClientVersion(data[1]);
    new DWMessageEvent(
        this,
        new int[] {this.getController().reportCapability()}
    );
  }
}
