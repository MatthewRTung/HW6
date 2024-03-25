package cs3500.planner;

import cs3500.planner.model.Schedule;
import cs3500.planner.view.EventFrame;
import cs3500.planner.model.ReadOnlyScheduleModel;

public class PlannerRunner {
  public static void main(String[] args) {
    ReadOnlyScheduleModel cs = new Schedule();
    EventFrame eventFrame = new EventFrame(cs);
    eventFrame.setVisible(true);
  }
}
