package org.thelair.dw4.drivewire.ports.serial;

import org.thelair.dw4.drivewire.ports.BasePortDef;
import org.thelair.dw4.drivewire.ports.DWIPort;
import org.thelair.dw4.drivewire.ports.InvalidPortTypeDefinition;

public class DWSerialPort implements DWIPort {
  private SerialPortDef portDef;
  @Override
  public void openWith(BasePortDef port) throws InvalidPortTypeDefinition {
    this.portDef = validatePortDef(port);
    //register
  }

  @Override
  public void setPortDef(BasePortDef port) throws InvalidPortTypeDefinition {
    this.portDef = validatePortDef(port);
  }

  @Override
  public int identifyPort() {
    return portDef.getPortId();
  }

  @Override
  public void closePort() {
    //deregister
  }

  private SerialPortDef validatePortDef(BasePortDef port)
      throws InvalidPortTypeDefinition {
    if (port.getClass() == SerialPortDef.class) {
      return (SerialPortDef) port;
    } else {
      throw new InvalidPortTypeDefinition("Invalid serial port definition",
          port);
    }
  }
}
