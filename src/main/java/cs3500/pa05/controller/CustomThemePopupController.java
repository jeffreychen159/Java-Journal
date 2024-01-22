package cs3500.pa05.controller;

import cs3500.pa05.view.BackgroundView;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represents a custom theme class
 */
public class CustomThemePopupController {
  private final Popup customThemePopup;
  private final VBox customViewPane;
  private final Stage stage;
  private final BackgroundView backgroundView;

  /**
   * Instantiates a Custom Theme Popup
   *
   * @param stage          stage of the scene
   * @param backgroundView object to change the background
   */
  public CustomThemePopupController(Stage stage, BackgroundView backgroundView) {
    this.stage = stage;
    this.backgroundView = backgroundView;
    customThemePopup = new Popup();
    customViewPane = new VBox();
  }

  /**
   * Initializes a theme popup
   */
  public void initCustomThemePopup() {
    customViewPane.setPrefSize(300, 400);
    customViewPane.setStyle("-fx-background-color: white;"
        + "-fx-border-color: black;"
        + "-fx-alignment: top-center");

    Label title = new Label("Custom Theme Picker");
    title.setStyle("-fx-padding: 2;"
        + "-fx-alignment: center;"
        + "-fx-font: 24px bold;"
        + "-fx-pref-width: 300;");

    Label titleColor = new Label("Title Color");
    titleColor.setStyle("-fx-border-insets: 20, 0, 0, 0;"
        + "-fx-pref-width: 300;"
        + "-fx-alignment: center;");

    ColorPicker titleColorPicker = new ColorPicker();
    titleColorPicker.setStyle("-fx-border-insets: 0, 100, 0, 100;"
        + "-fx-pref-width: 300;"
        + "-fx-pref-height: 30");

    titleColorPicker.setOnAction(event -> setTitleColorValue(titleColorPicker.getValue()));

    Label weekdayColor = new Label("Weekday Color");
    weekdayColor.setStyle("-fx-border-insets: 20, 0, 0, 0;"
        + "-fx-pref-width: 300;"
        + "-fx-alignment: center;");

    ColorPicker weekdayColorPicker = new ColorPicker();
    weekdayColorPicker.setStyle("-fx-border-insets: 0, 100, 0, 100;"
        + "-fx-pref-width: 300;"
        + "-fx-pref-height: 30");

    weekdayColorPicker.setOnAction(event -> setWeekdayColorValue(weekdayColorPicker.getValue()));

    Label weekpaneColor = new Label("Weekday Color");
    weekpaneColor.setStyle("-fx-border-insets: 20, 0, 0, 0;"
        + "-fx-pref-width: 300;"
        + "-fx-alignment: center;");

    ColorPicker weekpaneColorPicker = new ColorPicker();
    weekpaneColorPicker.setStyle("-fx-border-insets: 0, 100, 0, 100;"
        + "-fx-pref-width: 300;"
        + "-fx-pref-height: 30");

    weekpaneColorPicker.setOnAction(event -> setWeekpaneColorValue(weekpaneColorPicker.getValue()));

    List<String> allFonts = Font.getFontNames();

    Label fontPicker = new Label("Font Style");
    fontPicker.setStyle("-fx-border-insets: 0, 100, 0, 100;"
        + "-fx-pref-width: 300;"
        + "-fx-alignment: center;");

    ComboBox<String> fontFamily = new ComboBox<>();

    fontFamily.getItems().addAll(allFonts);
    fontFamily.setStyle("-fx-border-insets: 0, 100, 0, 100;"
        + "-fx-pref-width: 300;"
        + "-fx-pref-height: 30");

    fontFamily.setOnAction(event -> setFont(fontFamily.getValue().toString()));

    Label eventColor = new Label("Event Item Color");
    eventColor.setStyle("-fx-border-insets: 0, 100, 0, 100;"
        + "-fx-pref-width: 300;"
        + "-fx-alignment: center;");

    ColorPicker eventColorPicker = new ColorPicker();
    eventColorPicker.setStyle("-fx-border-insets: 0, 100, 0, 100;"
        + "-fx-pref-width: 300;"
        + "-fx-pref-height: 30");

    eventColorPicker.setOnAction(event -> setEventColor(eventColorPicker.getValue()));


    customViewPane.getChildren()
        .addAll(title, titleColor, titleColorPicker, weekdayColor, weekdayColorPicker,
            weekpaneColor, weekpaneColorPicker, fontPicker, fontFamily, eventColor,
            eventColorPicker);

    customThemePopup.getContent().add(customViewPane);

    Button closeButton = new Button(" X ");
    closeButton.setOnAction(e -> customThemePopup.hide());
    customThemePopup.getContent().add(closeButton);

    customThemePopup.show(this.stage);
  }

  /**
   * sets the title color to the chosen color
   *
   * @param color user picked color
   */
  public void setTitleColorValue(Color color) {
    backgroundView.setCustomTitleColor(color);
  }

  /**
   * sets the weekdays to the chosen color
   *
   * @param color chosen color
   */
  public void setWeekdayColorValue(Color color) {
    backgroundView.setCustomWeekdayColor(color);
  }

  /**
   * sets the weekpane's color
   *
   * @param color color to set
   */
  public void setWeekpaneColorValue(Color color) {
    backgroundView.setCustomWeekpaneColor(color);
  }

  /**
   * sets the background view's font to the given font
   *
   * @param family given font family
   */
  public void setFont(String family) {
    backgroundView.setFontValue(family);
  }

  public void setEventColor(Color c) {
    backgroundView.setEventColor(c);
  }
}
