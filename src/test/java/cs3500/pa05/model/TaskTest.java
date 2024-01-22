package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.DayOfWeek;
import org.junit.jupiter.api.Test;

class TaskTest {

  @Test
  void testEquals() {
    Task task1 = new Task("task1", "text", false, DayOfWeek.MONDAY);
    Task task2 = new Task(task1.getName(), task1.getDescription(),
        task1.getComplete(), task1.getDay());
    assertEquals(task1, task2);
    assertNotEquals(" ", task1);
    task2 = new Task("task1.getName()", task1.getDescription(),
        task1.getComplete(), task1.getDay());
    assertNotEquals(task1, task2);
    task2 = new Task(task1.getName(), "task1.getDescription()",
        task1.getComplete(), task1.getDay());
    assertNotEquals(task1, task2);
    task2 = new Task(task1.getName(), task1.getDescription(),
        true, task1.getDay());
    assertNotEquals(task1, task2);
    task2 = new Task(task1.getName(), task1.getDescription(),
        task1.getComplete(), DayOfWeek.FRIDAY);
    assertNotEquals(task1, task2);
  }
}