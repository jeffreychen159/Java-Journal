package cs3500.pa05.view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;


/**
 * class for storing the background view
 */
public class BackgroundView {
  private final GridPane weekPane;
  private final HBox titleBox;
  private final HBox daysBox;
  private final HBox taskBarBox;
  private List<TextFlow> eventList = new ArrayList<>();
  private List<Label> labelList = new ArrayList<>();

  /**
   * Initializes a Background View
   *
   * @param weekPane   the week pane of the journal
   * @param titleBox   the title box of the journal
   * @param daysBox    the weekdays of the journal
   * @param taskBarBox the task bar box of the journal
   */
  public BackgroundView(GridPane weekPane, HBox titleBox, HBox daysBox, HBox taskBarBox) {
    this.weekPane = weekPane;
    this.titleBox = titleBox;
    this.daysBox = daysBox;
    this.taskBarBox = taskBarBox;
  }

  /**
   * adds a label to the list of labels
   *
   * @param l label to be added
   */
  public void addLabel(Label l) {
    labelList.add(l);
  }

  /**
   * Adds an event to modify its features
   *
   * @param t event block
   */
  public void addEvent(TextFlow t) {
    eventList.add(t);
  }

  /**
   * Sets the colors of backgrounds and texts
   */
  public void setTheme() {
    //TODO: Implement theme
    titleBox.setBackground(new Background(
        new BackgroundFill(Color.rgb(200, 240, 215),
            null, Insets.EMPTY)));

    daysBox.setBackground(new Background(
        new BackgroundFill(Color.rgb(150, 200, 150),
            null, Insets.EMPTY)));

    weekPane.setBackground(new Background(
        new BackgroundFill(Color.rgb(255, 255, 255),
            null, Insets.EMPTY)));

    taskBarBox.setBackground(new Background(
        new BackgroundFill(Color.rgb(150, 200, 150),
            null, Insets.EMPTY)));
    for (Label l : labelList) {
      setDarkText(l);
    }
  }

  /**
   * sets the theme to dark mode
   */
  public void setDark() {
    weekPane.setBackground(new Background(new BackgroundFill(
        Color.rgb(128, 128, 128), null, Insets.EMPTY)));
    titleBox.setBackground(new Background(new BackgroundFill(
        Color.rgb(90, 90, 90), null, Insets.EMPTY)));
    daysBox.setBackground(new Background(new BackgroundFill(
        Color.rgb(150, 150, 150), null, Insets.EMPTY)));
    taskBarBox.setBackground(new Background(new BackgroundFill(
        Color.rgb(128, 128, 128), null, Insets.EMPTY)));

    for (Label l : labelList) {
      setLightText(l);
    }
  }

  /**
   * Sets the theme to colorful mode
   */
  public void setColorful() {
    titleBox.setBackground(new Background(
        new BackgroundFill(Color.rgb(200, 240, 215),
            null, Insets.EMPTY)));

    daysBox.setBackground(new Background(
        new BackgroundFill(Color.rgb(150, 200, 150),
            null, Insets.EMPTY)));

    weekPane.setBackground(new Background(
        new BackgroundFill(Color.rgb(173, 216, 230),
            null, Insets.EMPTY)));

    taskBarBox.setBackground(new Background(
        new BackgroundFill(Color.rgb(150, 200, 150),
            null, Insets.EMPTY)));

    for (Label l : labelList) {
      setDarkText(l);
    }
  }

  /**
   * Sets a custom color for the titleBox
   *
   * @param color color to change
   */
  public void setCustomTitleColor(Color color) {
    titleBox.setBackground(new Background(new BackgroundFill(color, null, Insets.EMPTY)));
  }

  /**
   * Sets a custom color for the daysBox
   *
   * @param color color to change
   */
  public void setCustomWeekdayColor(Color color) {
    daysBox.setBackground(new Background(new BackgroundFill(color, null, Insets.EMPTY)));
  }

  /**
   * Sets a custom color for the weekPane
   *
   * @param color color to change
   */
  public void setCustomWeekpaneColor(Color color) {
    weekPane.setBackground(new Background(new BackgroundFill(color, null, Insets.EMPTY)));
  }

  /**
   * Sets text to this font
   *
   * @param family font family
   */
  public void setFontValue(String family) {
    for (Label l : labelList) {
      l.setFont(new Font(family, 18));
    }
  }

  /**
   * Sets the color for the event
   *
   * @param c color to set
   */
  public void setEventColor(Color c) {
    for (TextFlow t : eventList) {
      t.setBackground(
          new Background(
              new BackgroundFill(
                  c, new CornerRadii(5), new Insets(5, 5, 5, 5))));
    }
  }

  /**
   * Sets the text color to light
   *
   * @param l text label
   */
  private void setLightText(Label l) {
    l.setTextFill(Color.rgb(255, 255, 255));
  }

  /**
   * Sets the text color to dark
   *
   * @param l text label
   */
  private void setDarkText(Label l) {
    l.setTextFill(Color.rgb(0, 0, 0));
  }
}
