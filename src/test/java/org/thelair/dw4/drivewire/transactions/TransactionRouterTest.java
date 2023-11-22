package org.thelair.dw4.drivewire.transactions;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TransactionRouterTest {
  /**
   * This test is nugatory in terms of behaviours and is present only to trigger
   * the coding of the dictionary
   */
  @Test
  public void getDictionary() {
    TransactionRouter subject = new TransactionRouter();
    Map<Integer, Transaction> actual = subject.getDictionary();
    assertFalse(actual == null, "dictionary object must be instantiated");
    assertFalse(actual.isEmpty(), "dictionary should be non-zero in length");
  }
}
