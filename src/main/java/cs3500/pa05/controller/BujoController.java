package cs3500.pa05.controller;

import cs3500.pa05.model.Bujo;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.JsonUtils;
import cs3500.pa05.model.Task;
import cs3500.pa05.view.BackgroundView;
import cs3500.pa05.view.ProgressBarView;
import cs3500.pa05.view.WarningPopupView;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * main control class for bullet journals
 */
public class BujoController {
  private final HostServices services;
  private Bujo bujo;
  private final Stage stage;
  @FXML
  private Label notes;
  @FXML
  private TextField titleTextField;
  @FXML
  private Button saveButton;
  @FXML
  private Button taskQueueButton;
  @FXML
  private Button newItemButton;
  @FXML
  private Popup customThemePopup;
  @FXML
  private Popup taskQueuePopup;
  @FXML
  private Popup journalItemPopup;
  @FXML
  private ChoiceBox themeChoiceBox;
  @FXML
  private Button overviewButton;
  @FXML
  private HBox titleBox;
  @FXML
  private HBox daysBox;
  @FXML
  private GridPane weekPane;
  @FXML
  private HBox taskBarBox;
  @FXML
  private Label sunday;
  @FXML
  private Label monday;
  @FXML
  private Label tuesday;
  @FXML
  private Label wednesday;
  @FXML
  private Label thursday;
  @FXML
  private Label friday;
  @FXML
  private Label saturday;
  @FXML
  private Label title;
  private File workingFile;
  @FXML
  private ChoiceBox maxTasksChoiceBox;
  private int maxTasksAllowed = 5;
  @FXML
  private ChoiceBox maxEventsChoiceBox;
  private NewItemController itemController;
  private int maxEventsAllowed = 5;
  @FXML
  private Button loadButton;
  @FXML
  private ProgressBar progressBar;
  @FXML
  private TextArea notesArea;
  @FXML
  private Button customTheme;
  private ProgressBarView progressBarView;
  private BackgroundView backgroundView;

  private TaskQueueController taskQueueController;
  private WarningPopupView warningPopupView;
  private OverviewPopupController overviewPopupController;

  private Map<DayOfWeek, Integer> weekIndMap;
  private List<VBox> dayBoxes;
  private CustomThemePopupController customThemeController;
  private SplashScreenController splashScreenController;


  /**
   * Constructs a BujoController
   *
   * @param stage    stage for bujo
   * @param services the applications services
   */
  public BujoController(Stage stage, HostServices services) {
    this.bujo = new Bujo("", new ArrayList<>(), new ArrayList<>());
    this.stage = stage;
    this.services = services;

    weekIndMap = new HashMap<>();
    initWeekIndMap();
    this.dayBoxes = new ArrayList<>();
  }

  /**
   * run method for bullet journals
   *
   * @throws IOException exception if .fxml for extra windows not found
   */
  public void run() throws IOException {
    initBackgroundView();
    backgroundView.setTheme();
    initActions();
    initThemeChoiceBox();
    initMaxTasksChoiceBox();
    initMaxEventsChoiceBox();
    initWeekGrid();
    titleTextField.setText("Week name...");
    titleTextField.setAlignment(Pos.CENTER);
    this.itemController = new NewItemController(this.stage, bujo, this);
    this.taskQueuePopup = new Popup();
    this.customThemePopup = new Popup();
    initTaskQueuePopup();
    initProgressBar();
    //this.splashScreenController = new SplashScreenController(this.stage); //remove temporarily
    //splashScreenController.initSplashScreenPopup();


    Stage stage = new Stage(); // maybe use stage passed in as arg?

  }

  /**
   * load file
   *
   * @param stage a stage
   */
  private void loadFile(Stage stage) {
    File bujoFile;
    FileChooser chooser = new FileChooser();
    chooser.setTitle("Open .bujo File");
    bujoFile = chooser.showOpenDialog(stage);
    this.workingFile = bujoFile;
    this.bujo = JsonUtils.readBujo(bujoFile);
    this.titleTextField.setText(bujo.getName());
    this.notesArea.setText(bujo.getNotes());
    this.displayRefresh();
  }

  /**
   * Gets maxTasksAllowed field
   *
   * @return this maxTasksAllowed
   */
  public int getMaxTasksAllowed() {
    return this.maxTasksAllowed;
  }

  /**
   * Gets maxEventsAllowed field
   *
   * @return this maxEventsAllowed
   */
  public int getMaxEventsAllowed() {
    return this.maxEventsAllowed;
  }

