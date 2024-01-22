package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.DayOfWeek;
import org.junit.jupiter.api.Test;

class EventTest {

  @Test
  void testEquals() {
    Event event1 = new Event("Event1", "text",
        DayOfWeek.FRIDAY, 1, 1);
    Event event2 = new Event(event1.getName(), event1.getDescription(),
        event1.getDay(), event1.getTime(), event1.duration);
    assertEquals(event1, event2);
    assertNotEquals(" ", event1);
    event2 = new Event("event1.getName()", event1.getDescription(),
        event1.getDay(), event1.getTime(), event1.duration);
    assertNotEquals(event1, event2);
    event2 = new Event(event1.getName(), "event1.getDescription()",
        event1.getDay(), event1.getTime(), event1.duration);
    assertNotEquals(event1, event2);
    event2 = new Event(event1.getName(), event1.getDescription(),
        DayOfWeek.THURSDAY, event1.getTime(), event1.duration);
    assertNotEquals(event1, event2);
    event2 = new Event(event1.getName(), event1.getDescription(),
        event1.getDay(), 7, event1.duration);
    assertNotEquals(event1, event2);
    event2 = new Event(event1.getName(), event1.getDescription(),
        event1.getDay(), event1.getTime(), 9);
    assertNotEquals(event1, event2);
  }
}