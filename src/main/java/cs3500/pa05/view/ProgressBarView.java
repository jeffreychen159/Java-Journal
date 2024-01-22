package cs3500.pa05.view;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

/**
 * class for displaying and editing the progress bar
 */
public class ProgressBarView {
  @FXML
  private ProgressBar progressBar;


  /**
   * general constructor for progress bar views
   *
   * @param progressBar the progress bar
   */
  public ProgressBarView(ProgressBar progressBar) {
    this.progressBar = progressBar;
  }

  /**
   * modifies the progress bars fill value
   *
   * @param value amount of the progress bar to fill
   */
  public void setProgressBar(double value) {
    progressBar.setProgress(value);
  }
}
