package org.thelair.dw4.drivewire.ports.serial;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.thelair.dw4.drivewire.ports.*;
import org.thelair.dw4.drivewire.ports.serial.hardware.DWISerial;
import org.thelair.dw4.drivewire.ports.serial.hardware.NullSerial;
import org.thelair.dw4.drivewire.ports.tcp.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Describe drivewire serial ports.
 */
@ExtendWith(MockitoExtension.class)
public class DWSerialPortTest {
  private final SerialPortDef portDef = new SerialPortDef(2400, 1, 0, 1, "/dev/tty/usb1", 1);
  @Mock
  private DWIPortManager portManager;
  @Mock
  private SerialPortHardware hardPorts;

  /**
   * Serial ports must identify as such.
   */
  @Test
  @DisplayName("It should always identify as a serial port")
  public void identifyAsANullPort() {
    DWISerial nullPort = new NullSerial();
    Mockito.when(hardPorts.getPortByName(anyString())).thenReturn(nullPort);
    DWIPort port = new DWSerialPort(null, 0, hardPorts);
    try {
      port.setPortDef(portDef);
      assertEquals(1, port.identifyPort(), "should return serial port value (1)");
    } catch (InvalidPortTypeDefinition ex) {
      fail("should not throw exception on valid port definition");
    }
  }

  /**
   * When a port is opened it should register with the open port pool.
   */
  @Test
  @DisplayName("It should register with its manager on open")
  public void registerWithManagerOnOpen() {
    DWISerial nullPort = new NullSerial();
    Mockito.when(hardPorts.getPortByName(anyString())).thenReturn(nullPort);
    DWIPort port = new DWSerialPort(portManager, 0, hardPorts);
    try {
      port.openWith(portDef);
      verify(portManager, times(1)).registerOpenPort(port);
    } catch (InvalidPortTypeDefinition ex) {
      fail("Should not throw exception");
    }
  }

  /**
   * When a port is closed it should be de-registered from the open port pool.
   */
  @Test
  @DisplayName("It should de-register with its manager on close")
  public void deregisterWithManagerOnClose() {
    DWIPort port = new DWSerialPort(portManager, 0, hardPorts);
    port.closePort();
    verify(portManager, times(1)).registerClosedPort(port);
  }

  /**
   * Attempts to configure a serial port with a port definition for any other port
   * type should result in an InvalidPortTypeDefinition.
   */
  @Test
  @DisplayName("It should throw an exception if the port def is not for serial")
  public void throwExceptionOnNonSerialPort() {
    DWIPort port = new DWSerialPort(portManager, 0, hardPorts);
    Exception ex = assertThrows(InvalidPortTypeDefinition.class,
        () -> port.openWith(new TcpPortDef()));
    assertEquals("Invalid serial port definition",
        ex.getMessage(),
        "it should report Invalid serial port definition");
  }
}
