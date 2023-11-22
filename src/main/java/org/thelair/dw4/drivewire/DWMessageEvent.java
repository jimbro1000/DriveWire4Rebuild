package org.thelair.dw4.drivewire;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * DW Request message event.
 */
@Getter
public class DWMessageEvent extends ApplicationEvent {
  /**
   * Response message.
   */
  @Setter
  private int[] message;
  /**
   * Create new response message.
   * @param source source object
   * @param responseData int[] byte data
   */
  public DWMessageEvent(final Object source, final int[] responseData) {
    super(source);
    message = responseData;
  }
}
