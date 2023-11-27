package org.thelair.dw4.drivewire.statistics;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Statistics collector interface.
 * Gather and report statistics
 */
public interface StatsCollector {
  /**
   * Get collector id.
   * @return uuid
   */
  UUID getId();
  /**
   * Increment a counter.
   * @param key counter name
   */
  void addCounter(String key);

  /**
   * Increment a counter by the given value.
   * @param key counter name
   * @param count increment
   */
  void addCounter(String key, long count);

  /**
   * Reset all counters.
   */
  void reset();

  /**
   * Get list of counter key names.
   * @return list of keys
   */
  List<String> getKeys();

  /**
   * Get all statistics.
   * @return map of counters
   */
  Map<String, Long> getStats();
}
