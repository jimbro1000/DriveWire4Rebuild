package org.thelair.dw4.drivewire.transactions.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thelair.dw4.drivewire.DWCore;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.thelair.dw4.drivewire.transactions.Transaction.OP_INIT;

/**
 * Describe DriveWire Term operation.
 */
@ExtendWith(MockitoExtension.class)
public class DwTermTest {
  @Mock
  DWCore controller;

  private DwTerm subject;

  /**
   * Prepare test environment.
   */
  @BeforeEach
  public void setup() {
    subject = new DwTerm();
    subject.register(controller);
  }
  /**
   * When called for a DW3 init it should record
   * the client as 0 capability.
   */
  @Test
  public void dwTermSetCapabilityToMinusOne() {
    subject.process(new int[] {OP_INIT.getOpCode()});
    verify(controller, times(1)).setClientVersion(0);
  }
}
