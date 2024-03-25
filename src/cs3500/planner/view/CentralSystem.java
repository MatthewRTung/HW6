package cs3500.planner.view;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class CentralSystem extends JFrame implements CentralSystemView {
  private final JPanel schedulePanel;

  public CentralSystem() {
    super("Planner Central System");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setSize(800, 600);
    schedulePanel = new JPanel();
    this.add(schedulePanel, BorderLayout.CENTER);
    this.setVisible(true);
  }

  @Override
  public void updateView() {

  }

  @Override
  public void displayError(String message) {

  }
}
