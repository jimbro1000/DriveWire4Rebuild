package org.thelair.dw4.drivewire.statistics;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Describes Simple Collector implementation of StatsCollector.
 */
public class SimpleCollectorTest {
  /**
   * It must implement the stats collector interface.
   */
  @Test
  public void itImplementsStatsCollector() {
    SimpleCollector subject = new SimpleCollector(UUID.randomUUID(),
        SimpleCollectorTest.class);
    List<Class<?>> interfaces =
        Arrays.asList(subject.getClass().getInterfaces());
    assertTrue(interfaces.contains(StatsCollector.class));
  }

  /**
   * It must persist a given unique identifier.
   */
  @Test
  public void itIsTaggedWithAUniqueIdentifier() {
    final UUID tag = UUID.randomUUID();
    final SimpleCollector subject = new SimpleCollector(tag, SimpleCollectorTest.class);
    assertEquals(tag, subject.getCollectorId(), "Given unique id must match");
  }

  /**
   * It returns -1 for any unrecognised keys.
   */
  @Test
  public void itReturnsMinusOneIfAKeyIsNotRecognised() {
    final SimpleCollector subject = new SimpleCollector(UUID.randomUUID(), SimpleCollectorTest.class);
    final long count = subject.getStat("test");
    assertEquals(-1, count, "should have a count of -1");
  }

  /**
   * Keys added without an initial count start at one.
   */
  @Test
  public void itRecordsNewKeysWithACountOfOne() {
    final SimpleCollector subject = new SimpleCollector(UUID.randomUUID(), SimpleCollectorTest.class);
    subject.addCounter("test");
    final List<String> keys = subject.getKeys();
    assertEquals("test", keys.get(0), "should only have a single matching key");
    final long count = subject.getStat("test");
    assertEquals(1, count, "should have a single count");
  }

  /**
   * It adds one to the given count if a value is not provided.
   */
  @Test
  public void itIncrementsExistingKeysByOne() {
    final SimpleCollector subject = new SimpleCollector(UUID.randomUUID(), SimpleCollectorTest.class);
    subject.addCounter("existingTest");
    subject.addCounter("existingTest");
    final long count = subject.getStat("existingTest");
    assertEquals(2, count, "should have a count of two");
  }

  /**
   * Keys added with a given count start at that value.
   */
  @Test
  public void itCreatesANewKeyWithAGivenCount() {
    final SimpleCollector subject = new SimpleCollector(UUID.randomUUID(), SimpleCollectorTest.class);
    subject.addCounter("test", 100);
    final List<String> keys = subject.getKeys();
    assertEquals("test", keys.get(0), "should only have a single matching key");
    final long count = subject.getStat("test");
    assertEquals(100, count, "should have a count matching the given value");
  }

  /**
   * It increments the count by the given provided value.
   */
  @Test
  public void itIncrementsExistingKeysByTheGivenValue() {
    final SimpleCollector subject = new SimpleCollector(UUID.randomUUID(), SimpleCollectorTest.class);
    subject.addCounter("existingBigTest");
    subject.addCounter("existingBigTest", 100);
    final long count = subject.getStat("existingBigTest");
    assertEquals(101, count, "should have a count of one plus the given count");
  }

  /**
   * It resets all known counters to zero.
   */
  @Test
  public void itResetsKnownKeysToZero() {
    final SimpleCollector subject = new SimpleCollector(UUID.randomUUID(), SimpleCollectorTest.class);
    subject.addCounter("test1");
    subject.addCounter("test2", 100);
    subject.reset();
    assertEquals(0L, subject.getStat("test1"), "count should be zero");
    assertEquals(0L, subject.getStat("test2"), "count should be zero");
  }

  /**
   * It returns a map of keys and counts.
   */
  @Test
  public void itReturnsAMapOfAllKnownStats() {
    final SimpleCollector subject = new SimpleCollector(UUID.randomUUID(), SimpleCollectorTest.class);
    subject.addCounter("testA");
    subject.addCounter("testB", 100);
    final Map<String, Long> stats = subject.getStats();
    assertTrue(stats.containsKey("testA"));
    assertTrue(stats.containsKey("testB"));
    assertEquals(1, stats.get("testA"), "count should be one");
    assertEquals(100, stats.get("testB"), "count should be 100");
  }

  /**
   * Collector maintains a closed flag.
   */
  @Test
  public void itFlagsTheCollectorAsClosed() {
    final SimpleCollector subject = new SimpleCollector(UUID.randomUUID(), SimpleCollectorTest.class);
    assertFalse(subject.isClosed());
    subject.closeCollector();
    assertTrue(subject.isClosed());
  }
}