  /**
   * Sets on-action events
   */
  private void initActions() {
    // save button
    saveButton.setOnAction(event -> handleSave());

    // task queue button
    taskQueueButton.setOnAction(e -> makeTaskPopup());
    newItemButton.setOnAction(e -> makeJournalItemPopup());

    themeChoiceBox.setOnAction(event -> changeBackground());
    maxEventsChoiceBox.setOnAction(
        event -> setEventMax());
    maxTasksChoiceBox.setOnAction(
        event -> setTaskMax());
    titleTextField.textProperty().addListener((obs, oldText, newText) -> {
      bujo.setName(newText);
    });

    loadButton.setOnAction(event -> loadFile(stage));

    overviewButton.setOnAction(event -> makeOverview());
    notesArea.textProperty().addListener((obs, oldText, newText) -> {
      bujo.setNotes(newText);
    });

    customTheme.setOnAction(event -> makeCustomTheme());
  }

  private void setTaskMax() {
    String boxText = maxTasksChoiceBox.getValue().toString();
    int i = Integer.parseInt(boxText.substring(boxText.length() - 1));
    bujo.setTaskMax(i);
  }

  private void setEventMax() {
    String boxText = maxEventsChoiceBox.getValue().toString();
    int i = Integer.parseInt(boxText.substring(boxText.length() - 1));
    bujo.setEventMax(i);
  }

  private void initThemeChoiceBox() {
    List<String> themeList = new ArrayList<>();
    themeList.add("Dark Mode");
    themeList.add("Light Mode");
    themeList.add("Colorful Mode");
    ObservableList<String> themes = FXCollections.observableList(themeList);
    themeChoiceBox.setValue("Light Mode");
    themeChoiceBox.setItems(themes);

    themeChoiceBox.setOnAction(event -> changeBackground());

    backgroundView.addLabel(sunday);
    backgroundView.addLabel(monday);
    backgroundView.addLabel(tuesday);
    backgroundView.addLabel(wednesday);
    backgroundView.addLabel(thursday);
    backgroundView.addLabel(friday);
    backgroundView.addLabel(saturday);
    backgroundView.addLabel(notes);
  }

  private void initBackgroundView() {
    backgroundView = new BackgroundView(weekPane, titleBox, daysBox, taskBarBox);
  }

  private void changeBackground() {
    String value = themeChoiceBox.getValue().toString();

    switch (value) {
      case "Dark Mode" -> backgroundView.setDark();
      case "Colorful Mode" -> backgroundView.setColorful();
      case "Light Mode" -> backgroundView.setTheme();
      default -> backgroundView.setTheme();
    }
  }

  /**
   * handler for save button, should produce a file with data for saving
   */
  private void handleSave() {
    if (this.workingFile == null) {
      this.workingFile = new File(bujo.getName() + ".bujo");
    }

    JsonUtils.writeRecord(bujo, workingFile);

    System.out.println("Saving " + this.titleTextField.getText());
    System.out.println("Max Tasks: " + this.maxTasksAllowed);

  }

  /**
   * Initializes the max tasks choice box
   */
  private void initMaxTasksChoiceBox() {
    List<String> numList = new ArrayList<>();
    for (int i = 1; i < maxTasksAllowed + 1; i++) {
      numList.add("Max Tasks: " + i);
    }

    ObservableList<String> nums = FXCollections.observableList(numList);
    maxTasksChoiceBox.setValue("Max Tasks: " + this.maxTasksAllowed);
    maxTasksChoiceBox.setItems(nums);

    maxTasksChoiceBox.setOnAction(event -> {
      int currMaxTasks = bujo.getCurrMaxTasks();

      int temp = Integer.valueOf(maxTasksChoiceBox.getValue().toString().substring(11));
      if (temp < currMaxTasks) {
        makeWarning("There are already " + currMaxTasks + " tasks present"
            + "\nplease select a valid option");
        maxTasksChoiceBox.getSelectionModel().select("Max Tasks: " + currMaxTasks);
      } else {
        this.maxTasksAllowed = temp;
      }
    });
  }

