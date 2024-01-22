package cs3500.pa05.controller;

import cs3500.pa05.model.Bujo;
import cs3500.pa05.model.Task;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represent the task queue popup
 */
public class TaskQueueController {
  private Popup taskQueuePopup;
  private VBox taskQueuePane;
  private ListView listView;
  private ObservableList<String> items = FXCollections.observableArrayList();
  private Stage stage;
  private Bujo bujo;

  /**
   * Creates a TaskQueueController
   *
   * @param stage stage
   * @param bujo bujo model
   */
  public TaskQueueController(Stage stage, Bujo bujo) {
    this.stage = stage;
    this.taskQueuePopup = new Popup();
    this.taskQueuePane = new VBox();
    this.listView = new ListView();
    this.bujo = bujo;
  }

  /**
   * Initializes a task queue popup
   *
   * @throws IOException exception
   */
  public void initTaskQueuePopup() throws IOException {
    taskQueuePane.setPrefSize(300, 500);
    taskQueuePane.setStyle("-fx-background-color: white;");
    taskQueuePane.setAlignment(Pos.CENTER);

    Label title = new Label("Task Queue");
    title.setPrefSize(300, 30);
    title.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-font-size: 20px;");
    title.setAlignment(Pos.CENTER);
    taskQueuePane.getChildren().add(title);

    listView.setItems(items);

    taskQueuePopup.getContent().add(taskQueuePane);
    taskQueuePopup.getContent().add(listView);

    taskQueuePane.getChildren().add(listView);

    initListView();

    Button taskQueueCloseButton = new Button(" X ");
    taskQueueCloseButton.setOnAction(e -> taskQueuePopup.hide());
    taskQueuePopup.getContent().add(taskQueueCloseButton);

    this.taskQueuePopup.show(this.stage);
  }

  /**
   * Initializes ListView with tasks and events
   */
  private void initListView() {
    List<Task> tasks = bujo.getTasks();

    for (int i = 0; i < tasks.size(); i++) {
      Task task = tasks.get(i);
      String taskName = task.getName();
      String taskDone = String.valueOf(task.getComplete());
      String output = "Name: " + taskName + " | Completed: " + taskDone;
      this.items.add(output);
    }
  }

}
