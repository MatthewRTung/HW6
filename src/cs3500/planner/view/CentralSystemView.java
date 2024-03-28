package cs3500.planner.view;

/**
 * Interface for the CentralSystemFrame that creates the GUI for the NUPlanner.
 */
public interface CentralSystemView {

  /**
   * Method that updates the view of the CentralSystemFrame.
   */
  void updateView();

  /**
   * Method that displays an error message in the CentralSystemFrame.
   * @param message the error message to be displayed
   */
  void displayError(String message);
}
