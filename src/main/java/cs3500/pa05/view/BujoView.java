package cs3500.pa05.view;

import cs3500.pa05.controller.BujoController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Handles displaying Bujo
 */
public class BujoView {
  private final FXMLLoader loader;

  /**
   * general constructor for bujo view
   *
   * @param controller the controller class for this view
   */
  public BujoView(BujoController controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("bujo.fxml"));
    this.loader.setController(controller);
  }

  /**
   * Loads a scene from a Bujo GUI layout.
   *
   * @return the layout
   * @throws IllegalStateException if layout cannot be loaded
   */
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to load layout");
    }
  }
}
