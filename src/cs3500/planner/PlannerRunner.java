package cs3500.planner;

import cs3500.planner.model.CentralSystem;

import cs3500.planner.view.CentralSystemFrame;

public class PlannerRunner {
  public static void main(String[] args) {
    CentralSystem model = new CentralSystem();
    CentralSystemFrame frame = new CentralSystemFrame(model);
    frame.setVisible(true);

  }
}
