package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.DayOfWeek;

/**
 * data structure for journal task objects
 */
public class Task extends JournalItem {
  @JsonProperty
  private boolean complete;

  /**
   * main constructor for tasks
   *
   * @param name name of task
   * @param description short description of task
   * @param complete whether task is complete
   * @param day day of week for task
   */
  @JsonCreator
  public Task(@JsonProperty("name") String name, @JsonProperty("description") String description,
              @JsonProperty("complete") boolean complete, @JsonProperty("day") DayOfWeek day) {
    this.name = name;
    this.description = description;
    this.complete = complete;
    this.day = day;
  }

  /**
   * checks if task is complete
   *
   * @return if this task is complete
   */
  public boolean getComplete() {
    return this.complete;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Task otherTask) {
      return this.complete == otherTask.complete
          && this.name.equals(otherTask.name)
          && this.day.equals(otherTask.day)
          && this.description.equals(otherTask.description);
    }
    return false;
  }
}
