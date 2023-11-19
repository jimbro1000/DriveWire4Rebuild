package org.thelair.dw4.drivewire.ports.serial.hardware;

import com.fazecast.jSerialComm.SerialPortDataListener;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface definition for serial hardware classes.
 */
public interface DWISerial {
  /**
   * Adds a SerialPortDataListener to the serial port interface.
   *
   * @param dataListener A SerialPortDataListener,
   *                     SerialPortDataListenerWithExceptions,
   *                     SerialPortPacketListener,
   *                     SerialPortMessageListener, or
   *                     SerialPortMessageListenerWithExceptions implementation
   *                     to be used for event-based serial port communications.
   *
   * @return Whether the listener was successfully registered with the serial
   * port
   */
  boolean addDataListener(SerialPortDataListener dataListener);

  /**
   * Returns the number of bytes available without blocking if
   * readBytes(byte[], int) were to be called immediately after this method
   * returns.
   *
   * @return The number of bytes currently available to be read, or -1 if the
   * port is not open
   */
  int bytesAvailable();

  /**
   * Returns the number of bytes still waiting to be written in the device's
   * output queue.
   *
   * @return The number of bytes currently waiting to be written, or -1 if the
   * port is not open
   */
  int bytesAwaitingWrite();

  /**
   * Closes this serial port.
   *
   * @return Whether the port was successfully closed
   */
  boolean closePort();

  /**
   * Flushes any already-received data from the registered
   * SerialPortDataListener that has not yet triggered an event.
   */
  void flushDataListener();

  /**
   * Flushes the serial port's Rx/Tx device buffers.
   *
   * @return Whether the IO buffers were (or will be) successfully flushed
   */
  boolean flushIOBuffers();

  /**
   * Gets the current baud rate of the serial port.
   *
   * @return The current baud rate of the serial port
   */
  int getBaudRate();

  /**
   * Gets the current number of data bits per word.
   *
   * @return The current number of data bits per word
   */
  int getDataBits();

  /**
   * Returns the flow control settings enabled on this serial port.
   *
   * @return The flow control settings enabled on this serial port
   */
  int getFlowControl();

  /**
   * Returns an InputStream object associated with this serial port.
   *
   * @return An InputStream object associated with this serial port
   */
  InputStream getInputStream();

  /**
   * Returns an OutputStream object associated with this serial port.
   *
   * @return An OutputStream object associated with this serial port
   */
  OutputStream getOutputStream();

  /**
   * Gets the current number of stop bits per word.
   *
   * @return The current number of stop bits per word.
   */
  int getStopBits();

  /**
   * Returns whether the port is currently open and available for communication.
   *
   * @return Whether the port is opened
   */
  boolean isOpen();

  /**
   * Opens this serial port for reading and writing.
   *
   * @return Whether the port was successfully opened with a valid configuration
   */
  boolean openPort();

  /**
   * Reads up to bytesToRead raw data bytes from the serial port and stores
   * them in the buffer.
   *
   * @param buffer      The buffer into which the raw data is read
   * @param bytesToRead The number of bytes to read from the serial port
   * @return The number of bytes successfully read, or -1 if there was an error
   * reading from the port
   */
  int readBytes(byte[] buffer, int bytesToRead);

  /**
   * Reads up to bytesToRead raw data bytes from the serial port and stores
   * them in the buffer starting at the indicated offset.
   *
   * @param buffer      The buffer into which the raw data is read
   * @param bytesToRead The number of bytes to read from the serial port
   * @param offset      The read buffer index into which to begin storing data
   * @return The number of bytes successfully read, or -1 if there was an error
   * reading from the port
   */
  int readBytes(byte[] buffer, int bytesToRead, int offset);

  /**
   * Removes the associated SerialPortDataListener from the serial port
   * interface.
   */
  void removeDataListener();

  /**
   * Sets all serial port parameters at one time.
   *
   * @param newBaudRate The desired baud rate for this serial port
   * @param newDataBits The number of data bits to use per word
   * @param newStopBits The number of stop bits to use
   * @param newParity   The type of parity error-checking desired
   * @return Whether the port configuration is valid or disallowed on this
   * system (only meaningful after the port is already opened)
   */
  boolean trySetCommPortParameters(
      int newBaudRate, int newDataBits, int newStopBits, int newParity
  );

  /**
   * Writes up to bytesToWrite raw data bytes from the buffer parameter to the
   * serial port.
   *
   * @param buffer       The buffer containing the raw data to write to the
   *                     serial port
   * @param bytesToWrite The number of bytes to write to the serial port
   * @return The number of bytes successfully written, or -1 if there was an
   * error writing to the port
   */
  int writeBytes(byte[] buffer, int bytesToWrite);

  /**
   * Writes up to bytesToWrite raw data bytes from the buffer parameter to the
   * serial port starting at the indicated offset.
   *
   * @param buffer       The buffer containing the raw data to write to the
   *                     serial port
   * @param bytesToWrite The number of bytes to write to the serial port
   * @param offset       The buffer index from which to begin writing to the
   *                     serial port
   * @return The number of bytes successfully written, or -1 if there was an
   * error writing to the port
   */
  int writeBytes(byte[] buffer, int bytesToWrite, int offset);
}
