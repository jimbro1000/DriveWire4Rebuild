package org.thelair.dw4.drivewire.statistics;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manager and factory for stat collectors.
 */
public final class StatsManager {
  /**
   * Live stat collectors.
   */
  private static final Map<UUID, StatsCollector> REGISTER = new HashMap<>();
  /**
   * Closed but current collectors.
   */
  private static final Map<UUID, StatsCollector> GRAVEYARD = new HashMap<>();

  private StatsManager() {
    //hidden constructor
  }

  /**
   * Get a new collector.
   *
   * @param clazz representing class
   * @return stat collector
   */
  public static StatsCollector getCollector(final Class<?> clazz) {
    final UUID id = UUID.randomUUID();
    StatsCollector collector = new SimpleCollector(id, clazz);
    REGISTER.put(id, collector);
    return collector;
  }

  /**
   * Close an existing collector.
   *
   * @param collector stat collector
   */
  public static void closeCollector(final StatsCollector collector) {
    final UUID id = collector.getId();
    REGISTER.remove(id);
    GRAVEYARD.put(id, collector);
  }

  /**
   * Reset all current collectors.
   */
  public static void resetSession() {
    REGISTER.clear();
    GRAVEYARD.clear();
  }
}
