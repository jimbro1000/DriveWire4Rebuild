package org.thelair.dw4.drivewire.transactions.operations;

import org.thelair.dw4.drivewire.DWMessageEvent;

import java.time.LocalDateTime;

/**
 * Provide date and time information to client.
 */
public class DwTime extends BaseOp implements Operation {
  /**
   * Base year offset.
   */
  public static final int BASE_YEAR = 1900;
  /**
   * Byte size of response data.
   */
  public static final int RESPONSE_SIZE = 6;
  /**
   * Process operation.
   *
   * @param data operation message data
   */
  @Override
  public void process(final int[] data) {
    final LocalDateTime currentTime = LocalDateTime.now();
    int[] response = new int[RESPONSE_SIZE];
    int index = 0;
    response[index++] = currentTime.getYear() - BASE_YEAR;
    response[index++] = currentTime.getMonth().getValue();
    response[index++] = currentTime.getDayOfMonth();
    response[index++] = currentTime.getHour();
    response[index++] = currentTime.getMinute();
    response[index] = currentTime.getSecond();
    new DWMessageEvent(this, response);
  }
}
