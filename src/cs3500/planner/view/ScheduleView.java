package cs3500.planner.view;

import java.util.List;

import cs3500.planner.model.Event;

/**
 * Interface for the ScheduleView used to display the schedule.
 */
public interface ScheduleView {

  /**
   * Method used to display the schedule for different users.
   * @param events the list of events to be displayed
   */
  void displaySchedule(List<Event> events);
}
