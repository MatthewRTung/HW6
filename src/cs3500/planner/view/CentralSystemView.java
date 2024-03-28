package cs3500.planner.view;

/**
 * The CentralSystemView interface defines the essential methods that any view class
 * should implement to interact with the central system. It serves as a contract
 * for updating the view and displaying errors.
 */
public interface CentralSystemView {

  /**
   * Updates the view to reflect any changes in the model or the state of the application.
   * This method is typically called when the data displayed in the view needs to be refreshed,
   * such as after a change in the underlying model or a user action.
   */
  void updateView();

  /**
   * Displays an error message to the user. This method is used to communicate error
   * conditions or exceptional situations to the user in a consistent manner.
   *
   * @param message The error message to be displayed. This message should be clear
   *                and informative so that the user understands the nature of the error.
   */
  void displayError(String message);
}
