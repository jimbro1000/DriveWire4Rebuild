package org.thelair.dw4.drivewire.ports.serial;

import com.fazecast.jSerialComm.SerialPort;
import org.thelair.dw4.drivewire.ports.serial.hardware.DWISerial;
import org.thelair.dw4.drivewire.ports.serial.hardware.JWSerial;

/**
 * Abstraction of serial port hardware.
 */
public class SerialPortHardware {
  /**
   * Match serial port to name.
   * @param portName required port name
   * @return SerialPort
   */
  public DWISerial getPortByName(final String portName) {
    final SerialPort[] ports = SerialPort.getCommPorts();
    SerialPort matchedPort = null;
    for (final SerialPort port : ports) {
      if (port.getSystemPortName().contains(portName)) {
        matchedPort = port;
      }
    }
    return new JWSerial(matchedPort);
  }
}
