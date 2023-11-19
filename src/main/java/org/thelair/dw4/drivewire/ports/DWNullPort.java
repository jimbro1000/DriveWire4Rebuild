package org.thelair.dw4.drivewire.ports;

/**
 * Null port definition type.
 */
public final class DWNullPort implements DWIPort {
  /**
   * Port manager.
   */
  private final DWIPortManager portManager;
  /**
   * Unique port number.
   */
  private final int portId;
  /**
   * Create null port with reference to manager.
   * @param manager port manager handling this port
   * @param managerId port id reference from manager
   */
  public DWNullPort(final DWIPortManager manager, final int managerId) {
    this.portManager = manager;
    this.portId = managerId;
  }
  @Override
  public void openWith(final BasePortDef port)
      throws InvalidPortTypeDefinition {
    portManager.registerOpenPort(this);
  }

  @Override
  public void setPortDef(final BasePortDef port)
      throws InvalidPortTypeDefinition {
  }

  @Override
  public int identifyPort() {
    return this.portId;
  }

  @Override
  public void closePort() {
    portManager.registerClosedPort(this);
  }
}
