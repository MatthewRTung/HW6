package cs3500.planner;

import cs3500.planner.model.CentralSystem;
import cs3500.planner.model.ReadOnlyCentralSystemModel;
import cs3500.planner.model.Schedule;
import cs3500.planner.view.CentralSystemFrame;
import cs3500.planner.view.EventFrame;
import cs3500.planner.model.ReadOnlyScheduleModel;

public class PlannerRunner {
  public static void main(String[] args) {
    //For the event frame
    //ReadOnlyScheduleModel cs = new Schedule();
    //EventFrame eventFrame = new EventFrame(cs);
    //eventFrame.setVisible(true);
    CentralSystem model = new CentralSystem();
    CentralSystemFrame frame = new CentralSystemFrame(model);
    frame.setVisible(true);

  }
}
