package org.thelair.dw4.drivewire.ports.tcp;

import org.thelair.dw4.drivewire.ports.BasePortDef;

import java.util.HashMap;
import java.util.Map;

public class TcpPortDef extends BasePortDef {
  @Override
  public DWPortTypeIdentity identify() {
    return DWPortTypeIdentity.tcpPort;
  }

  @Override
  public Map<String, Integer> getPortDetail() {
    Map<String, Integer> result = new HashMap<>();
    result.put("Identity", DWPortTypeIdentity.tcpPort.type);
    return result;
  }
}
