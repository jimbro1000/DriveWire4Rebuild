package org.thelair.dw4.drivewire.statistics;

import lombok.Getter;

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
   * Represented class name.
   */
  @Getter
  private final String className;
  /**
   * Closed status.
   */
  @Getter
  private boolean closed;

  /**
   * Simple stat collector.
   *
   * @param uuid unique id
   * @param clazz collecting class
   */
  public SimpleCollector(final UUID uuid, final Class<?> clazz) {
    stats = new HashMap<>();
    collectorId = uuid;
    className = clazz.getName();
    closed = false;
  }
  /**
   * Get collector id.
   *
   * @return uuid
   */
  @Override
  public UUID getCollectorId() {
    return collectorId;
  }

  /**
   * Increment a counter.
   *
   * @param key counter name
   */
  @Override
  public void addCounter(final String key) {
    if (stats.containsKey(key)) {
      incrementCounter(key, 1);
    } else {
      stats.put(key, 1L);
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
    if (stats.containsKey(key)) {
      incrementCounter(key, count);
    } else {
      stats.put(key, count);
    }
  }

  private void incrementCounter(final String key, final long count) {
    final Long counter = stats.get(key) + count;
    stats.replace(key, counter);
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
   * Get list of recognised counter key names.
   *
   * @return list of keys
   */
  @Override
  public List<String> getKeys() {
    return stats.keySet().stream().toList();
  }

  /**
   * Get all statistics.
   * Returns a copy of the statistics map
   *
   * @return map of counters
   */
  @Override
  public Map<String, Long> getStats() {
    return copyStats();
  }

  private Map<String, Long> copyStats() {
    return new HashMap<>(stats);
  }

  /**
   * Get a single named statistic.
   * Returns stored counter value if key name is recognised,
   * Returns -1 if key is not recognised
   *
   * @param key counter name
   * @return long count
   */
  @Override
  public Long getStat(final String key) {
    if (stats.containsKey(key)) {
      return stats.get(key);
    }
    return -1L;
  }

  /**
   * Remove collector from active.
   */
  @Override
  public void closeCollector() {
    closed = true;
  }
}
