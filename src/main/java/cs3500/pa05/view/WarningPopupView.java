package cs3500.pa05.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * class for displaying commitment warning popups
 */
public class WarningPopupView {
  private Popup warningPopup;
  private final Stage stage;

  /**
   * general constructor for warning popups
   *
   * @param stage the host stage
   */
  public WarningPopupView(Stage stage) {
    this.stage = stage;
  }

  /**
   * Creates a warning popup with given messsage
   *
   * @param msg warning message
   */
  public void initWarningPopup(String msg) {
    warningPopup = new Popup();
    VBox warningPane = new VBox();
    warningPane.setPrefSize(300, 300);
    warningPane.setStyle("-fx-background-color: white; -fx-border-color: black;");
    warningPane.setAlignment(Pos.CENTER);

    Label warningMsg = new Label("WARNING!");
    warningMsg.setPrefSize(300, 100);
    warningMsg.setStyle("-fx-font-size: 40px; -fx-text-fill: red;");
    warningMsg.setAlignment(Pos.CENTER);

    Label message = new Label(msg);
    message.setPrefSize(300, 200);
    message.setStyle("-fx-font-size: 18px;");
    message.setAlignment(Pos.CENTER);
    warningPane.getChildren().addAll(warningMsg, message);

    warningPopup.getContent().addAll(warningPane);


    Button closeButton = new Button(" X ");
    closeButton.setOnAction(e -> warningPopup.hide());
    warningPopup.getContent().add(closeButton);

    warningPopup.show(this.stage);
  }

}
