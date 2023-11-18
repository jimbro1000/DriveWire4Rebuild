package org.thelair.dw4.drivewire.ports;

import java.util.Map;

/**
 * Permitted drivewire port types.
 */
public interface DWIPortType {
  /**
   * Port type enum.
   * Implements a conversion to int
   */
  enum DWPortTypeIdentity {
    /**
     * null port.
     */
    NULL_PORT(0),
    /**
     * RS232 serial port.
     */
    SERIAL_PORT(1),
    /**
     * TCP port.
     */
    TCP_PORT(2);
    /**
     * int equivalent of port type.
     */
    private final int type;
    DWPortTypeIdentity(final int portType) {
      this.type = portType;
    }

    /**
     * Get enum port type as int.
     * @return port type as int
     */
    public int getPortType() {
      return type;
    }
  }

  /**
   * Identify type of port.
   * @return port type
   */
  DWPortTypeIdentity identify();

  /**
   * Get map of port definition.
   * @return port definition
   */
  Map<String, Integer> getPortDetail();
}

