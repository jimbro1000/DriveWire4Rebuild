package org.thelair.dw4.drivewire.ports.tcp;

import org.thelair.dw4.drivewire.ports.BasePortDef;
import org.thelair.dw4.drivewire.ports.DWIPort;
import org.thelair.dw4.drivewire.ports.DWIPortType;
import org.thelair.dw4.drivewire.ports.InvalidPortTypeDefinition;

/**
 * Drivewire TCP port record.
 */
public final class DWTcpPort implements DWIPort {
  /**
   * TCP port definition.
   */
  private TcpPortDef portDef;
  @Override
  public void openWith(final BasePortDef port)
      throws InvalidPortTypeDefinition {
    portDef = validatePortDef(port);
    //register
  }

  @Override
  public void setPortDef(final BasePortDef port)
      throws InvalidPortTypeDefinition {
    portDef = validatePortDef(port);
  }

  @Override
  public int identifyPort() {
    return DWIPortType.DWPortTypeIdentity.TCP_PORT.getPortType();
  }

  @Override
  public void closePort() {

  }

  /**
   * Serialise port definition as String.
   *
   * @return port definition values
   */
  @Override
  public String getPortDefinition() {
    return "";
  }

  private TcpPortDef validatePortDef(final BasePortDef port)
      throws InvalidPortTypeDefinition {
    if (port.getClass() == TcpPortDef.class) {
      return (TcpPortDef) port;
    } else {
      throw new InvalidPortTypeDefinition("Invalid serial port definition",
          port);
    }
  }
}
