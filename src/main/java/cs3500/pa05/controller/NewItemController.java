package cs3500.pa05.controller;

import cs3500.pa05.model.Bujo;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.view.ActionEmoji;
import cs3500.pa05.view.WarningPopupView;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;


/**
 * Class for controlling Item creation from popup
 */
public class NewItemController {

  private final Bujo bujo;
  @FXML
  private Label header;
  private final Stage stage;
  @FXML
  Popup journalItemPopup;
  @FXML
  private VBox selectionPane;
  @FXML
  private ComboBox<String> itemTypeChoice;
  private TextField nameInput;
  private TextField descriptionInput;
  private ComboBox<DayOfWeek> dayInput;
  private TextField startInput;
  private TextField durationInput;
  private ComboBox<Boolean> completeInput;
  private Button submitButton;
  private ComboBox<String> emoji;
  private final BujoController ctrl;


  /**
   * creates NewItemControllers
   *
   * @param stage the stage that owns the popup
   * @param bujo the bujo to edit
   */
  NewItemController(Stage stage, Bujo bujo, BujoController ctrl) {
    journalItemPopup = new Popup();
    selectionPane = new VBox();
    this.ctrl = ctrl;
    this.bujo = bujo;
    this.stage = stage;

  }

  /**
   * Initializes popup for task queue
   *
   * @throws IOException exception if .fxml not found
   */
  void initJournalItemPopup() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
        .getResource("journalItemPopup.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    journalItemPopup.getContent().add(s.getRoot());
    Button journalItemCloseButton = new Button(" X ");
    journalItemCloseButton.setOnAction(e -> journalItemPopup.hide());
    journalItemPopup.getContent().add(journalItemCloseButton);
    makeJournalItemPopup();
  }


  /**
   * Creates a popup for item creation
   */
  private void makeJournalItemPopup() {
    List<String> typeList = new ArrayList<>(List.of("Event", "Task"));
    ObservableList<String> types = FXCollections.observableList(typeList);
    itemTypeChoice.setPromptText("Choose Item Type");
    itemTypeChoice.setItems(types);
    initActions();
    this.journalItemPopup.show(stage);

  }

  /**
   * initiates top level actions
   */
  private void initActions() {
    // type selection
    itemTypeChoice.setOnAction(event -> generateMenu());

  }

  /**
   * creates the general menu
   */
  private void generateMenu() {
    String choice = itemTypeChoice.getValue();

    if (choice.equals("Event")) {
      generateEvent();
    } else {
      generateTask();
    }
  }

  /**
   * creates the display for event objects
   */
  private void generateEvent() {
    nameInput = new TextField("Event Name");
    nameInput.maxWidth(100.0);
    nameInput.maxHeight(25.0);
    descriptionInput = new TextField("Event Description");
    descriptionInput.maxWidth(100.0);
    descriptionInput.prefHeight(150.0);
    dayInput = new ComboBox<>();
    dayInput.setPromptText("Select Day of Event");
    dayInput.getItems().setAll(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
    dayInput.maxHeight(25);
    dayInput.maxWidth(100);
    startInput = new TextField("Start");
    startInput.maxHeight(25);
    startInput.maxWidth(100);
    startInput.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
    durationInput = new TextField("End");
    durationInput.maxHeight(25);
    durationInput.maxWidth(100);
    durationInput.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
    submitButton = new Button("Create Event");
    submitButton.setOnAction(event -> createEvent());
    emoji = new ComboBox<>();
    emoji.setPromptText("Emoji");
    List<String> actionEmojis = ActionEmoji.getAllEmojis();
    emoji.maxHeight(25);
    emoji.maxWidth(100);
    emoji.getItems().setAll(actionEmojis);
    selectionPane.getChildren().setAll(header, itemTypeChoice, nameInput,
        descriptionInput, dayInput, startInput, durationInput, emoji, submitButton);
  }


  /**
   * creates the display for task objects
   */
  private void generateTask() {
    nameInput = new TextField("Task Name");
    nameInput.maxWidth(100.0);
    nameInput.maxHeight(25.0);
    descriptionInput = new TextField("Task Description");
    descriptionInput.maxWidth(100.0);
    descriptionInput.prefHeight(150.0);
    dayInput = new ComboBox<>();
    dayInput.setPromptText("Select Day of Task");
    dayInput.getItems().setAll(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
    dayInput.maxHeight(25);
    dayInput.maxWidth(100);
    completeInput = new ComboBox<>();
    completeInput.setPromptText("Is Task Complete?");
    completeInput.getItems().setAll(true, false);
    emoji = new ComboBox<>();
    emoji.setPromptText("Emoji");
    List<String> actionEmojis = ActionEmoji.getAllEmojis();
    emoji.maxHeight(25);
    emoji.maxWidth(100);
    emoji.getItems().setAll(actionEmojis);
    submitButton = new Button("Create Task");
    submitButton.setOnAction(event -> createTask());
    selectionPane.getChildren().setAll(header, itemTypeChoice, nameInput,
        descriptionInput, dayInput, completeInput, emoji, submitButton);

  }

  /**
   * adds a user created task to the bujo
   */
  private void createTask() {
    if (nameInput.getText().isEmpty()
        || dayInput.getValue() == null
        || completeInput.getValue() == null) {
      header.setText("Invalid input, please try again");

    } else if (ctrl.getMaxTasksAllowed() == bujo.getTasksForDay(dayInput.getValue()).size()) {
      WarningPopupView warning = new WarningPopupView(this.stage);
      warning.initWarningPopup("Already have maximum tasks for\n                   "
          + dayInput.getValue());

    } else {
      Task task = new Task(nameInput.getText(), descriptionInput.getText(),
          completeInput.getValue(), dayInput.getValue());
      task.addEmoji(emoji.getValue());
      this.bujo.addTask(task);
      this.ctrl.displayRefresh();  ///////////// TODO CHANGE THIS TEMPORARILY
      //ctrl.addTaskToGrid(task);
      this.journalItemPopup.hide();
    }
  }



  /**
   * adds a user created event to the bujo
   */
  private void createEvent() {
    if (nameInput.getText().isEmpty()
        || dayInput.getValue() == null
        || startInput.getText().isEmpty() || durationInput.getText().isEmpty()) {
      header.setText("Invalid input, please try again");

    } else if (ctrl.getMaxEventsAllowed() == bujo.getEventsForDay(dayInput.getValue()).size()) {
      WarningPopupView warning = new WarningPopupView(this.stage);
      warning.initWarningPopup("Already have maximum events for\n                   "
          + dayInput.getValue());

    } else {
      Event event = new Event(nameInput.getText(), descriptionInput.getText(),
          dayInput.getValue(), Integer.parseInt(startInput.getText()),
          Integer.parseInt(durationInput.getText()));
      event.addEmoji(emoji.getValue());
      this.bujo.addEvent(event);
      this.ctrl.displayRefresh(); ///////////// TODO CHANGE THIS TEMPORARILY
      //ctrl.addEventToGrid(event);
      this.journalItemPopup.hide();
    }
  }

}
