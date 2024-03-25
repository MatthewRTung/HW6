package cs3500.planner.view;

import java.awt.GridLayout;

import javax.swing.*;

import cs3500.planner.model.ReadOnlyScheduleModel;

public class EventFrame extends JFrame implements EventView {
  private ReadOnlyScheduleModel readonlyModel;

  private JTextField eventNameField;
  private JTextField eventLocationField;
  private JCheckBox isOnlineCheckbox;
  private JComboBox<String> startingDayComboBox;
  private JComboBox<String> endingDayComboBox;
  private JTextField startingTimeField;
  private JTextField endingTimeField;
  private JList<String> userList;
  private JButton createButton;
  private JButton removeButton;
  private JButton modifyButton;

  public EventFrame(ReadOnlyScheduleModel readonlyModel) {
    super("Event Management");
    this.readonlyModel = readonlyModel;
    initializeComponents();
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.pack();
    this.setVisible(true);
  }

  private void initializeComponents() {
    this.setLayout(new GridLayout(0, 2));

    eventNameField = new JTextField();
    this.add(new JLabel("Event Name:"));
    this.add(eventNameField);

    eventLocationField = new JTextField();
    this.add(new JLabel("Location:"));
    this.add(eventLocationField);

    isOnlineCheckbox = new JCheckBox("Is online");
    this.add(isOnlineCheckbox);

    String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    startingDayComboBox = new JComboBox<>(daysOfWeek);
    this.add(new JLabel("Starting Day:"));
    this.add(startingDayComboBox);

    endingDayComboBox = new JComboBox<>(daysOfWeek);
    this.add(new JLabel("Ending Day:"));
    this.add(endingDayComboBox);

    startingTimeField = new JTextField();
    this.add(new JLabel("Starting Time:"));
    this.add(startingTimeField);

    endingTimeField = new JTextField();
    this.add(new JLabel("Ending Time:"));
    this.add(endingTimeField);

    userList = new JList<>();
    userList.setModel(new DefaultListModel<>()); // This should be populated with user data from the model
    this.add(new JLabel("Available users"));
    this.add(new JScrollPane(userList));

    createButton = new JButton("Create Event");
    createButton.addActionListener(e -> createEvent());
    this.add(createButton);

    removeButton = new JButton("Remove Event");
    removeButton.addActionListener(e -> removeEvent());
    this.add(removeButton);

    modifyButton = new JButton("Modify Event");
    // Add action listener for modify if needed
    this.add(modifyButton);
  }

  private void createEvent() {
    if (validateInput()) {
      String eventDetails = getEventDetails();
      System.out.println("Create event: " + eventDetails);
    } else {
      displayError("Error: Missing event information.");
    }
  }

  private void removeEvent() {
    if (validateInput()) {
      String eventDetails = getEventDetails();
      System.out.println("Remove event: " + eventDetails);
    } else {
      displayError("Error: Missing event information for removal.");
    }
  }

  private boolean validateInput() {
    // Example validation: Check if the event name or times are empty
    return !eventNameField.getText().trim().isEmpty() &&
            !startingTimeField.getText().trim().isEmpty() &&
            !endingTimeField.getText().trim().isEmpty();
  }

  private String getEventDetails() {
    // Construct event details from user inputs
    String selectedUsers = String.join(", ", userList.getSelectedValuesList());
    return "Name: " + eventNameField.getText() +
            ", Location: " + eventLocationField.getText() +
            ", Is Online: " + isOnlineCheckbox.isSelected() +
            ", Starting Day: " + startingDayComboBox.getSelectedItem() +
            ", Ending Day: " + endingDayComboBox.getSelectedItem() +
            ", Starting Time: " + startingTimeField.getText() +
            ", Ending Time: " + endingTimeField.getText() +
            ", Users: " + selectedUsers;
  }

  @Override
  public void setEventDetails(cs3500.planner.model.Event event) {
    // Populate fields with event details if modifying an existing event
  }

  @Override
  public void clearForm() {
    // Clear all input fields and selections
  }

  @Override
  public void displayError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

}
