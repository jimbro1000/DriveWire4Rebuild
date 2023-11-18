package org.thelair.dw4.drivewire.ports.serial;

import lombok.*;
import org.thelair.dw4.drivewire.ports.BasePortDef;

import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class SerialPortDef extends BasePortDef {
  @Setter
  private int baudRate;
  @Setter
  private int dataBits;
  @Setter
  private int parityType;
  @Setter
  private int portId;
  @Setter
  private int stopBits;

  @Override
  public DWPortTypeIdentity identify() {
    return DWPortTypeIdentity.serialPort;
  }

  @Override
  public Map<String, Integer> getPortDetail() {
    Map<String, Integer> result = new HashMap<>();
    result.put("BaudRate", baudRate);
    result.put("DataBits", dataBits);
    result.put("Identity", DWPortTypeIdentity.serialPort.type);
    result.put("ParityType", parityType);
    result.put("PortId", portId);
    result.put("StopBits", stopBits);
    return result;
  }
}
