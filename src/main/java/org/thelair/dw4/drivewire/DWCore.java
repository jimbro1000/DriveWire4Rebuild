package org.thelair.dw4.drivewire;

import org.thelair.dw4.drivewire.ports.*;
import org.thelair.dw4.drivewire.ports.serial.*;

/**
 * Core application to DriveWire.
 */
public class DWCore {
  private final DWIPortManager portManager;

  private DWIPort serial;

  public DWCore(final DWIPortManager manager) {
    this.portManager = manager;
    testPorts();
  }

  private void testPorts() {
    serial = portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.SERIAL_PORT);
    try {
      serial.openWith(new SerialPortDef(2400, 8, 1, 0, "com1",1));
    } catch (InvalidPortTypeDefinition ex) {
      System.out.println(ex.getMessage());
    }
  }
}
