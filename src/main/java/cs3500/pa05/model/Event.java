package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.DayOfWeek;

/**
 * object for creating Events
 */
public class Event extends JournalItem {

  /*
  likely need to express in 24 hour time
  or switch to proper DateTime object with a static year and day
   */
  @JsonProperty
  int time;

  @JsonProperty
  int duration;

  /**
   * general constructor for events
   *
   * @param name name of event
   * @param description event description
   * @param day day of event
   * @param time event start time
   * @param duration duration in minutes
   */
  @JsonCreator
  public Event(@JsonProperty("name") String name, @JsonProperty("description") String description,
               @JsonProperty("day") DayOfWeek day, @JsonProperty("time") int time,
               @JsonProperty("duration") int duration) {
    this.name = name;
    this.description = description;
    this.day = day;
    this.time = time;
    this.duration = duration;
  }

  public int getTime() {
    return this.time;
  }

  public int getDuration() {
    return this.duration;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Event otherEvent) {
      return this.duration == otherEvent.duration
          && this.name.equals(otherEvent.name)
          && this.day.equals(otherEvent.day)
          && this.time == otherEvent.time
          && this.description.equals(otherEvent.description);
    }
    return false;
  }
}
