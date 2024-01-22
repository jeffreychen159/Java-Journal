package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.DayOfWeek;
import java.util.Objects;

/**
 * interface for journal events
 */
public abstract class JournalItem {
  @JsonProperty
  String name;
  @JsonProperty
  String description;
  @JsonProperty
  DayOfWeek day;
  @JsonIgnore
  String emoji;

  boolean displayed = false;

  /**
   * returns the items name
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * returns the description
   *
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * returns the day
   *
   * @return the day
   */
  public DayOfWeek getDay() {
    return this.day;
  }

  /**
   * updates that this has been displayed
   */
  public void changeDisplayed() {
    this.displayed = true;
  }

  /**
   * returns if this has been displayed
   *
   * @return if this has been displayed
   */
  public boolean getDisplayed() {
    return this.displayed;
  }

  public void addEmoji(String emoji) {
    this.emoji = emoji;
  }

  /**
   * returns the emoji for the mood
   *
   * @return String of emoji
   */
  public String getEmoji() {
    return Objects.requireNonNullElse(this.emoji, "");
  }


}
