package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.view.ActionEmoji;
import java.time.DayOfWeek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JournalItemTest {
  private JournalItem event1;
  private JournalItem task1;

  /**
   * Initializes examples for testing
   */
  @BeforeEach
  void initData() {
    event1 = new Event("Event 1", "Event Desc 1", DayOfWeek.SUNDAY, 1230, 30);
    task1 = new Task("Task 1", "Task Desc 1", false, DayOfWeek.SUNDAY);
  }

  @Test
  void getName() {
    assertEquals("Event 1", event1.getName());
    assertEquals("Task 1", task1.getName());
  }

  @Test
  void getDescription() {
    assertEquals("Event Desc 1", event1.getDescription());
    assertEquals("Task Desc 1", task1.getDescription());
  }

  @Test
  void getDay() {
    assertEquals(DayOfWeek.SUNDAY, event1.getDay());
    assertEquals(DayOfWeek.SUNDAY, task1.getDay());
  }

  @Test
  void addEmoji() {
    event1.addEmoji(ActionEmoji.ART.convertEmoji());

    assertEquals(event1.getEmoji(), ActionEmoji.ART.convertEmoji());
  }
}