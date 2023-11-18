package org.thelair.dw4.drivewire.ports.tcp;

import org.thelair.dw4.drivewire.ports.BasePortDef;

import java.util.HashMap;
import java.util.Map;

/**
 * TCP port definition record.
 */
public final class TcpPortDef extends BasePortDef {
  @Override
  public DWPortTypeIdentity identify() {
    return DWPortTypeIdentity.TCP_PORT;
  }

  @Override
  public Map<String, Integer> getPortDetail() {
    final Map<String, Integer> result = new HashMap<>();
    result.put("Identity", DWPortTypeIdentity.TCP_PORT.getPortType());
    return result;
  }
}
