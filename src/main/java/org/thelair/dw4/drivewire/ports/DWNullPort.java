package org.thelair.dw4.drivewire.ports;

import org.apache.logging.log4j.*;
import org.thelair.dw4.drivewire.*;

/**
 * Null port definition type.
 */
public final class DWNullPort implements DWIPort {
  private static final Logger logger = LogManager.getLogger(DWNullPort.class);
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
    logger.info("created new null port " + managerId);
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

  /**
   * Serialise port definition as String.
   *
   * @return port definition values
   */
  @Override
  public String getPortDefinition() {
    return "null";
  }
}
