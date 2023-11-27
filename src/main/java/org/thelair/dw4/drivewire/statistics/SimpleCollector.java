package org.thelair.dw4.drivewire.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Simple stats collector.
 */
public class SimpleCollector implements StatsCollector {
  /**
   * Map of valid stat counters.
   */
  private final Map<String, Long> stats;
  /**
   * Unique collector id.
   */
  private final UUID collectorId;

  /**
   * Simple stat collector.
   *
   * @param id unique id
   * @param clazz collecting class
   */
  public SimpleCollector(final UUID id, final Class<?> clazz) {
    stats = new HashMap<>();
    collectorId = id;
  }
  /**
   * Get collector id.
   *
   * @return uuid
   */
  @Override
  public UUID getId() {
    return collectorId;
  }

  /**
   * Increment a counter.
   *
   * @param key counter name
   */
  @Override
  public void addCounter(final String key) {
    if (!stats.containsKey(key)) {
      stats.put(key, 0L);
    }
  }

  /**
   * Increment a counter by the given value.
   *
   * @param key   counter name
   * @param count increment
   */
  @Override
  public void addCounter(final String key, final long count) {
    if (!stats.containsKey(key)) {
      stats.put(key, count);
    }
  }

  /**
   * Reset all counters.
   */
  @Override
  public void reset() {
    final Set<String> keys = stats.keySet();
    for (final String key:keys) {
      stats.replace(key, 0L);
    }
  }

  /**
   * Get list of counter key names.
   *
   * @return list of keys
   */
  @Override
  public List<String> getKeys() {
    return stats.keySet().stream().toList();
  }

  /**
   * Get all statistics.
   *
   * @return map of counters
   */
  @Override
  public Map<String, Long> getStats() {
    return null;
  }
}
