package org.thelair.dw4.drivewire.statistics;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
