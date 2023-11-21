package org.thelair.dw4.drivewire.transactions;

public enum Transactions {
  /**
   * RESET1 - $FF.
   */
  OP_RESET1(0xFF, 0),
  /**
   * RESET2 - $FE.
   */
  OP_RESET2(0xFE, 0),
  /**
   * RESET3 - $F8.
   */
  OP_RESET3(0xF8, 0),
  /**
   * RE-READ EXTENDED - $F2.
   */
  OP_REREADEX(0xF2, 4),
  /**
   * READ EXTENDED - $D2.
   */
  OP_READEX(0xD2, 4),
  /**
   * SERIAL TERMINATE - $C5.
   */
  OP_SERTERM(0xC5, 1),
  /**
   * SERIAL SET STAT - $C4.
   */
  OP_SERSETSTAT(0xC4, 2),
  /**
   * SERIAL WRITE - $C3.
   */
  OP_SERWRITE(0xC3, 2),
  /**
   * FAST WRITE - $80.
   */
  OP_FASTWRITE(0x80, 1),
  /**
   * RE-WRITE - $77.
   */
  OP_REWRITE(0x77, 262),
  /**
   * RE-READ - $72.
   */
  OP_REREAD(0x72, 4),
  /**
   * SERIAL WRITE MULTI-BYTE - $64.
   */
  OP_SERWRITEM(0x64, 258),
  /**
   * SERIAL READ MULTI-BYTE - $63.
   */
  OP_SERREADM(0x63, 2),
  /**
   * WRITE - $57.
   */
  OP_WRITE(0x57, 262),
  /**
   * TERMINATE - $54.
   */
  OP_TERM(0x54, 0),
  /**
   * SET STAT - $53.
   */
  OP_SETSTAT(0x53, 2),
  /**
   * READ - $52.
   */
  OP_READ(0x52, 4),
  /**
   * PRINT (BYTE) - $50.
   */
  OP_PRINT(0x50, 1),
  /**
   * DRIVEWIRE INIT - $49.
   */
  OP_DWINIT(0x49, 1),
  /**
   * GET STAT - $47.
   */
  OP_GETSTAT(0x47, 2),
  /**
   * FLUSH PRINT - $46.
   */
  OP_PRINTFLUSH(0x46, 0),
  /**
   * SERIAL INIT - $45.
   */
  OP_SERINIT(0x45, 1),
  /**
   * SERIAL GET STAT - $44.
   */
  OP_SERGETSTAT(0x44, 2),
  /**
   * SERIAL READ - $43.
   */
  OP_SERREAD(0x43, 0),
  /**
   * (SYNC) TIME - $43.
   */
  OP_TIME(0x23, 0),
  /**
   * CREATE NAMED OBJECT - $02.
   */
  OP_NAMEOBJ_CREATE(0x02, 1),
  /**
   * MOUNT NAMED OBJECT - $01.
   */
  OP_NAMEOBJ_MOUNT(0x01, 1),
  /**
   * NO OPERATION - $00.
   */
  OP_NOP(0x00, 0);

  /**
   * operation code (byte).
   */
  private final int opCode;
  /**
   * remaining bytes to read in message.
   */
  private final int opLength;
  Transactions(final int code, final int len) {
    this.opCode = code;
    this.opLength = len;
  }

  /**
   * Get operation code byte.
   * @return opcode byte value
   */
  public int getOpCode() {
    return this.opCode;
  }

  /**
   * Get operation byte length.
   * @return expected number of following bytes
   */
  public int getOpLength() {
    return this.opLength;
  }
}
