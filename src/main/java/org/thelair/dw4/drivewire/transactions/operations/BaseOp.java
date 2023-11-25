package org.thelair.dw4.drivewire.transactions.operations;

import lombok.Getter;
import org.thelair.dw4.drivewire.DWCore;

/**
 * Operations base class.
 */
public abstract class BaseOp {
  /**
   * Controller host.
   */
  @Getter
  private DWCore controller = null;

  /**
   * Register controller.
   *
   * @param origin message origin controller
   */
  public void register(final DWCore origin) {
    this.controller = origin;
  }
}
