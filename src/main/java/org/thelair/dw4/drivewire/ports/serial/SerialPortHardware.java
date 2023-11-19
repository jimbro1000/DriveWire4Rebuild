package org.thelair.dw4.drivewire.ports.serial;

import com.fazecast.jSerialComm.SerialPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thelair.dw4.drivewire.ports.serial.hardware.DWISerial;
import org.thelair.dw4.drivewire.ports.serial.hardware.JWSerial;

/**
 * Abstraction of serial port hardware.
 */
public class SerialPortHardware {
  /**
   * Log appender.
   */
  private static final Logger LOGGER
      = LogManager.getLogger(SerialPortHardware.class);

  /**
   * Match serial port to name.
   *
   * @param portName required port name
   * @return SerialPort
   */
  public DWISerial getPortByName(final String portName) {
    final SerialPort[] ports = SerialPort.getCommPorts();
    SerialPort matchedPort = null;
    for (final SerialPort port : ports) {
      LOGGER.info("Potential port found " + port.getSystemPortName());
      if (port.getSystemPortName().contains(portName)) {
        LOGGER.info("Port matched " + portName
            + " with " + port.getSystemPortName());
        matchedPort = port;
      }
    }
    return new JWSerial(matchedPort);
  }
}
