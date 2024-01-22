package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;


/**
 * BulletJournal object, combining metadata with
 events and tasks
 */
public class Bujo {
  @JsonProperty
  private String name;
  @JsonProperty
  List<Event> events;
  @JsonProperty
  List<Task> tasks;

  @JsonProperty
  int eventMax;

  @JsonProperty
  int taskMax;
  @JsonProperty
  String notes;

  /**
   * main constructor for bujos
   *
   * @param name the name
   * @param events list of events
   * @param tasks list of tasks
   * @param taskMax maximum tasks
   * @param eventMax maximum events
   * @param notes the notes
   */
  @JsonCreator
  public Bujo(@JsonProperty("name") String name, @JsonProperty("events") List<Event> events,
              @JsonProperty("tasks") List<Task> tasks, @JsonProperty("taskMax") int taskMax,
              @JsonProperty("eventMax") int eventMax, @JsonProperty("notes") String notes) {
    this.name = name;
    this.events = events;
    this.tasks = tasks;
    this.eventMax = eventMax;
    this.taskMax = taskMax;
    this.notes = notes;
  }

  /**
   * generic constructor for bujos
   *
   * @param name the name
   * @param events list of events
   * @param tasks list of tasks
   */
  public Bujo(String name, List<Event> events, List<Task> tasks) {
    this.name = name;
    this.events = events;
    this.tasks = tasks;
    this.eventMax = 8;
    this.taskMax = 8;
    this.notes = "";
  }


  /**
   * adds a task to this bujo
   *
   * @param task task to add
   */
  @JsonIgnore
  public void addTask(Task task) {
    this.tasks.add(task);
  }

  /**
   * adds an event to this bujo
   *
   * @param event event to add
   */

  @JsonIgnore
  public void addEvent(Event event) {
    this.events.add(event);
  }

  /**
   * change the maximum event count
   *
   * @param i event count
   */
  @JsonIgnore
  public void setEventMax(int i) {
    this.eventMax = i;
  }

  /**
   * change the maximum task count
   *
   * @param i task count
   */
  public void setTaskMax(int i) {
    this.taskMax = i;
  }

  /**
   * change the weeks name
   *
   * @param s week name
   */
  public void setName(String s) {
    this.name = s;
  }

  /**
   * return the name
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * return the events list
   *
   * @return the events
   */
  public List<Event> getEvents() {
    return events;
  }

  /**
   * return this.tasks
   *
   * @return the task list
   */
  public List<Task> getTasks() {
    return tasks;
  }

  /**
   * Gets all the events for a specific day
   *
   * @param day day of week
   * @return list of events for that day
   */
  @JsonIgnore
  public List<Event> getEventsForDay(DayOfWeek day) {
    List<Event> temp = new ArrayList<>();
    for (Event event : events) {
      if (event.getDay().equals(day)) {
        temp.add(event);
      }
    }
    return temp;
  }

  /**
   * Gets all the tasks for a specific day
   *
   * @param day day of week
   * @return list of tasks for that day
   */
  @JsonIgnore
  public List<Task> getTasksForDay(DayOfWeek day) {
    List<Task> temp = new ArrayList<>();
    for (Task task : tasks) {
      if (task.getDay().equals(day)) {
        temp.add(task);
      }
    }
    return temp;
  }

  /**
   * Gets the highest amount of events in a day in the week
   *
   * @return highest number of events in a day
   */
  @JsonIgnore
  public int getCurrMaxEvents() {
    int currMaxEvents = 0;
    for (int i = 0; i < DayOfWeek.values().length; i++) {
      DayOfWeek day = DayOfWeek.values()[i];
      List<Event> dayEvents = getEventsForDay(day);
      if (dayEvents.size() > currMaxEvents) {
        currMaxEvents = dayEvents.size();
      }
    }
    return currMaxEvents;
  }

  /**
   * Gets the highest amount of tasks in a day in the week
   *
   * @return highest number of tasks in a day
   */
  @JsonIgnore
  public int getCurrMaxTasks() {
    int currMaxTasks = 0;
    for (int i = 0; i < DayOfWeek.values().length; i++) {
      DayOfWeek day = DayOfWeek.values()[i];
      List<Task> dayTasks = getTasksForDay(day);
      if (dayTasks.size() > currMaxTasks) {
        currMaxTasks = dayTasks.size();
      }
    }
    return currMaxTasks;
  }

  /**
   * check how many tasks are complete
   *
   * @return count of complete tasks
   */
  @JsonIgnore
  public int getCompletedTasksCount() {
    int count = 0;
    for (Task task : this.tasks) {
      if (task.getComplete()) {
        count++;
      }
    }
    return count;
  }



  /**
   * updates the notes
   *
   * @param newText new notes
   */
  public void setNotes(String newText) {
    this.notes = newText;
  }

  /**
   * returns the notes
   *
   * @return the notes
   */
  public String getNotes() {
    return this.notes;
  }

  /**
   * returns the total number of tasks
   *
   * @return total number of tasks
   */
  @JsonIgnore
  public int getAllTasks() {
    return this.tasks.size();
  }

  /**
   * override for equals
   *
   * @param other another object
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Bujo otherBujo) {
      return this.name.equals(otherBujo.name)
          && this.events.equals(otherBujo.events)
          && this.tasks.equals(otherBujo.tasks)
          && this.eventMax == otherBujo.eventMax
          && this.taskMax == otherBujo.taskMax
          && this.notes.equals(otherBujo.notes);
    } else {
      return false;
    }
  }

}
