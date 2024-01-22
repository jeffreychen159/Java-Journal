package cs3500.pa05;


import cs3500.pa05.controller.BujoController;
import cs3500.pa05.controller.SplashScreenController;
import cs3500.pa05.view.BujoView;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * entry point for Bujo app
 */
public class Driver extends Application {

  /**
   * entry point for Bujo app
   *
   * @param args CLI arguments
   */
  public static void main(String[] args) {
    launch(); //test
  }


  /**
   * The main entry point for all JavaFX applications.
   * The start method is called after the init method has returned,
   * and after the system is ready for the application to begin running.
   *
   * <p>
   * NOTE: This method is called on the JavaFX Application Thread.
   * </p>
   *
   * @param stage the primary stage for this application, onto which
   *                     the application scene can be set.
   *                     Applications may create other stages, if needed, but they will not be
   *                     primary stages.
   */
  @Override
  public void start(Stage stage) {
    HostServices services = getHostServices();
    BujoController controller = new BujoController(stage, services);

    BujoView view = new BujoView(controller);

    Scene mainScene = view.load();
    SplashScreenController splashScreenController = new SplashScreenController(stage, mainScene);

    try {
      splashScreenController.initSplashScreen();
      Scene splashScreen = splashScreenController.getScene();
      stage.setScene(splashScreen);
      controller.run();
      stage.show();
    } catch (IllegalStateException | IOException e) {
      System.out.println(e);
      System.err.println("Unable to load GUI");
    }

    stage.setTitle("Bujo Application");
  }
}
