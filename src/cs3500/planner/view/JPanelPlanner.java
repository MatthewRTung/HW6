package cs3500.planner.view;

import javax.swing.*;

import cs3500.planner.model.ReadOnlyScheduleModel;

public class JPanelPlanner extends JPanel {
  /**
   * Our view will need to display a model, so it needs to get the current sequence from the model.
   */
  private final ReadOnlyScheduleModel model;

  public JPanelPlanner(ReadOnlyScheduleModel model) {
    this.model = model;
  }


}
