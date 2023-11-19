package org.thelair.dw4.drivewire.ports;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thelair.dw4.drivewire.ports.serial.DWSerialPort;
import org.thelair.dw4.drivewire.ports.serial.SerialPortHardware;
import org.thelair.dw4.drivewire.ports.tcp.DWTcpPort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * DriveWire port manager.
 */
public class DWPortManager implements DWIPortManager {
  /**
   * Log appender.
   */
  private static final Logger LOGGER
      = LogManager.getLogger(DWPortManager.class);
  /**
   * ports mapped by internal id.
   */
  private final Map<Integer, DWIPort> portMap = new HashMap<>();
  /**
   * set of open ports.
   */
  private final Set<DWIPort> openPorts = new HashSet<>();
  /**
   * next valid port id.
   */
  private int nextPort = 1;
  /**
   * Count of all defined ports.
   * @return number of ports (open and closed)
   */
  @Override
  public int getPortCount() {
    return portMap.keySet().size();
  }

  /**
   * Count of all closed ports.
   * @return number of closed ports
   */
  @Override
  public int getClosedPortCount() {
    return getPortCount() - getOpenPortCount();
  }

  /**
   * Count of all open ports.
   * @return number of open ports
   */
  @Override
  public int getOpenPortCount() {
    return openPorts.size();
  }

  /**
   * Register port as open.
   * @param port generic port
   */
  @Override
  public void registerOpenPort(final DWIPort port) {
    // flag port as open
    openPorts.add(port);
    LOGGER.info("port opened " + port.identifyPort());
  }

  /**
   * Register port as closed.
   * @param port generic port
   */
  @Override
  public void registerClosedPort(final DWIPort port) {
    // flag port as closed
    openPorts.remove(port);
    LOGGER.info("port closed " + port.identifyPort());
  }

  /**
   * Create new port instance of the given type.
   * @param portType type of port to create
   * @return port object
   */
  @Override
  public DWIPort createPortInstance(
      final DWIPortType.DWPortTypeIdentity portType
  ) {
    LOGGER.info("creating port to spec " + portType.name());
    final DWIPort result = switch (portType) {
      case TCP_PORT -> new DWTcpPort();
      case SERIAL_PORT -> new DWSerialPort(
          this, nextPort, new SerialPortHardware()
      );
      default -> new DWNullPort(this, nextPort);
    };
    LOGGER.info("port built on id " + nextPort);
    portMap.put(nextPort, result);
    findNextPort();
    LOGGER.info("next available port id " + nextPort);
    return result;
  }

  /**
   * Destroy the given port.
   * @param port generic closed port
   */
  @Override
  public void disposePort(final DWIPort port) {
    final int portId = port.identifyPort();
    LOGGER.info("disposing of port " + portId);
    if (openPorts.contains(port)) {
      port.closePort();
    }
    portMap.remove(port.identifyPort());
    LOGGER.info("disposed of port " + portId);
    if (portId < nextPort) {
      nextPort = portId;
    }
  }

  private void findNextPort() {
    nextPort++;
    while (portMap.containsKey(nextPort)) {
      nextPort++;
    }
  }
}
