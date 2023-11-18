package org.thelair.dw4.drivewire.ports;

public interface DWIPort {
  public void openWith(BasePortDef port) throws InvalidPortTypeDefinition;
  public void setPortDef(BasePortDef port) throws InvalidPortTypeDefinition;
  public int identifyPort();
  public void closePort();
}
