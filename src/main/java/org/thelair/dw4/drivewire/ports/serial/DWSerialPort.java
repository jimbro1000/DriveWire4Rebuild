package org.thelair.dw4.drivewire.ports.serial;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thelair.dw4.drivewire.ports.BasePortDef;
import org.thelair.dw4.drivewire.ports.DWIPort;
import org.thelair.dw4.drivewire.ports.DWIPortManager;
import org.thelair.dw4.drivewire.ports.InvalidPortTypeDefinition;
import org.thelair.dw4.drivewire.ports.serial.hardware.DWISerial;

import java.util.Map;

/**
 * RS232 Serial port definition.
 */
public final class DWSerialPort implements DWIPort {
  /**
   * Log appender.
   */
  private static final Logger LOGGER = LogManager.getLogger(DWSerialPort.class);
  /**
   * Serial port definition.
   */
  private SerialPortDef portDef;
  /**
   * Port manager.
   */
  private final DWIPortManager portManager;
  /**
   * concrete com port object.
   */
  private DWISerial comPort;
  /**
   * Serial port handler.
   */
  private final SerialPortHardware portHandler;
  /**
   * Unique port identifier.
   */
  private final int portId;

  /**
   * Create serial port with reference to manager.
   * @param manager port manager handling this port
   * @param port identifier
   * @param hardPorts host serial hardware
   */
  public DWSerialPort(
      final DWIPortManager manager,
      final int port,
      final SerialPortHardware hardPorts
  ) {
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
      LOGGER.error("Invalid port definition provided for a serial port");
      throw new InvalidPortTypeDefinition("Invalid serial port definition",
          port);
    }
    final SerialPortDef serialDef = (SerialPortDef) port;
    comPort = matchPort(serialDef.getPortName());
    return serialDef;
  }

  private DWISerial matchPort(final String portName)
      throws InvalidPortTypeDefinition {
    final DWISerial matchedPort = portHandler.getPortByName(portName);
    if (matchedPort == null) {
      LOGGER.error("named serial port is not available");
      throw new InvalidPortTypeDefinition(
          "named port is not available", this.portDef
      );
    }
    return matchedPort;
  }

  /**
   * Get serialised port definition.
   *
   * @return port details
   */
  public String getPortDefinition() {
    StringBuilder stringDef = new StringBuilder();
    Map<String, Integer> def = portDef.getPortDetail();
    for (final String key:def.keySet()) {
      stringDef.append(key).append(" : ").append(def.get(key)).append(" | ");
    }
    return stringDef.toString();
  }
}
