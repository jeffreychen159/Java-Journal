package cs3500.pa05.controller;

import cs3500.pa05.model.Bujo;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represents a weekly overview popup
 */
public class OverviewPopupController {
  private Popup overviewPopup;
  private final Stage stage;
  private final Bujo bujo;

  /**
   * general constructor for overview popups
   *
   * @param stage the host stage
   * @param bujo the bullet journal to pull data from
   */
  public OverviewPopupController(Stage stage, Bujo bujo) {
    this.stage = stage;
    this.bujo = bujo;
  }

  /**
   * Creates a weekly overview popup
   */
  public void initOverviewPopup() {
    overviewPopup = new Popup();
    VBox overviewPane = new VBox();
    overviewPane.setPrefSize(300, 300);
    overviewPane.setStyle("-fx-background-color: white; -fx-border-color: black;");
    overviewPane.setAlignment(Pos.TOP_CENTER);

    Label title = new Label("Weekly Overview:");
    title.setPrefSize(300, 50);
    title.setStyle("-fx-font-size: 20px;");
    title.setAlignment(Pos.CENTER);

    Label stats = new Label(createStatsString());
    stats.setPrefSize(300, 200);
    stats.setAlignment(Pos.CENTER);
    stats.setStyle("-fx-font-size: 20px;");

    overviewPane.getChildren().addAll(title, stats);
    overviewPopup.getContent().add(overviewPane);

    Button closeButton = new Button(" X ");
    closeButton.setOnAction(e -> overviewPopup.hide());
    overviewPopup.getContent().add(closeButton);

    overviewPopup.show(this.stage);
  }

  /**
   * Gathers the stats of weekly overview into a string
   *
   * @return string representation of bujo metadata
   */
  private String createStatsString() {
    int numEvents = bujo.getEvents().size();
    int numTasks = bujo.getTasks().size();
    double tasksCompleted = Math.round(
        (double) bujo.getCompletedTasksCount() * 100 / numTasks * 100.0) / 100.0;
    return "Total Events: " + numEvents
        + "\nTotal Tasks: " + numTasks
        + "\nTasks Completed: " + tasksCompleted + "%";
  }

}
