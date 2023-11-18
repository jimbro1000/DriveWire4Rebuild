package org.thelair.dw4.drivewire.ports.serial;

import org.thelair.dw4.drivewire.ports.BasePortDef;
import org.thelair.dw4.drivewire.ports.DWIPort;
import org.thelair.dw4.drivewire.ports.DWIPortManager;
import org.thelair.dw4.drivewire.ports.InvalidPortTypeDefinition;

/**
 * RS232 Serial port definition.
 */
public final class DWSerialPort implements DWIPort {
  /**
   * Serial port definition.
   */
  private SerialPortDef portDef;
  /**
   * Port manager.
   */
  private final DWIPortManager portManager;

  /**
   * Create serial port with reference to manager.
   * @param manager port manager handling this port
   */
  public DWSerialPort(final DWIPortManager manager) {
    this.portManager = manager;
  }
  @Override
  public void openWith(final BasePortDef port)
      throws InvalidPortTypeDefinition {
    this.portDef = validatePortDef(port);
    portManager.registerOpenPort(this);
  }

  @Override
  public void setPortDef(final BasePortDef port)
      throws InvalidPortTypeDefinition {
    this.portDef = validatePortDef(port);
  }

  @Override
  public int identifyPort() {
    return portDef.getPortId();
  }

  @Override
  public void closePort() {
    portManager.registerClosedPort(this);
  }

  private SerialPortDef validatePortDef(final BasePortDef port)
      throws InvalidPortTypeDefinition {
    if (port.getClass() == SerialPortDef.class) {
      return (SerialPortDef) port;
    } else {
      throw new InvalidPortTypeDefinition("Invalid serial port definition",
          port);
    }
  }
}
