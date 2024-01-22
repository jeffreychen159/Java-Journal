package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class DayFilterTest {
  Event event1 = new Event("Event1", "do a thing",
      DayOfWeek.MONDAY, 1, 45);
  Event event2 = new Event("Event2", "do a some stuff",
      DayOfWeek.MONDAY, 4, 10);
  Event event3 = new Event("Event3", "Perform the dedicated action",
      DayOfWeek.TUESDAY, 3, 15);
  Event event4 = new Event("Event4", "take an action",
      DayOfWeek.TUESDAY, 13, 25);
  Event event5 = new Event("Event5", "do a thing",
      DayOfWeek.WEDNESDAY, 2, 45);
  Event event6 = new Event("Event6", "do a some stuff",
      DayOfWeek.WEDNESDAY, 4, 120);
  Event event7 = new Event("Event7", "Perform the dedicated action",
      DayOfWeek.THURSDAY, 7, 15);
  Event event8 = new Event("Event8", "take an action",
      DayOfWeek.THURSDAY, 13, 25);
  Event event9 = new Event("Event9", "do a thing",
      DayOfWeek.FRIDAY, 1, 45);
  Event event10 = new Event("Event10", "do a some stuff",
      DayOfWeek.FRIDAY, 4, 10);
  Event event11 = new Event("Event11", "Perform the dedicated action",
      DayOfWeek.SATURDAY, 3, 15);
  Event event12 = new Event("Event12", "take an action",
      DayOfWeek.SATURDAY, 13, 25);
  Event event13 = new Event("Event13", "do a thing",
      DayOfWeek.SUNDAY, 1, 45);
  Event event14 = new Event("Event14", "do a some stuff",
      DayOfWeek.SUNDAY, 4, 10);


  Task task1 = new Task("Task1", "do a thing",
      false, DayOfWeek.MONDAY);
  Task task2 = new Task("Task2", "do a some stuff",
      true, DayOfWeek.MONDAY);
  Task task3 = new Task("Task3", "Perform the dedicated action",
      false, DayOfWeek.TUESDAY);
  Task task4 = new Task("Task4", "take an action",
      true, DayOfWeek.TUESDAY);
  Task task5 = new Task("Task5", "do a thing",
      true, DayOfWeek.WEDNESDAY);
  Task task6 = new Task("Task6", "do a some stuff",
      false, DayOfWeek.WEDNESDAY);
  Task task7 = new Task("Task8", "Perform the dedicated action",
      false, DayOfWeek.THURSDAY);
  Task task8 = new Task("Task8", "take an action",
      false, DayOfWeek.THURSDAY);
  Task task9 = new Task("Task9", "do a thing",
      true, DayOfWeek.FRIDAY);
  Task task10 = new Task("Task10", "do a some stuff",
      true, DayOfWeek.FRIDAY);
  Task task11 = new Task("Task11", "Perform the dedicated action",
      true, DayOfWeek.SATURDAY);
  Task task12 = new Task("Task12", "take an action",
      false, DayOfWeek.SATURDAY);
  Task task13 = new Task("Task13", "do a thing",
      true, DayOfWeek.SUNDAY);
  Task task14 = new Task("Task14", "do a some stuff",
      false, DayOfWeek.SUNDAY);
  List<Event> events = new ArrayList<>(List.of(event1, event2, event3, event4, event5, event6,
      event7, event8, event9, event10, event11, event12, event13, event14));
  List<Task> tasks = new ArrayList<>(List.of(task1, task2, task3, task4, task5, task6,
      task7, task8, task9, task10, task11, task12, task13, task14));

  @Test
  void dayEventFilter() {
    List<Event> mondayEvents = new ArrayList<>(List.of(event1, event2));
    assertEquals(DayFilter.dayEventFilter(events, DayOfWeek.MONDAY), mondayEvents);
    List<Event> tuesdayEvents = new ArrayList<>(List.of(event3, event4));
    assertEquals(DayFilter.dayEventFilter(events, DayOfWeek.TUESDAY), tuesdayEvents);
    List<Event> wednesdayEvents = new ArrayList<>(List.of(event5, event6));
    assertEquals(DayFilter.dayEventFilter(events, DayOfWeek.WEDNESDAY), wednesdayEvents);
    List<Event> thursdayEvents = new ArrayList<>(List.of(event7, event8));
    assertEquals(DayFilter.dayEventFilter(events, DayOfWeek.THURSDAY), thursdayEvents);
    List<Event> fridayEvents = new ArrayList<>(List.of(event9, event10));
    assertEquals(DayFilter.dayEventFilter(events, DayOfWeek.FRIDAY), fridayEvents);
    List<Event> saturdayEvents = new ArrayList<>(List.of(event11, event12));
    assertEquals(DayFilter.dayEventFilter(events, DayOfWeek.SATURDAY), saturdayEvents);
    List<Event> sundayEvents = new ArrayList<>(List.of(event13, event14));
    assertEquals(DayFilter.dayEventFilter(events, DayOfWeek.SUNDAY), sundayEvents);
  }

  @Test
  void dayTaskFilter() {
    List<Task> mondayTasks = new ArrayList<>(List.of(task1, task2));
    assertEquals(DayFilter.dayTaskFilter(tasks, DayOfWeek.MONDAY), mondayTasks);
    List<Task> tuesdayTasks = new ArrayList<>(List.of(task3, task4));
    assertEquals(DayFilter.dayTaskFilter(tasks, DayOfWeek.TUESDAY), tuesdayTasks);
    List<Task> wednesdayTasks = new ArrayList<>(List.of(task5, task6));
    assertEquals(DayFilter.dayTaskFilter(tasks, DayOfWeek.WEDNESDAY), wednesdayTasks);
    List<Task> thursdayTasks = new ArrayList<>(List.of(task7, task8));
    assertEquals(DayFilter.dayTaskFilter(tasks, DayOfWeek.THURSDAY), thursdayTasks);
    List<Task> fridayTasks = new ArrayList<>(List.of(task9, task10));
    assertEquals(DayFilter.dayTaskFilter(tasks, DayOfWeek.FRIDAY), fridayTasks);
    List<Task> saturdayTasks = new ArrayList<>(List.of(task11, task12));
    assertEquals(DayFilter.dayTaskFilter(tasks, DayOfWeek.SATURDAY), saturdayTasks);
    List<Task> sundayTasks = new ArrayList<>(List.of(task13, task14));
    assertEquals(DayFilter.dayTaskFilter(tasks, DayOfWeek.SUNDAY), sundayTasks);
  }
}