package org.thelair.dw4.drivewire.util;

/**
 * Utility class to "safely" determine host operating system.
 */
public final class OsUtils {
  /**
   * Operating system types.
   */
  public enum OS {
    /**
     * Windows based operating system.
     */
    WINDOWS,
    /**
     * Linux based operating system.
     */
    LINUX,
    /**
     * Macintosh based operating system.
     */
    MAC,
    /**
     * Solaris based operating system.
     */
    SOLARIS,
    /**
     * Unknown operating system.
     */
    OTHER
  }

  /**
   * Static cached OS type.
   */
  private static OS cachedOs = null;

  /**
   * Hidden default constructor.
   */
  private OsUtils() {  }

  /**
   * Determine the operating system type.
   *
   * @return OS enum
   */
  public static OS getOsName() {
    if (cachedOs == null) {
      final String osName = System.getProperty("os.name").toLowerCase();
      if (
          osName.contains("mac")
          || osName.contains("darwin")
      ) {
        cachedOs = OS.MAC;
      } else if (osName.contains("win")) {
        cachedOs = OS.WINDOWS;
      } else if (
          osName.contains("nix")
          || osName.contains("nux")
          || osName.contains("aix")
      ) {
        cachedOs = OS.LINUX;
      } else if (osName.contains("sunos")) {
        cachedOs = OS.SOLARIS;
      } else {
        cachedOs = OS.OTHER;
      }
    }
    return cachedOs;
  }
}
