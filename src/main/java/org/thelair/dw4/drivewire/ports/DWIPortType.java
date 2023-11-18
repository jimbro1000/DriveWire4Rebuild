package org.thelair.dw4.drivewire.ports;

import java.util.Map;

public interface DWIPortType {
  enum DWPortTypeIdentity {
    nullPort(0),
    serialPort(1),
    tcpPort(2);
    public final int type;
    private DWPortTypeIdentity(int type) {
      this.type = type;
    }
  }
  DWPortTypeIdentity identify();
  Map<String,Integer> getPortDetail();
}

