package org.thelair.dw4.drivewire.ports;

public final class NullPortDef implements DWIPort {
  @Override
  public void openWith(BasePortDef port) throws InvalidPortTypeDefinition {
  }

  @Override
  public void setPortDef(BasePortDef port) throws InvalidPortTypeDefinition {
  }

  @Override
  public int identifyPort() {
    return -1;
  }

  @Override
  public void closePort() {
  }
}