  /**
   * Initializes the max evemts choice box
   */
  private void initMaxEventsChoiceBox() {
    List<String> numList = new ArrayList<>();
    for (int i = 1; i < maxTasksAllowed + 1; i++) {
      numList.add("Max Events: " + i);
    }

    ObservableList<String> nums = FXCollections.observableList(numList);
    maxEventsChoiceBox.setValue("Max Events: " + this.maxEventsAllowed);
    maxEventsChoiceBox.setItems(nums);

    maxEventsChoiceBox.setOnAction(event -> {
      int currMaxEvents = bujo.getCurrMaxEvents();

      int temp = Integer.valueOf(maxEventsChoiceBox.getValue().toString().substring(12));
      if (temp < currMaxEvents) {
        makeWarning("There are already " + currMaxEvents + " events present"
            + "\nplease select a valid option");
        maxEventsChoiceBox.getSelectionModel().select("Max Tasks: " + currMaxEvents);
      } else {
        this.maxEventsAllowed = temp;
      }
    });
  }

  /**
   * Creates a warning popup
   *
   * @param msg warning message
   */
  private void makeWarning(String msg) {
    warningPopupView = new WarningPopupView(this.stage);
    warningPopupView.initWarningPopup(msg);
  }

  /**
   * Initializes popup for task queue
   *
   * @throws IOException exception if .fxml not found
   */
  private void initTaskQueuePopup() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
        .getResource("taskQueuePopup.fxml"));
    Scene s = loader.load();
    taskQueuePopup.getContent().add((Node) s.getRoot());
    Button taskQueueCloseButton = new Button(" X ");
    taskQueueCloseButton.setOnAction(e -> taskQueuePopup.hide());
    taskQueuePopup.getContent().add(taskQueueCloseButton);

    this.taskQueuePopup.show(this.stage);
  }

  /**
   * Initializes the progress bar
   */
  private void initProgressBar() {
    progressBar.setStyle("-fx-accent: lime");
    this.progressBarView = new ProgressBarView(progressBar);
  }


  /**
   * Creates a popup for task queue
   */
  private void makeTaskPopup() {
    try {
      this.taskQueueController = new TaskQueueController(this.stage, bujo);
      this.taskQueueController.initTaskQueuePopup();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  /**
   * Creates a popup for item creation
   */
  private void makeJournalItemPopup() {
    try {
      this.itemController.initJournalItemPopup();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * updates the view to show recently added tasks
   */
  public void displayRefresh() {
    this.titleTextField.setText(bujo.getName());

    this.notesArea.setText(bujo.getNotes());

    List<DayOfWeek> days = new ArrayList<>(
        List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));

    for (int i = 0; i < days.size(); i++) {
      List<Event> dayEvents = bujo.getEventsForDay(days.get(i));
      for (int j = 0; j < dayEvents.size(); j++) {
        Event event = dayEvents.get(j);
        if (!event.getDisplayed()) {
          event.changeDisplayed();
          addEventToGrid(event);
        }
      }

      List<Task> dayTasks = bujo.getTasksForDay(days.get(i));
      for (int k = 0; k < dayTasks.size(); k++) {
        Task task = dayTasks.get(k);
        if (!task.getDisplayed()) {
          task.changeDisplayed();
          addTaskToGrid(dayTasks.get(k));
        }
      }
    }
    progressBarView.setProgressBar(totalTaskProgress());
  }

  /**
   * Initializes the grid pane of the week with vbox and notes area
   */
  private void initWeekGrid() {
    for (int i = 0; i < this.weekPane.getColumnCount(); i++) {
      VBox temp = new VBox();
      this.dayBoxes.add(temp);
      weekPane.add(temp, i, 0);
    }

    VBox notesBox = new VBox();
    weekPane.add(notesBox, 7, 0);
    notesArea = new TextArea();
    notesArea.setPrefSize(75, 600);
    notesArea.setWrapText(true);
    notesBox.getChildren().add(notesArea);
  }

  /**
   * Initializes map for WeekIndMap
   */
  private void initWeekIndMap() {
    weekIndMap.put(DayOfWeek.SUNDAY, 0);
    weekIndMap.put(DayOfWeek.MONDAY, 1);
    weekIndMap.put(DayOfWeek.TUESDAY, 2);
    weekIndMap.put(DayOfWeek.WEDNESDAY, 3);
    weekIndMap.put(DayOfWeek.THURSDAY, 4);
    weekIndMap.put(DayOfWeek.FRIDAY, 5);
    weekIndMap.put(DayOfWeek.SATURDAY, 6);
  }

  /**
   * Adds an event to the week grid pane
   *
   * @param event event to be added and displayed
   */
  public void addEventToGrid(Event event) {
    DayOfWeek day = event.getDay();
    int dayInd = weekIndMap.get(day);
    VBox currBox = dayBoxes.get(dayInd);

    TextFlow eventImg = resolveEventLinks(event);
    eventImg.setPrefSize(125, 75);
    currBox.getChildren().add(eventImg);
  }


  /**
   * Adds a task to the week grid pane
   *
   * @param task task to be added and displayed
   */
  public void addTaskToGrid(Task task) {
    DayOfWeek day = task.getDay();
    int dayInd = weekIndMap.get(day);
    VBox currBox = dayBoxes.get(dayInd);

    TextFlow taskImg = resolveTaskLinks(task);
    taskImg.setPrefSize(125, 50);
    currBox.getChildren().add(taskImg);
  }

  private double totalTaskProgress() {
    int allComplete = this.bujo.getCompletedTasksCount();
    int allTasks = this.bujo.getAllTasks();
    return ((double) allComplete / allTasks);
  }

  private TextFlow resolveEventLinks(Event anEvent) {
    List<Node> fulltext = new ArrayList<>();
    fulltext.add(new Text(anEvent.getName() + System.lineSeparator()));

    Scanner scan = new Scanner(anEvent.getDescription());
    while (scan.hasNext()) {
      String word = scan.next();
      try {
        URL u = new URL(word);
        u.toURI();
      } catch (URISyntaxException | MalformedURLException e) {
        fulltext.add(new Text(word));
        fulltext.add(new Text(" "));
        continue;
      }
      Hyperlink link = new Hyperlink(word);
      link.setOnAction(event -> services.showDocument(word));
      link.setMaxWidth(130);
      fulltext.add(link);
      fulltext.add(new Text(" "));
    }
    fulltext.add(new Text(System.lineSeparator()));
    fulltext.add(new Text("Start: " + anEvent.getTime()
        + System.lineSeparator()));
    fulltext.add(new Text("End: " + String.valueOf(anEvent.getDuration())));
    fulltext.add(new Text(System.lineSeparator() + anEvent.getEmoji()));
    TextFlow description = new TextFlow();
    description.getChildren().setAll(fulltext);
    description.setStyle("-fx-padding: 2;"
        + "-fx-pref-width: 200;"
        + "-fx-border-style: solid inside;"
        + "-fx-border-width: 1;"
        + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;"
        + "-fx-border-color: black;");
    backgroundView.addEvent(description);
    return description;
  }

  private TextFlow resolveTaskLinks(Task task) {
    List<Node> fulltext = new ArrayList<>();
    fulltext.add(new Text(task.getName() + System.lineSeparator()));

    Scanner scan = new Scanner(task.getDescription());
    while (scan.hasNext()) {
      String word = scan.next();
      try {
        URL u = new URL(word);
        u.toURI();
      } catch (URISyntaxException | MalformedURLException e) {
        fulltext.add(new Text(word));
        fulltext.add(new Text(" "));
        continue;
      }
      Hyperlink link = new Hyperlink(word);
      link.setOnAction(event -> services.showDocument(word));
      link.setMaxWidth(130);
      fulltext.add(link);
      fulltext.add(new Text(" "));
      fulltext.add(new Text(task.getEmoji()));
    }
    fulltext.add(new Text(System.lineSeparator()));
    fulltext.add(new Text(task.getEmoji()));
    TextFlow description = new TextFlow();
    description.getChildren().setAll(fulltext);
    description.setStyle("-fx-padding: 2;"
        + "-fx-pref-width: 200;"
        + "-fx-border-style: solid inside;"
        + "-fx-border-width: 1;"
        + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;"
        + "-fx-border-color: black;");
    if (task.getComplete()) {
      description.setBackground(new Background(
          new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(5), new Insets(5, 5, 5, 5))));
    } else {
      description.setBackground(new Background(
          new BackgroundFill(Color.LIGHTPINK, new CornerRadii(5), new Insets(5, 5, 5, 5))));
    }
    return description;
  }

  /**
   * Creates a weekly overview popup
   */
  private void makeOverview() {
    overviewPopupController = new OverviewPopupController(this.stage, this.bujo);
    overviewPopupController.initOverviewPopup();
  }

  /**
   * Creates a custom theme popup
   */
  private void makeCustomTheme() {
    customThemeController = new CustomThemePopupController(this.stage, this.backgroundView);
    customThemeController.initCustomThemePopup();
  }


}
