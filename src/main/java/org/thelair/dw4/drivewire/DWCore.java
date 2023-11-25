package org.thelair.dw4.drivewire;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.thelair.dw4.drivewire.ports.DWIPort;
import org.thelair.dw4.drivewire.ports.DWIPortManager;
import org.thelair.dw4.drivewire.ports.DWIPortType;
import org.thelair.dw4.drivewire.ports.InvalidPortTypeDefinition;
import org.thelair.dw4.drivewire.ports.serial.SerialPortDef;
import org.thelair.dw4.drivewire.transactions.Transaction;
import org.thelair.dw4.drivewire.transactions.TransactionRouter;
import org.thelair.dw4.drivewire.transactions.operations.DwInit;
import org.thelair.dw4.drivewire.transactions.operations.DwNop;
import org.thelair.dw4.drivewire.transactions.operations.DwReset;
import org.thelair.dw4.drivewire.transactions.operations.DwTime;

/**
 * Core application to DriveWire.
 */
@Component
public class DWCore implements ApplicationListener<ApplicationReadyEvent> {
  /**
   * Log appender.
   */
  private static final Logger LOGGER = LogManager.getLogger(DWCore.class);
  /**
   * Enabled features.
   */
  private static final int DW4_FEATURES = 0x00;
  /**
   * Default serial baud rate of 2400.
   */
  public static final int DEFAULT_BAUD_RATE = 2400;
  /**
   * Default serial data bits.
   */
  public static final int DEFAULT_DATA_BITS = 8;
  /**
   * port manager.
   */
  private final DWIPortManager portManager;
  /**
   * transaction router.
   */
  private final TransactionRouter router;
  /**
   * client version.
   */
  @Getter
  @Setter
  private int clientVersion;

  /**
   * Create core service.
   * @param manager port manager
   */
  public DWCore(final DWIPortManager manager) {
    this.router = new TransactionRouter();
    this.portManager = manager;
    this.clientVersion = -1;
    LOGGER.info("Initialised core");
  }

  private void populateRouter() {
    router.registerOperation(Transaction.OP_RESET1, new DwReset());
    router.registerOperation(Transaction.OP_RESET2, new DwReset());
    router.registerOperation(Transaction.OP_RESET3, new DwReset());
    router.registerOperation(Transaction.OP_INIT, new DwInit());
    router.registerOperation(Transaction.OP_DWINIT, new DwInit());
    router.registerOperation(Transaction.OP_TIME, new DwTime());
    router.registerOperation(Transaction.OP_NOP, new DwNop());
  }

  /**
   * Handle application ready event.
   *
   * @param event ready event
   */
  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    populateRouter();
    testPorts();
  }

  /**
   * Report server capability.
   * @return register of enabled features
   */
  public int reportCapability() {
    LOGGER.info("Reported server features: " + DW4_FEATURES);
    return DW4_FEATURES;
  }

  private void testPorts() {
    LOGGER.info("creating port");
    final DWIPort serial = portManager.createPortInstance(
        DWIPortType.DWPortTypeIdentity.SERIAL_PORT
    );
    LOGGER.info("serial port " + serial.toString());
    try {
      serial.openWith(
          new SerialPortDef(DEFAULT_BAUD_RATE,
              DEFAULT_DATA_BITS,
              1,
              0,
              "com1",
              1)
      );
      LOGGER.info("port opened " + serial.getPortDefinition());
    } catch (InvalidPortTypeDefinition ex) {
      LOGGER.error("failed to open port " + ex.getMessage());
      System.out.println(ex.getMessage());
    }
  }
}
