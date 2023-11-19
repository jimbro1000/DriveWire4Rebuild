package org.thelair.dw4.drivewire.ports.serial;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Describes Serial Port Definition.
 */
public class SerialPortDefTest {
  /**
   * Ports must provide port details as a Map.
   */
  @Test
  @DisplayName("It should compile the port definition as a map")
  public void itShouldReportTheDefContentsAsAMap() {
    SerialPortDef subject = new SerialPortDef(1,2,3,4,"a",5);
    Map<String, Integer> actual = subject.getPortDetail();
    assertEquals(1, actual.get("BaudRate"), "It must contain a baud rate " +
        "value");
    assertEquals(2, actual.get("DataBits"), "It must contain a data bit " +
        "value");
    assertEquals(3, actual.get("ParityType"), "It must contain a parity type " +
        "value");
    assertEquals(4, actual.get("PortId"), "It must contain a port id " +
        "value");
    assertEquals(5, actual.get("StopBits"), "It must contain a stop bit " +
        "value");
    assertEquals(1, actual.get("Identity"), "It must identify as a serial " +
        "port");
  }
}
