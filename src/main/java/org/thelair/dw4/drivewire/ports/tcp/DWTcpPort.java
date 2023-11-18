package org.thelair.dw4.drivewire.ports.tcp;

import org.thelair.dw4.drivewire.ports.BasePortDef;
import org.thelair.dw4.drivewire.ports.DWIPort;
import org.thelair.dw4.drivewire.ports.InvalidPortTypeDefinition;

public class DWTcpPort implements DWIPort {
  private TcpPortDef portDef;
  @Override
  public void openWith(BasePortDef port) throws InvalidPortTypeDefinition {
    portDef = validatePortDef(port);
    //register
  }

  @Override
  public void setPortDef(BasePortDef port) throws InvalidPortTypeDefinition {
    portDef = validatePortDef(port);
  }

  @Override
  public int identifyPort() {
    return 0;
  }

  @Override
  public void closePort() {

  }

  private TcpPortDef validatePortDef(BasePortDef port)
      throws InvalidPortTypeDefinition {
    if (port.getClass() == TcpPortDef.class) {
      return (TcpPortDef) port;
    } else {
      throw new InvalidPortTypeDefinition("Invalid serial port definition",
          port);
    }
  }
}
