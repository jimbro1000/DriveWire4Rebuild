package org.thelair.dw4.drivewire.statistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Describes Stats Manager.
 */
public class StatsManagerTest {
  private StatsCollector collector;

  /**
   * Prepare test subject.
   */
  @BeforeEach
  public void setup() {
    collector = StatsManager.getCollector(StatsManagerTest.class);
  }

  /**
   * Stats manager provides new collector objects.
   */
  @Test
  public void itProvidesANewCollectorForTheGivenClass() {
    final SimpleCollector subject = (SimpleCollector)collector;
    assertEquals(StatsManagerTest.class.getName(), subject.getClassName(), "class names should match");
  }

  /**
   * Stats manager closes named collectors.
   */
  @Test
  public void itClosesTheSelectedCollector() {
    StatsManager.closeCollector(collector);
    final SimpleCollector subject = (SimpleCollector)collector;
    assertTrue(subject.isClosed());
  }

  /**
   * Stats Manager reset clears counters on open collectors.
   */
  @Test
  public void itResetsOpenCollectors() {
    collector.addCounter("openTest", 100);
    StatsManager.resetSession();
    assertEquals(0, collector.getStat("openTest"));
  }

  /**
   * Stats Manager provides a list of collectors including all open collectors.
   */
  @Test
  public void itReportsUsedOpenCollectors() {
    List<StatsCollector> collectors = StatsManager.getSessionCollectors();
    assertTrue(collectors.contains(collector), "it should report open collectors");
  }

  /**
   * Stats Manager provides a list of collectors including closed collectors that
   * have not been reset.
   */
  @Test
  public void itReportsUsedClosedCollectors() {
    StatsManager.closeCollector(collector);
    List<StatsCollector> collectors = StatsManager.getSessionCollectors();
    assertTrue(collectors.contains(collector), "it should report open collectors");
  }

  /**
   * Stats Manager provides a list of collectors that
   * omits closed and reset collectors.
   */
  @Test
  public void itDisposesOfClosedCollectors() {
    StatsManager.closeCollector(collector);
    StatsManager.resetSession();
    List<StatsCollector> collectors = StatsManager.getSessionCollectors();
    assertFalse(collectors.contains(collector), "it should no longer keep a record of a closed collector");
  }
}
