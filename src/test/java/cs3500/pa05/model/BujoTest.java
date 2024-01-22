package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BujoTest {
  Bujo bujo;
  List<Event> events;
  List<Task> tasks;

  /**
   * Initializes examples for testing
   */
  @BeforeEach
  void initData() {
    events = new ArrayList<>();
    tasks = new ArrayList<>();
    events.add(new Event("Event 1", "Event Desc 1", DayOfWeek.SUNDAY, 1230, 30));
    tasks.add(new Task("Task 1", "Task Desc 1", false, DayOfWeek.SUNDAY));
    bujo = new Bujo("Bujo 1", events, tasks);
  }

  @Test
  void addTask() {
    assertEquals(1, tasks.size());
    bujo.addTask(new Task("Task 2", "Task Desc 2", false, DayOfWeek.SUNDAY));
    assertEquals(2, tasks.size());
  }

  @Test
  void addEvent() {
    assertEquals(1, events.size());
    bujo.addEvent(new Event("Event 2", "Event Desc 2", DayOfWeek.SUNDAY,
        0100, 30));
    assertEquals(2, events.size());
  }

  @Test
  void setEventMax() {
    assertEquals(8, bujo.eventMax);
    bujo.setEventMax(5);
    assertEquals(5, bujo.eventMax);
  }

  @Test
  void setNotes() {
    String s = " adfas f";
    bujo.setNotes(s);
    assertEquals(s, bujo.notes);
  }

  @Test
  void getAllTasks() {
    assertEquals(1, bujo.getAllTasks());
    bujo.getTasks().clear();
    assertEquals(0, bujo.getAllTasks());
  }

  @Test
  void setTaskMax() {
    assertEquals(8, bujo.taskMax);
    bujo.setTaskMax(5);
    assertEquals(5, bujo.taskMax);
  }

  @Test
  void setName() {
    assertEquals("Bujo 1", bujo.getName());
    bujo.setName("Test 1");
    assertEquals("Test 1", bujo.getName());
  }

  @Test
  void getName() {
    String expected = "Bujo 1";
    assertEquals(expected, bujo.getName());
  }

  @Test
  void getEvents() {
    assertEquals(1, bujo.getEvents().size());
    assertEquals("Event 1", bujo.getEvents().get(0).name);
    assertEquals("Event Desc 1", bujo.getEvents().get(0).description);
    assertEquals(DayOfWeek.SUNDAY, bujo.getEvents().get(0).day);
    assertEquals(1230, bujo.getEvents().get(0).getTime());
    assertEquals(30, bujo.getEvents().get(0).getDuration());
  }

  @Test
  void getTasks() {
    assertEquals(1, bujo.getTasks().size());
    assertEquals("Task 1", bujo.getTasks().get(0).name);
    assertEquals("Task Desc 1", bujo.getTasks().get(0).description);
    assertFalse(bujo.getTasks().get(0).getComplete());
    assertEquals(DayOfWeek.SUNDAY, bujo.getTasks().get(0).day);
  }

  @Test
  void getEventsForDay() {
    List<Event> eventsEx = bujo.getEventsForDay(DayOfWeek.SUNDAY);
    assertEquals(1, eventsEx.size());
    assertEquals("Event 1", eventsEx.get(0).name);

    List<Event> eventsEx2 = bujo.getEventsForDay(DayOfWeek.FRIDAY);
    assertEquals(0, eventsEx2.size());

    bujo.addEvent(new Event("Event 2", "Event Desc 2", DayOfWeek.FRIDAY, 0100, 30));
    List<Event> eventsEx3 = bujo.getEventsForDay(DayOfWeek.FRIDAY);
    assertEquals(1, eventsEx3.size());
    assertEquals("Event 2", eventsEx3.get(0).name);
  }

  @Test
  void getTasksForDay() {
    List<Task> tasksEx = bujo.getTasksForDay(DayOfWeek.SUNDAY);
    assertEquals(1, tasksEx.size());
    assertEquals("Task 1", tasksEx.get(0).name);

    List<Task> tasksEx2 = bujo.getTasksForDay(DayOfWeek.FRIDAY);
    assertEquals(0, tasksEx2.size());

    bujo.addTask(new Task("Task 2", "Task Desc 2", false, DayOfWeek.FRIDAY));
    List<Task> tasksEx3 = bujo.getTasksForDay(DayOfWeek.FRIDAY);
    assertEquals(1, tasksEx3.size());
    assertEquals("Task 2", tasksEx3.get(0).name);

  }

  @Test
  void getCurrMaxEvents() {
    assertEquals(1, bujo.getCurrMaxEvents());

    bujo.addEvent(new Event("Event 2", "Event Desc 2", DayOfWeek.WEDNESDAY,
        0100, 30));
    bujo.addEvent(new Event("Event 2", "Event Desc 2", DayOfWeek.WEDNESDAY,
        0100, 30));
    bujo.addEvent(new Event("Event 2", "Event Desc 2", DayOfWeek.WEDNESDAY,
        0100, 30));
    assertEquals(3, bujo.getCurrMaxEvents());

    bujo.addEvent(new Event("Event 3", "Event Desc 3", DayOfWeek.THURSDAY, 0100, 30));
    assertEquals(3, bujo.getCurrMaxEvents());

    bujo.addEvent(new Event("Event 3", "Event Desc 3", DayOfWeek.THURSDAY, 0100, 30));
    bujo.addEvent(new Event("Event 3", "Event Desc 3", DayOfWeek.THURSDAY, 0100, 30));
    bujo.addEvent(new Event("Event 3", "Event Desc 3", DayOfWeek.THURSDAY, 0100, 30));
    assertEquals(4, bujo.getCurrMaxEvents());
  }

  @Test
  void getCurrMaxTasks() {
    assertEquals(1, bujo.getCurrMaxTasks());

    bujo.addTask(new Task("Task 2", "Task Desc 2", false, DayOfWeek.FRIDAY));
    bujo.addTask(new Task("Task 2", "Task Desc 2", false, DayOfWeek.FRIDAY));
    bujo.addTask(new Task("Task 2", "Task Desc 2", false, DayOfWeek.FRIDAY));
    assertEquals(3, bujo.getCurrMaxTasks());

    bujo.addTask(new Task("Task 3", "Task Desc 3", false, DayOfWeek.WEDNESDAY));
    bujo.addTask(new Task("Task 3", "Task Desc 3", false, DayOfWeek.WEDNESDAY));
    assertEquals(3, bujo.getCurrMaxTasks());

  }

  @Test
  void getCompletedTasksCount() {
    assertEquals(0, bujo.getCompletedTasksCount());
    bujo.addTask(new Task("Task 2", "Task Desc 2", true, DayOfWeek.FRIDAY));
    bujo.addTask(new Task("Task 2", "Task Desc 2", true, DayOfWeek.FRIDAY));
    assertEquals(2, bujo.getCompletedTasksCount());

  }

  @Test
  void equals() {
    Bujo bujo2 = new Bujo(bujo.getName(), new ArrayList<>(bujo.getEvents()),
        new ArrayList<>(bujo.getTasks()), bujo.taskMax,
        bujo.taskMax, bujo.getNotes());
    assertNotEquals(" ", bujo);
    bujo2.setName(" ");
    assertNotEquals(bujo, bujo2);
    bujo2.setName(bujo.getName());
    bujo2.getEvents().clear();
    assertNotEquals(bujo, bujo2);
    bujo2.getEvents().addAll(bujo.getEvents());
    bujo2.getTasks().clear();
    assertNotEquals(bujo, bujo2);
    bujo2.getTasks().addAll(bujo.getTasks());
    bujo2.setTaskMax(1);
    assertNotEquals(bujo, bujo2);
    bujo2.setTaskMax(8);
    bujo2.setEventMax(1);
    assertNotEquals(bujo, bujo2);
    bujo2.setEventMax(8);
    bujo2.setNotes(" d ");
    assertNotEquals(bujo, bujo2);
  }
}