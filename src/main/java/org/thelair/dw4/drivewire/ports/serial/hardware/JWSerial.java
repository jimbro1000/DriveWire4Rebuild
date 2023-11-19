package org.thelair.dw4.drivewire.ports.serial.hardware;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;

import java.io.InputStream;
import java.io.OutputStream;

public class JWSerial implements DWISerial {
  /**
   * Direct port implementation in JWSerialComm.
   */
  private final SerialPort hostSerial;

  /**
   * Construct wrapper to direct implementation.
   *
   * @param host JWSerialComm port object
   */
  public JWSerial(final SerialPort host) {
    hostSerial = host;
  }

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
  @Override
  public boolean addDataListener(final SerialPortDataListener dataListener) {
    return hostSerial.addDataListener(dataListener);
  }

  /**
   * Returns the number of bytes available without blocking if
   * readBytes(byte[], int) were to be called immediately after this method
   * returns.
   *
   * @return The number of bytes currently available to be read, or -1 if the
   * port is not open
   */
  @Override
  public int bytesAvailable() {
    return hostSerial.bytesAvailable();
  }

  /**
   * Returns the number of bytes still waiting to be written in the device's
   * output queue.
   *
   * @return The number of bytes currently waiting to be written, or -1 if the
   * port is not open
   */
  @Override
  public int bytesAwaitingWrite() {
    return hostSerial.bytesAwaitingWrite();
  }

  /**
   * Closes this serial port.
   *
   * @return Whether the port was successfully closed
   */
  @Override
  public boolean closePort() {
    return hostSerial.closePort();
  }

  /**
   * Flushes any already-received data from the registered
   * SerialPortDataListener that has not yet triggered an event.
   */
  @Override
  public void flushDataListener() {
    hostSerial.flushDataListener();
  }

  /**
   * Flushes the serial port's Rx/Tx device buffers.
   *
   * @return Whether the IO buffers were (or will be) successfully flushed
   */
  @Override
  public boolean flushIOBuffers() {
    return hostSerial.flushIOBuffers();
  }

  /**
   * Gets the current baud rate of the serial port.
   *
   * @return The current baud rate of the serial port
   */
  @Override
  public int getBaudRate() {
    return hostSerial.getBaudRate();
  }

  /**
   * Gets the current number of data bits per word.
   *
   * @return The current number of data bits per word
   */
  @Override
  public int getFlowControl() {
    return hostSerial.getFlowControlSettings();
  }

  /**
   * Returns the flow control settings enabled on this serial port.
   *
   * @return The flow control settings enabled on this serial port
   */
  @Override
  public InputStream getInputStream() {
    return hostSerial.getInputStream();
  }

  /**
   * Returns an InputStream object associated with this serial port.
   *
   * @return An InputStream object associated with this serial port
   */
  @Override
  public int getDataBits() {
    return hostSerial.getNumDataBits();
  }

  /**
   * Returns an OutputStream object associated with this serial port.
   *
   * @return An OutputStream object associated with this serial port
   */
  @Override
  public int getStopBits() {
    return hostSerial.getNumStopBits();
  }

  /**
   * Gets the current number of stop bits per word.
   *
   * @return The current number of stop bits per word.
   */
  @Override
  public OutputStream getOutputStream() {
    return hostSerial.getOutputStream();
  }

  /**
   * Returns whether the port is currently open and available for communication.
   *
   * @return Whether the port is opened
   */
  @Override
  public boolean isOpen() {
    return hostSerial.isOpen();
  }

  /**
   * Opens this serial port for reading and writing.
   *
   * @return Whether the port was successfully opened with a valid configuration
   */
  @Override
  public boolean openPort() {
    return hostSerial.openPort();
  }

  /**
   * Reads up to bytesToRead raw data bytes from the serial port and stores
   * them in the buffer.
   *
   * @param buffer      The buffer into which the raw data is read
   * @param bytesToRead The number of bytes to read from the serial port
   * @return The number of bytes successfully read, or -1 if there was an error
   * reading from the port
   */
  @Override
  public int readBytes(final byte[] buffer, final int bytesToRead) {
    return hostSerial.readBytes(buffer, bytesToRead);
  }

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
  @Override
  public int readBytes(final byte[] buffer, final int bytesToRead,
                       final int offset) {
    return hostSerial.readBytes(buffer, bytesToRead, offset);
  }

  /**
   * Removes the associated SerialPortDataListener from the serial port
   * interface.
   */
  @Override
  public void removeDataListener() {
    hostSerial.removeDataListener();
  }

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
  @Override
  public boolean setCommPortParameters(final int newBaudRate,
                                       final int newDataBits,
                                       final int newStopBits,
                                       final int newParity) {
    return hostSerial.setComPortParameters(newBaudRate, newDataBits,
        newStopBits, newParity);
  }

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
  @Override
  public int writeBytes(final byte[] buffer, final int bytesToWrite) {
    return hostSerial.writeBytes(buffer, bytesToWrite);
  }

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
  @Override
  public int writeBytes(final byte[] buffer, final int bytesToWrite,
                        final int offset) {
    return hostSerial.writeBytes(buffer, bytesToWrite, offset);
  }
}
