package cs3500.pa05.controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represents a Splash Screen
 */
public class SplashScreenController {
  private Stage stage;
  private Scene scene;
  private VBox splashScreenBox;
  private Scene mainScene;

  /**
   * Initializes a SplashScreenController
   *
   * @param stage the primary stage
   * @param mainScene the main scene to switch to when starting application
   */
  public SplashScreenController(Stage stage, Scene mainScene) {
    this.stage = stage;
    splashScreenBox = new VBox();
    scene = new Scene(splashScreenBox, 1000, 600);
    this.mainScene = mainScene;
  }

  /**
   * Initializes the splash screen
   */
  public void initSplashScreen() {
    splashScreenBox.setPrefSize(1000, 600);
    splashScreenBox.setStyle("-fx-background-color: rgb(150, 200, 150);" + "-fx-alignment: center");

    Label title = new Label("Welcome!");
    title.setStyle("-fx-font-size: 60;");
    title.setPrefSize(300, 100);
    title.setAlignment(Pos.CENTER);

    Label instructions = new Label("Press \"Start\" to begin");
    instructions.setStyle("-fx-font-size: 24;");
    instructions.setPrefSize(300, 100);
    instructions.setAlignment(Pos.CENTER);

    Button startButton = new Button("Start");
    //startButton.setTextFill(Color.rgb(0, 255, 0));
    startButton.setStyle("-fx-font-size: 24;"
        + "-fx-pref-width: 100;"
        + "-fx-pref-height: 50;");

    startButton.setOnAction(e -> {
      stage.setScene(mainScene);
    });

    splashScreenBox.getChildren().addAll(title, instructions, startButton);
  }


  /**
   * Gets this scene
   *
   * @return this.scene
   */
  public Scene getScene() {
    return this.scene;
  }


}
