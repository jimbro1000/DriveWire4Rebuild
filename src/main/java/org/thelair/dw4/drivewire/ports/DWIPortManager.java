package org.thelair.dw4.drivewire.ports;

import jakarta.servlet.ServletContextEvent;

/**
 * Port handler interface.
 */
public interface DWIPortManager {
  /**
   * Provide a count of all ports recorded.
   * @return total number of ports
   */
  int getPortCount();

  /**
   * Provide a count of open ports.
   * @return total number of open ports
   */
  int getOpenPortCount();

  /**
   * Provide a count of closed ports.
   * @return total number of closed ports
   */
  int getClosedPortCount();
  /**
   * Register an unused port as open.
   * @param port generic closed port
   */
  void registerOpenPort(DWIPort port);

  /**
   * Register a used port as closed.
   * @param port generic open port
   */
  void registerClosedPort(DWIPort port);

  /**
   * Create a new port instance.
   * @param portType type of port to create
   * @return unused port
   */
  DWIPort createPortInstance(DWIPortType.DWPortTypeIdentity portType);

  /**
   * Dispose of unused port.
   * @param port generic closed port
   */
  void disposePort(DWIPort port);


  /**
   * Handles context shutdown event.
   * Close all open ports. Dispose of all ports
   *
   * @param event context event
   */
  void contextDestroyed(ServletContextEvent event);
}
