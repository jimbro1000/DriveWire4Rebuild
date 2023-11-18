package org.thelair.dw4.drivewire.ports.serial;

import com.fazecast.jSerialComm.SerialPort;

/**
 * Abstraction of serial port hardware.
 */
public class SerialPortHardware {
  /**
   * Match serial port to name.
   * @param portName required port name
   * @return SerialPort
   */
  public SerialPort getPortByName(final String portName) {
    SerialPort[] ports = SerialPort.getCommPorts();
    SerialPort matchedPort = null;
    for (SerialPort port : ports) {
      if (port.getSystemPortName().equals(portName)) {
        matchedPort = port;
      }
    }
    return matchedPort;
  }
}
