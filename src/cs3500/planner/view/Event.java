package cs3500.planner.view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Event extends JFrame implements EventView {
  private JTextField eventNameField;
  private JTextField eventLocationField;

  public Event() {
    super("Event Details");
    initializeComponents();
  }

  @Override
  public void setEventDetails(cs3500.planner.model.Event event) {

  }

  @Override
  public void clearForm() {

  }

  @Override
  public void displayError(String message) {

  }

  private void initializeComponents() {
    this.setLayout(new GridLayout(0, 2));
    eventNameField = new JTextField();
    this.add(new JLabel("Event Name:"));
    this.add(eventNameField);

    eventLocationField = new JTextField();
    this.add(new JLabel("Location:"));
    this.add(eventLocationField);
    this.pack();
    this.setVisible(true);
  }
}
