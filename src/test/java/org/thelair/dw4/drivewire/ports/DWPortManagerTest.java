package org.thelair.dw4.drivewire.ports;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thelair.dw4.drivewire.ports.serial.DWSerialPort;
import org.thelair.dw4.drivewire.ports.tcp.DWTcpPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DWPortManagerTest {
  private DWIPortManager portManager;
  @BeforeEach
  public void setup() {
    portManager = new DWPortManager();
  }

  @Test
  public void itShouldReturnANullPortOnRequest() {
    DWIPort actual =
        portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.NULL_PORT);
    assertEquals(DWNullPort.class, actual.getClass(), "it should provide a " +
        "null port");
  }

  @Test
  public void itShouldReturnASerialPortOnRequest() {
    DWIPort actual =
        portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.SERIAL_PORT);
    assertEquals(DWSerialPort.class, actual.getClass(), "it should provide a " +
        "serial port");
  }

  @Test
  public void itShouldReturnATcpPortOnRequest() {
    DWIPort actual =
        portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.TCP_PORT);
    assertEquals(DWTcpPort.class, actual.getClass(), "it should provide a " +
        "tcp port");
  }

  @Test
  public void itRecordsCreatedPortsAsClosed() {
    portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.SERIAL_PORT);
    assertEquals(0, portManager.getOpenPortCount(), "Ports should be created " +
        "as closed");
    assertEquals(1, portManager.getClosedPortCount(), "Ports should be created " +
        "as closed");
    assertEquals(1, portManager.getPortCount(), "Ports should be recorded");
  }

  @Test
  public void itAddsOpenedPortToPool() throws InvalidPortTypeDefinition {
    DWIPort port =
        portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.NULL_PORT);
    int openPorts = portManager.getOpenPortCount();
    port.openWith(null);
    assertEquals(openPorts + 1, portManager.getOpenPortCount(), "opened ports" +
        " should be added to open port set");
  }

  @Test
  public void itRemovesOpenPortFromPoolWhenClosed()
      throws InvalidPortTypeDefinition {
    DWIPort port =
        portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.NULL_PORT);
    port.openWith(null);
    int openPorts = portManager.getOpenPortCount();
    port.closePort();
    assertEquals(openPorts - 1, portManager.getOpenPortCount(), "closed ports" +
        " should be removed from open port set");
  }

  @Test
  public void itRemovesDestroyedPortsFromClosedStore() {
    DWIPort port =
        portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.NULL_PORT);
    int ports = portManager.getClosedPortCount();
    portManager.disposePort(port);
    assertEquals(ports - 1, portManager.getClosedPortCount(), "it should " +
        "remove a disposed port from the closed pool");
  }

  @Test
  public void itRemovesDestroyedPortsFromOpenStore()
      throws InvalidPortTypeDefinition {
    DWIPort port =
        portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.NULL_PORT);
    port.openWith(null);
    int ports = portManager.getOpenPortCount();
    portManager.disposePort(port);
    assertEquals(ports - 1, portManager.getOpenPortCount(), "it should " +
        "remove a disposed port from the closed pool");
  }
}
