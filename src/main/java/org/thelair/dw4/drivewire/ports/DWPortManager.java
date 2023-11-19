package org.thelair.dw4.drivewire.ports;

import org.thelair.dw4.drivewire.ports.serial.DWSerialPort;
import org.thelair.dw4.drivewire.ports.serial.SerialPortHardware;
import org.thelair.dw4.drivewire.ports.tcp.DWTcpPort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DWPortManager implements DWIPortManager {
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
  }

  /**
   * Register port as closed.
   * @param port generic port
   */
  @Override
  public void registerClosedPort(final DWIPort port) {
    // flag port as closed
    openPorts.remove(port);
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
    final DWIPort result = switch (portType) {
      case TCP_PORT -> new DWTcpPort();
      case SERIAL_PORT -> new DWSerialPort(
          this, nextPort, new SerialPortHardware()
      );
      default -> new DWNullPort(this, nextPort);
    };
    portMap.put(nextPort, result);
    findNextPort();
    return result;
  }

  /**
   * Destroy the given port.
   * @param port generic closed port
   */
  @Override
  public void disposePort(final DWIPort port) {
    final int portId = port.identifyPort();
    if (openPorts.contains(port)) {
      port.closePort();
    }
    portMap.remove(port.identifyPort());
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
