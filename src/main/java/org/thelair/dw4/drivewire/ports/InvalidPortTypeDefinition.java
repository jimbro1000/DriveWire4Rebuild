package org.thelair.dw4.drivewire.ports;

import lombok.Getter;

import java.io.InvalidObjectException;
import java.io.ObjectInputValidation;

/**
 * Invalid port definition exception.
 */
@Getter
public class InvalidPortTypeDefinition extends InvalidObjectException {
  /**
   * Port definition causing the exception.
   */
  private final BasePortDef sourcePortDef;
  /**
   * Constructs an {@code InvalidObjectException}.
   *
   * @param reason Detailed message explaining the reason for the failure.
   * @param portDef Causing port definition object
   * @see ObjectInputValidation
   */
  public InvalidPortTypeDefinition(final String reason,
                                   final BasePortDef portDef) {
    super(reason);
    sourcePortDef = portDef;
  }
}
