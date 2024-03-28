package cs3500.planner.view;

import cs3500.planner.model.Event;

/**
<<<<<<< HEAD
 * Interface for the EventView to create the event frame to create events for users.
 */
public interface EventView {

  /**
   * Method used to set the details of the event for each event.
   * @param event The event to set the details of.
   */
  void setEventDetails(Event event);


  /**
   * Method used to display if there is an error with inputting event details.
   * @param message The error message to display.
=======
 * The EventView interface defines the essential methods that any event view class
 * should implement to display and interact with event details in the planner application.
 * It serves as a contract for setting event details and displaying error messages.
 */
public interface EventView {
<<<<<<< HEAD
=======
  void setEventDetails(Event event);
>>>>>>> 0087827707e68fffb845bfa316eac8043a96aae5

  /**
   * Sets the details of the event to be displayed in the view. This method is responsible
   * for populating the view's fields with the event's data, allowing users to see and
   * possibly edit the event's details.
   *
   * @param event The event whose details are to be displayed in the view.
   */
  void setEventDetails(Event event);

  /**
   * Displays an error message to the user. This method is used to communicate error
   * conditions or exceptional situations to the user in a consistent manner, typically
   * related to the event details being viewed or edited.
   *
   * @param message The error message to be displayed. This message should be clear
   *                and informative, helping the user understand what went wrong.
>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
   */
  void displayError(String message);
}