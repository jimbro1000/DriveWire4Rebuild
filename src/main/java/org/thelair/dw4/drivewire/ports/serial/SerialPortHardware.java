package org.thelair.dw4.drivewire.ports.serial;

import com.fazecast.jSerialComm.*;

public class SerialPortHardware {
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
