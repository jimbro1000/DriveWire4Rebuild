package org.thelair.dw4.drivewire.statistics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Manager and factory for stat collectors.
 */
@Component
public final class StatsManager {
  /**
   * Log appender.
   */
  private static final Logger LOGGER
      = LogManager.getLogger(StatsManager.class);
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
    final UUID collectorId = UUID.randomUUID();
    final StatsCollector collector = new SimpleCollector(collectorId, clazz);
    REGISTER.put(collectorId, collector);
    LOGGER.info("Acquire new logger with uuid: " + collectorId);
    return collector;
  }

  /**
   * Close an existing collector.
   * Moves collector to the graveyard until the next
   * reset event
   *
   * @param collector stat collector
   */
  public static void closeCollector(final StatsCollector collector) {
    final UUID collectorId = collector.getCollectorId();
    LOGGER.info("Close existing logger with uuid: " + collectorId);
    collector.closeCollector();
    REGISTER.remove(collectorId);
    GRAVEYARD.put(collectorId, collector);
  }

  /**
   * Reset all current collectors.
   */
  public static void resetSession() {
    LOGGER.info("Reset stats session, all closed collectors will be recycled");
    resetCollectors();
    GRAVEYARD.clear();
  }

  private static void resetCollectors() {
    for (final StatsCollector collector: REGISTER.values()) {
      collector.reset();
      LOGGER.info("Reset collector with uuid: " + collector.getCollectorId());
    }
  }

  /**
   * Get all collectors in the current session.
   * @return combined copy of current and graveyard collectors
   */
  public static List<StatsCollector> getSessionCollectors() {
    final List<StatsCollector> result = new ArrayList<>();
    result.addAll(REGISTER.values());
    result.addAll(GRAVEYARD.values());
    return result;
  }
}
