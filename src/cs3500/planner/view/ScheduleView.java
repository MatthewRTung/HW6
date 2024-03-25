package cs3500.planner.view;

import java.util.List;

import cs3500.planner.model.Event;

public interface ScheduleView {
  void displaySchedule(List<Event> events);
}
