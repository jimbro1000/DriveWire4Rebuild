package org.thelair.dw4.drivewire.ports.serial;

import com.fazecast.jSerialComm.*;
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

  private SerialPort comPort;

  private final SerialPortHardware portHandler;

  private int portId;

  /**
   * Create serial port with reference to manager.
   * @param manager port manager handling this port
   */
  public DWSerialPort(final DWIPortManager manager, final int port, final SerialPortHardware hardPorts) {
    this.portManager = manager;
    this.portId = port;
    this.portHandler = hardPorts;
  }
  @Override
  public void openWith(final BasePortDef port)
      throws InvalidPortTypeDefinition {
    this.portDef = validatePortDef(port);
    this.portDef.setPortId(portId);
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
    if (port.getClass() != SerialPortDef.class) {
      throw new InvalidPortTypeDefinition("Invalid serial port definition",
          port);
    }
    SerialPortDef serialDef = (SerialPortDef) port;
    comPort = matchPort(serialDef.getPortName());
    return serialDef;
  }

  private SerialPort matchPort(final String portName) throws InvalidPortTypeDefinition {
    SerialPort matchedPort = portHandler.getPortByName(portName);
    if (matchedPort == null) {
      throw new InvalidPortTypeDefinition("named port is not available", this.portDef);
    }
    return matchedPort;
  }
}
