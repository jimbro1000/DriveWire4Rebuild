package org.thelair.dw4.drivewire.ports.serial;

import com.fazecast.jSerialComm.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.thelair.dw4.drivewire.ports.*;
import org.thelair.dw4.drivewire.ports.tcp.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DWSerialPortTest {
  private final SerialPortDef portDef = new SerialPortDef(2400, 1, 0, 1, "/dev/tty/usb1", 1);
  @Mock
  private DWIPortManager portManager;
  @Mock
  private SerialPortHardware hardPorts;

  @Test
  @DisplayName("It should always identify as a serial port")
  public void identifyAsANullPort() throws InvalidPortTypeDefinition {
    Mockito.when(hardPorts.getPortByName(anyString())).thenReturn(SerialPort.getCommPorts()[0]);
    DWIPort port = new DWSerialPort(null, 0, hardPorts);
    port.setPortDef(portDef);
    assertEquals(1, port.identifyPort(), "should return serial port value (1)");
  }

  @Test
  @DisplayName("It should register with its manager on open")
  public void registerWithManagerOnOpen() throws InvalidPortTypeDefinition {
    Mockito.when(hardPorts.getPortByName(anyString())).thenReturn(SerialPort.getCommPorts()[0]);
    DWIPort port = new DWSerialPort(portManager, 0, hardPorts);
    port.openWith(portDef);
    verify(portManager, times(1)).registerOpenPort(port);
  }

  @Test
  @DisplayName("It should de-register with its manager on close")
  public void deregisterWithManagerOnClose() {
    DWIPort port = new DWSerialPort(portManager, 0, hardPorts);
    port.closePort();
    verify(portManager, times(1)).registerClosedPort(port);
  }

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
