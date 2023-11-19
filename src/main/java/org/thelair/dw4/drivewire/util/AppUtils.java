package org.thelair.dw4.drivewire.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppUtils {
  /**
   * Auto open flag.
   */
  @Value("${drivewire.autoopen}")
  private static int autoopen;
  /**
   * Device prefix for windows based services.
   */
  @Value("${drivewire.autowin}")
  private static String autoWinPrefix;
  /**
   * Device prefix for Mac based services.
   */
  @Value("${drivewire.automac}")
  private static String autoMacPrefix;
  /**
   * Device prefix for linux based services.
   */
  @Value("${drivewire.autolin}")
  private static String autoLinuxPrefix;
  /**
   * Device prefix for unknown services.
   */
  @Value("${drivewire.autonull}")
  private static String autoNullPrefix;
  /**
   * Should the application try to auto-open a com port.
   *
   * @return true if autoopen is defined
   */
  public boolean isAutoOpen() {
    return autoopen == 1;
  }

  /**
   * Determine the appropriate device name prefix.
   *
   * @return assumed prefix to port name
   */
  public String portNamePrefix() {
    return switch (OsUtils.getOsName()) {
      case WINDOWS -> autoWinPrefix;
      case MAC -> autoMacPrefix;
      case LINUX -> autoLinuxPrefix;
      default -> autoNullPrefix;
    };
  }
}
