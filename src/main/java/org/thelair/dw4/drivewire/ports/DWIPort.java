package org.thelair.dw4.drivewire.ports;

/**
 * Drivewire port interface.
 */
public interface DWIPort {
  /**
   * Open target port with given definition and register with port handler.
   * @param port definition record
   * @throws InvalidPortTypeDefinition on invalid definition type.
   */
  void openWith(BasePortDef port) throws InvalidPortTypeDefinition;

  /**
   * Modify port from definition.
   * @param port revised definition record
   * @throws InvalidPortTypeDefinition on invalid definition type.
   */
  void setPortDef(BasePortDef port) throws InvalidPortTypeDefinition;

  /**
   * Identify port type.
   * @return port type id.
   */
  int identifyPort();

  /**
   * Close port and deregister with port handler.
   */
  void closePort();
}
