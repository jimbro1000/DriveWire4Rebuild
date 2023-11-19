package org.thelair.dw4.drivewire.ports;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thelair.dw4.drivewire.ports.serial.DWSerialPort;
import org.thelair.dw4.drivewire.ports.tcp.DWTcpPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Describes drivewire port manager.
 */
public class DWPortManagerTest {
  private DWIPortManager portManager;

  /**
   * Prepare port manager for tests.
   */
  @BeforeEach
  public void setup() {
    portManager = new DWPortManager();
  }

  /**
   * It should generate null ports when required.
   */
  @Test
  public void itShouldReturnANullPortOnRequest() {
    DWIPort actual =
        portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.NULL_PORT);
    assertEquals(DWNullPort.class, actual.getClass(), "it should provide a " +
        "null port");
  }

  /**
   * It should generate serial ports when required.
   */
  @Test
  public void itShouldReturnASerialPortOnRequest() {
    DWIPort actual =
        portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.SERIAL_PORT);
    assertEquals(DWSerialPort.class, actual.getClass(), "it should provide a " +
        "serial port");
  }

  /**
   * It should generate TCP ports when required.
   */
  @Test
  public void itShouldReturnATcpPortOnRequest() {
    DWIPort actual =
        portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.TCP_PORT);
    assertEquals(DWTcpPort.class, actual.getClass(), "it should provide a " +
        "tcp port");
  }

  /**
   * It should register new ports as closed.
   */
  @Test
  public void itRecordsCreatedPortsAsClosed() {
    portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.SERIAL_PORT);
    assertEquals(0, portManager.getOpenPortCount(), "Ports should be created " +
        "as closed");
    assertEquals(1, portManager.getClosedPortCount(), "Ports should be created " +
        "as closed");
    assertEquals(1, portManager.getPortCount(), "Ports should be recorded");
  }

  /**
   * It should add a port to open pool when opened.
   */
  @Test
  public void itAddsOpenedPortToPool() {
    DWIPort port =
        portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.NULL_PORT);
    int openPorts = portManager.getOpenPortCount();
    try {
      port.openWith(null);
      assertEquals(openPorts + 1, portManager.getOpenPortCount(), "opened ports" +
          " should be added to open port set");
    } catch (InvalidPortTypeDefinition ex) {
      fail("should not throw exception on open");
    }
  }

  /**
   * It should remove open port from pool when closed.
   */
  @Test
  public void itRemovesOpenPortFromPoolWhenClosed() {
    DWIPort port =
        portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.NULL_PORT);
    try {
      port.openWith(null);
      int openPorts = portManager.getOpenPortCount();
      port.closePort();
      assertEquals(openPorts - 1, portManager.getOpenPortCount(), "closed ports" +
          " should be removed from open port set");
    } catch (InvalidPortTypeDefinition ex) {
      fail("should not thrown exception on open");
    }
  }

  /**
   * Ports should be removed from open port pool when disposed.
   */
  @Test
  public void itRemovesDestroyedPortsFromClosedStore() {
    DWIPort port =
        portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.NULL_PORT);
    int ports = portManager.getClosedPortCount();
    portManager.disposePort(port);
    assertEquals(ports - 1, portManager.getClosedPortCount(), "it should " +
        "remove a disposed port from the closed pool");
  }

  /**
   * Disposed ports must be removed from all pools.
   */
  @Test
  public void itRemovesDestroyedPortsFromOpenStore() {
    DWIPort port =
        portManager.createPortInstance(DWIPortType.DWPortTypeIdentity.NULL_PORT);
    try {
      port.openWith(null);
      int ports = portManager.getOpenPortCount();
      portManager.disposePort(port);
      assertEquals(ports - 1, portManager.getOpenPortCount(), "it should " +
          "remove a disposed port from the closed pool");
    } catch (InvalidPortTypeDefinition ex) {
      fail("should not throw exception on open");
    }
  }
}
