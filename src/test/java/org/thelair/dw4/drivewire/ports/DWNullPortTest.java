package org.thelair.dw4.drivewire.ports;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.thelair.dw4.drivewire.ports.serial.SerialPortDef;
import org.thelair.dw4.drivewire.ports.tcp.TcpPortDef;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Describes drivewire null port.
 */
@ExtendWith(MockitoExtension.class)
public class DWNullPortTest {
  @Mock
  private DWIPortManager portManager;

  /**
   * It should identify as a null port.
   */
  @Test
  @DisplayName("It should always identify as a null port")
  public void identifyAsANullPort() {
    DWIPort port = new DWNullPort(null, 1);
    assertEquals(1, port.identifyPort(), "should return given port value");
  }

  /**
   * It should register with the open port pool on open.
   */
  @Test
  @DisplayName("It should register with its manager on open")
  public void registerWithManagerOnOpen() {
    DWIPort port = new DWNullPort(portManager, 1);
    try {
      port.openWith(null);
      verify(portManager, times(1)).registerOpenPort(port);
    } catch (InvalidPortTypeDefinition ex) {
      fail("it should not throw exception on open");
    }
  }

  /**
   * It should deregister with open pool when closed.
   */
  @Test
  @DisplayName("It should re-register with its manager on close")
  public void deregisterWithManagerOnClose() {
    DWIPort port = new DWNullPort(portManager, 1);
    port.closePort();
    verify(portManager, times(1)).registerClosedPort(port);
  }

  /**
   * It should not throw exception when receiving a serial port definition.
   */
  @Test
  @DisplayName("It should accept a serial port definition")
  public void dontThrowExceptionOnSerialDefinition() {
    DWIPort port = new DWNullPort(null, 1);
    assertDoesNotThrow(() -> port.setPortDef(new SerialPortDef(0, 0, 0, 0, "com1", 0)));
  }

  /**
   * It should not throw exception when receiving a tcp port definition.
   */
  @Test
  @DisplayName("It should accept a tcp port definition")
  public void dontThrowExceptionOnTcpDefinition() {
    DWIPort port = new DWNullPort(null, 1);
    assertDoesNotThrow(() -> port.setPortDef(new TcpPortDef()));
  }
}
