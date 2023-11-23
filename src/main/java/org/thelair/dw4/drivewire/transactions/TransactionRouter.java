package org.thelair.dw4.drivewire.transactions;

import lombok.Getter;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thelair.dw4.drivewire.DWMessageEvent;
import org.thelair.dw4.drivewire.transactions.operations.Operation;

import java.util.HashMap;
import java.util.Map;

/**
 * Transaction router accepts opcode commands and routes
 * the result to the appropriate command handler.
 */
@Component
public class TransactionRouter {
  /**
   * Map of opcode:transaction pairs.
   * -- GETTER --
   *  Get dictionary of drive wire transactions.
   *
   * @return map of opcode to transactions
   */
  @Getter
  private final Map<Integer, Transaction> dictionary;
  /**
   * Map of transaction:operation pairs.
   */
  private final Map<Transaction, Operation> routing;

  /**
   * Create transaction router.
   */
  public TransactionRouter() {
    dictionary = new HashMap<>();
    routing = new HashMap<>();
    buildDictionary();
  }

  /**
   * Match transaction opCode to a handling function.
   * @param opCode Transaction enum
   * @param opFunction operation implementation
   */
  public void registerOperation(
      final Transaction opCode, final Operation opFunction
  ) {
    routing.put(opCode, opFunction);
  }

  private void buildDictionary() {
    for (Transaction entry : Transaction.values()) {
      dictionary.put(entry.getOpCode(), entry);
    }
  }

  private void sendResponse(final RCResponse resultCode, final int[] data) {
    // write result code
    // if data.length > 0 : write result data
  }

  private boolean validateOpCode(final int code, final int length) {
    if (!dictionary.containsKey(code)) {
      sendResponse(RCResponse.RC_SYNTAX_ERROR, new int[] {});
      return false;
    }
    Transaction transaction = dictionary.get(code);
    if (length > transaction.getOpMaxLength()
        || length < transaction.getOpLength()) {
      sendResponse(RCResponse.RC_SYNTAX_ERROR, new int[] {});
      return false;
    }
    return true;
  }

  /**
   * Accepts client request and route to correct handler.
   * @param data request data
   */
  public void processRequest(final int[] data) {
    if (validateOpCode(data[0], data.length - 1)) {
      sendResponse(RCResponse.RC_SYNTAX_ERROR, new int[] {});
      return;
    }
    Transaction transaction = dictionary.get(data[0]);
    Operation function = routing.get(transaction);
    function.process(data);
  }

  /**
   * Listen for response from request handler.
   *
   * @param event DWMessageEvent response
   */
  @Async
  @EventListener
  void processResponse(final DWMessageEvent event) {
    sendResponse(RCResponse.RC_SUCCESS, event.getMessage());
  }
}
