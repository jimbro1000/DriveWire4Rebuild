package org.thelair.dw4.drivewire.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppUtils {
  private static final Logger logger = LogManager.getLogger(AppUtils.class);

  /**
   * Auto open flag.
   */
  @Value("${drivewire.autoopen}")
  private int autoopen;
  /**
   * Device prefix for windows based services.
   */
  @Value("${drivewire.autowin}")
  private String autoWinPrefix;
  /**
   * Device prefix for Mac based services.
   */
  @Value("${drivewire.automac}")
  private String autoMacPrefix;
  /**
   * Device prefix for linux based services.
   */
  @Value("${drivewire.autolin}")
  private String autoLinuxPrefix;
  /**
   * Device prefix for unknown services.
   */
  @Value("${drivewire.autonull}")
  private String autoNullPrefix;
  /**
   * Should the application try to auto-open a com port.
   *
   * @return true if autoopen is defined
   */
  public boolean isAutoOpen() {
    logger.info("Auto open is " + autoopen);
    return autoopen == 1;
  }

  /**
   * Determine the appropriate device name prefix.
   *
   * @return assumed prefix to port name
   */
  public String portNamePrefix() {
    String name =  switch (OsUtils.getOsName()) {
      case WINDOWS -> autoWinPrefix;
      case MAC -> autoMacPrefix;
      case LINUX -> autoLinuxPrefix;
      default -> autoNullPrefix;
    };
    logger.info("port name prefix " + name);
    return name;
  }
}
