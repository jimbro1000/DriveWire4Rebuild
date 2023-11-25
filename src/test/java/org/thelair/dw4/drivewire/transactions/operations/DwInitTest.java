package org.thelair.dw4.drivewire.transactions.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thelair.dw4.drivewire.DWCore;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.thelair.dw4.drivewire.transactions.Transaction.OP_DWINIT;
import static org.thelair.dw4.drivewire.transactions.Transaction.OP_INIT;

/**
 * Describes DriveWire init operation.
 */
@ExtendWith(MockitoExtension.class)
public class DwInitTest {
  @Mock
  DWCore controller;

  private DwInit subject;

  /**
   * Prepare test environment.
   */
  @BeforeEach
  public void setup() {
    subject = new DwInit();
    subject.register(controller);
  }

  /**
   * When called for a DW3 init it should not report
   * capability.
   */
  @Test
  public void dw3InitJustLogsInit() {
    subject.process(new int[] {OP_INIT.getOpCode()});
    verify(controller, never()).reportCapability();
  }

  /**
   * When called for a DW3 init it should record
   * the client as 0 capability.
   */
  @Test
  public void dw3InitSetCapabilityToZero() {
    subject.process(new int[] {OP_INIT.getOpCode()});
    verify(controller, times(1)).setClientVersion(0);
  }

  /**
   * When called for a DW4 init it should request
   * server capability.
   */
  @Test
  public void dw4InitRequestsCapability() {
    subject.process(new int[] {OP_DWINIT.getOpCode(), 0});
    verify(controller, times(1)).reportCapability();
  }

  /**
   * When called for a DW4 init it should record
   * client capability data.
   */
  @Test
  public void dw4InitSetsClientCapability() {
    subject.process(new int[] {OP_DWINIT.getOpCode(), 0x10});
    verify(controller, times(1)).setClientVersion(0x10);
  }
}
