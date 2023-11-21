package org.thelair.dw4.drivewire.transactions;

import java.util.HashMap;
import java.util.Map;

/**
 * Transaction router accepts opcode commands and routes
 * the result to the appropriate command handler.
 */
public class TransactionRouter {
  /**
   * Map of opcode:transaction pairs.
   */
  private final Map<Integer, Transaction> dictionary;

  /**
   * Create transaction router.
   */
  public TransactionRouter() {
    dictionary = new HashMap<>();
    buildDictionary();
  }

  /**
   * Get dictionary of drivewire transactions.
   *
   * @return map of opcode to transactions
   */
  public Map<Integer, Transaction> getDictionary() {
    return dictionary;
  }

  private void buildDictionary() {
    for (Transaction entry : Transaction.values()) {
      dictionary.put(entry.getOpCode(), entry);
    }
  }
}
