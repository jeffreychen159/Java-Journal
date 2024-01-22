package cs3500.pa05.model;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

/**
 * filter for days
 */
public class DayFilter {
  /**
   * filters event based on day
   *
   * @param list of events
   * @param day desired day
   * @return events that occur on the day
   */
  public static List<Event> dayEventFilter(List<Event> list, DayOfWeek day) {
    return list.stream().filter(f -> f.day.equals(day)).collect(Collectors.toList());
  }

  /**
   * filters tasks based on day
   *
   * @param list of tasks
   * @param day of the week
   * @return the filtered list
   */
  public static List<Task> dayTaskFilter(List<Task> list, DayOfWeek day) {
    return list.stream().filter(f -> f.day.equals(day)).collect(Collectors.toList());
  }
}
