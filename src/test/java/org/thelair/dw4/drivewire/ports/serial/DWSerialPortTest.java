package org.thelair.dw4.drivewire.ports.serial;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thelair.dw4.drivewire.ports.DWIPort;
import org.thelair.dw4.drivewire.ports.DWIPortManager;
import org.thelair.dw4.drivewire.ports.InvalidPortTypeDefinition;
import org.thelair.dw4.drivewire.ports.tcp.TcpPortDef;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DWSerialPortTest {
  @Mock
  private DWIPortManager portManager;

  private final SerialPortDef portDef = new SerialPortDef(2400, 1, 0, 1, 1);

  @Test
  @DisplayName("It should always identify as a serial port")
  public void identifyAsANullPort() throws InvalidPortTypeDefinition {
    DWIPort port = new DWSerialPort(null);
    port.setPortDef(portDef);
    assertEquals(1, port.identifyPort(), "should return serial port value (1)");
  }

  @Test
  @DisplayName("It should register with its manager on open")
  public void registerWithManagerOnOpen() throws InvalidPortTypeDefinition {
    DWIPort port = new DWSerialPort(portManager);
    port.openWith(portDef);
    verify(portManager, times(1)).registerOpenPort(port);
  }

  @Test
  @DisplayName("It should re-register with its manager on close")
  public void deregisterWithManagerOnClose() {
    DWIPort port = new DWSerialPort(portManager);
    port.closePort();
    verify(portManager, times(1)).registerClosedPort(port);
  }

  @Test
  @DisplayName("It should throw an exception if the port def is not for serial")
  public void throwExceptionOnNonSerialPort() {
    DWIPort port = new DWSerialPort(portManager);
    assertThrows(InvalidPortTypeDefinition.class,
        ()-> port.openWith(new TcpPortDef()));
  }
}
