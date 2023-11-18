package org.thelair.dw4.drivewire.ports;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.thelair.dw4.drivewire.ports.serial.SerialPortDef;
import org.thelair.dw4.drivewire.ports.tcp.TcpPortDef;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DWNullPortTest {
  @Mock
  private DWIPortManager portManager;

  @Test
  @DisplayName("It should always identify as a null port")
  public void identifyAsANullPort() {
    DWIPort port = new DWNullPort(null);
    assertEquals(0, port.identifyPort(), "should return null port value (0)");
  }

  @Test
  @DisplayName("It should register with its manager on open")
  public void registerWithManagerOnOpen() throws InvalidPortTypeDefinition {
    DWIPort port = new DWNullPort(portManager);
    port.openWith(null);
    verify(portManager, times(1)).registerOpenPort(port);
  }

  @Test
  @DisplayName("It should re-register with its manager on close")
  public void deregisterWithManagerOnClose() {
    DWIPort port = new DWNullPort(portManager);
    port.closePort();
    verify(portManager, times(1)).registerClosedPort(port);
  }

  @Test
  @DisplayName("It should accept a serial port definition")
  public void dontThrowExceptionOnSerialDefintion() {
    DWIPort port = new DWNullPort(null);
    assertDoesNotThrow(() -> port.setPortDef(new SerialPortDef(0, 0, 0, 0, 0)));
  }

  @Test
  @DisplayName("It should accept a serial port definition")
  public void dontThrowExceptionOnTcpDefintion() {
    DWIPort port = new DWNullPort(null);
    assertDoesNotThrow(() -> port.setPortDef(new TcpPortDef()));
  }
}
