package cs3500.planner.view;

import javax.swing.*;

import cs3500.planner.model.ReadonlyScheduleModel;

public class JPanelPlanner extends JPanel {
  /**
   * Our view will need to display a model, so it needs to get the current sequence from the model.
   */
  private final ReadonlyScheduleModel model;

  public JPanelPlanner(ReadonlyScheduleModel model) {
    this.model = model;
  }


}
