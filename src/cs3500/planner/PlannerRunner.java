package cs3500.planner;

import cs3500.planner.model.CentralSystem;

import cs3500.planner.view.CentralSystemFrame;

/**
 * Class that contains the main method for running the program.
 */
public class PlannerRunner {

  /**
   * Constructs the NU Planner system and runs it.
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    CentralSystem model = new CentralSystem();
    CentralSystemFrame frame = new CentralSystemFrame(model);
    frame.setVisible(true);

  }
}