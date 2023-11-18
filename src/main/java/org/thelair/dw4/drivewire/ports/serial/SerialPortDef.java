package org.thelair.dw4.drivewire.ports.serial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.thelair.dw4.drivewire.ports.BasePortDef;

import java.util.HashMap;
import java.util.Map;

/**
 * RS232 Serial port configuration.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public final class SerialPortDef extends BasePortDef {
  /**
   * RS232 baud rate.
   */
  @Setter
  private int baudRate;
  /**
   * data bits per byte.
   */
  @Setter
  private int dataBits;
  /**
   * RS232 parity type 0/1.
   */
  @Setter
  private int parityType;
  /**
   * Unique port identifier.
   */
  @Setter
  private int portId;
  /**
   * Required port name.
   */
  @Setter
  private String portName;
  /**
   * RS232 stop bits 0/1.
   */
  @Setter
  private int stopBits;

  @Override
  public DWPortTypeIdentity identify() {
    return DWPortTypeIdentity.SERIAL_PORT;
  }

  @Override
  public Map<String, Integer> getPortDetail() {
    final Map<String, Integer> result = new HashMap<>();
    result.put("BaudRate", baudRate);
    result.put("DataBits", dataBits);
    result.put("Identity", DWPortTypeIdentity.SERIAL_PORT.getPortType());
    result.put("ParityType", parityType);
    result.put("PortId", portId);
    result.put("StopBits", stopBits);
    return result;
  }
}
