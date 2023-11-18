package org.thelair.dw4.drivewire.ports;

import lombok.Getter;

import java.io.InvalidObjectException;
import java.io.ObjectInputValidation;

@Getter
public class InvalidPortTypeDefinition extends InvalidObjectException {
  private final BasePortDef sourcePortDef;
  /**
   * Constructs an {@code InvalidObjectException}.
   *
   * @param reason Detailed message explaining the reason for the failure.
   * @param portDef Causing port definition object
   * @see ObjectInputValidation
   */
  public InvalidPortTypeDefinition(String reason, BasePortDef portDef) {
    super(reason);
    sourcePortDef = portDef;
  }
}
