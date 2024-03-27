package cs3500.planner.view;

import cs3500.planner.model.Event;

public interface EventView {
  void setEventDetails(Event event);
//
//  void clearForm();

  void displayError(String message);
}