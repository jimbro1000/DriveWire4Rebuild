package org.thelair.dw4.drivewire;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thelair.dw4.drivewire.ports.DWIPort;
import org.thelair.dw4.drivewire.ports.DWIPortManager;
import org.thelair.dw4.drivewire.ports.DWIPortType;
import org.thelair.dw4.drivewire.ports.InvalidPortTypeDefinition;
import org.thelair.dw4.drivewire.ports.serial.SerialPortDef;

/**
 * Core application to DriveWire.
 */
public class DWCore {
  /**
   * Log appender.
   */
  private static final Logger LOGGER = LogManager.getLogger(DWCore.class);

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
   * default serial port - may be disabled in configuration.
   */
  private DWIPort serial;

  /**
   * Create core service.
   * @param manager port manager
   */
  public DWCore(final DWIPortManager manager) {
    this.portManager = manager;
    LOGGER.info("Initialised core");
    testPorts();
  }

  private void testPorts() {
    LOGGER.info("creating serial port");
    serial = portManager.createPortInstance(
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
