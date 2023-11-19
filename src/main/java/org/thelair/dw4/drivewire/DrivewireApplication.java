package org.thelair.dw4.drivewire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thelair.dw4.drivewire.ports.DWPortManager;

/**
 * Spring application bootstrap.
 */
@SpringBootApplication
public class DrivewireApplication {
  /**
   * Core service.
   */
  private final DWCore core;

  /**
   * Instantiate core Spring application.
   * @param args configuration arguments
   */
  public static void main(final String[] args) {
    SpringApplication.run(DrivewireApplication.class, args);
  }

  /**
   * start core.
   */
  public DrivewireApplication() {
    core = new DWCore(new DWPortManager());
  }
}
