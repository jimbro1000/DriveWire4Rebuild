package org.thelair.dw4.drivewire.transactions;

import lombok.Getter;

/**
 * RC Response Codes.
 */
@Getter
public enum RCResponse {
  /**
   * Success.
   */
  RC_SUCCESS(0x00),
  /**
   * Syntax Error.
   */
  RC_SYNTAX_ERROR(0x0A),
  /**
   * Drive Error.
   */
  RC_DRIVE_ERROR(0x64),
  /**
   * Invalid Drive.
   */
  RC_INVALID_DRIVE(0x65),
  /**
   * Drive Not Loaded.
   */
  RC_DRIVE_NOT_LOADED(0x66),
  /**
   * Drive Already Loaded.
   */
  RC_DRIVE_ALREADY_LOADED(0x67),
  /**
   * Image Format Exception.
   */
  RC_IMAGE_FORMAT_EXCEPTION(0x68),
  /**
   * No Such Disk Set.
   */
  RC_NO_SUCH_DISKSET(0x6E),
  /**
   * Invalid Disk Def.
   */
  RC_INVALID_DISK_DEF(0x6F),
  /**
   * Net Error.
   */
  RC_NET_ERROR(0x78),
  /**
   * Net IO Error.
   */
  RC_NET_IO_ERROR(0x79),
  /**
   * Net Unknown Host.
   */
  RC_NET_UNKNOWN_HOST(0x7A),
  /**
   * Net Invalid Connection.
   */
  RC_NET_INVALID_CONNECTION(0x7B),
  /**
   * Invalid Port.
   */
  RC_INVALID_PORT(0x8C),
  /**
   * Invalid Handler.
   */
  RC_INVALID_HANDLER(0x8D),
  /**
   * Configuration Key Not Set.
   */
  RC_CONFIG_KEY_NOT_SET(0x8E),
  /**
   * Midi Error.
   */
  RC_MIDI_ERROR(0x96),
  /**
   * Midi Unavailable.
   */
  RC_MIDI_UNAVAILABLE(0x97),
  /**
   * Midi Invalid Device.
   */
  RC_MIDI_INVALID_DEVICE(0x98),
  /**
   * Midi Invalid Data.
   */
  RC_MIDI_INVALID_DATA(0x99),
  /**
   * Midi Soundbank Failed.
   */
  RC_MIDI_SOUNDBANK_FAILED(0x9A),
  /**
   * Midi Soundbank Not Supported.
   */
  RC_MIDI_SOUNDBANK_NOT_SUPPORTED(0x9B),
  /**
   * Midi Invalid Profile.
   */
  RC_MIDI_INVALID_PROFILE(0x9C),
  /**
   * Server Error.
   */
  RC_SERVER_ERROR(0xC8),
  /**
   * Server Filesystem Exception.
   */
  RC_SERVER_FILESYSTEM_EXCEPTION(0xC9),
  /**
   * Server IO Exception.
   */
  RC_SERVER_IO_EXCEPTION(0xCA),
  /**
   * Server File Not Found.
   */
  RC_SERVER_FILE_NOT_FOUND(0xCB),
  /**
   * Server Not Implemented.
   */
  RC_SERVER_NOT_IMPLEMENTED(0xCC),
  /**
   * Server Not Ready.
   */
  RC_SERVER_NOT_READY(0xCD),
  /**
   * Instance Not Ready.
   */
  RC_INSTANCE_NOT_READY(0xCE),
  /**
   * UI Error.
   */
  RC_UI_ERROR(0xDC),
  /**
   * UI Malformed Request.
   */
  RC_UI_MALFORMED_REQUEST(0xDD),
  /**
   * UI Malformed Response.
   */
  RC_UI_MALFORMED_RESPONSE(0xDE),
  /**
   * Help Topic Not Found.
   */
  RC_HELP_TOPIC_NOT_FOUND(0xE6),
  /**
   * Fail.
   */
  RC_FAIL(0xFF);

  /**
   * Response byte value.
   */
  private final int responseCode;

  RCResponse(final int code) {
    this.responseCode = code;
  }
}
