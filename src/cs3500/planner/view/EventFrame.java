package cs3500.planner.view;

import java.time.format.DateTimeFormatter;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;

import cs3500.planner.model.CentralSystem;
import cs3500.planner.model.Event;


/**
 * EventFrame provides a graphical user interface for viewing and editing event details.
 * Users can modify the event name, location, timing, and other attributes.
 */

public class EventFrame extends JFrame implements EventView {
  private CentralSystem centralSystem;
  private CentralSystemFrame frame;

  private JTextField eventNameField;
  private JTextField eventLocationField;
  private JCheckBox isOnlineCheckbox;
  private JComboBox<String> startingDayComboBox;
  private JComboBox<String> endingDayComboBox;
  private JTextField startingTimeField;
  private JTextField endingTimeField;
  private JList<String> userList;
  private JButton removeButton;
  private JButton modifyButton;

  /**
   * Constructs an EventFrame with a reference to the central system model.
   * Initializes the UI components and sets up event handlers.
   *
   * @param centralSystem The central system model this frame interacts with.
   */

  public EventFrame(CentralSystem centralSystem) {
    super("Event Planner");
    this.centralSystem = centralSystem;
    removeButton = new JButton("Remove Event");
    modifyButton = new JButton("Modify Event");
    modifyButton.addActionListener(e -> modifyEvent());
    removeButton.addActionListener(e -> removeEvent());
    initializeComponents();
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.pack();
    this.setVisible(true);
  }

  // Initializes the components used in the frame.
  private void initializeComponents() {
    this.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
    //Event Name
    eventNameField = new JTextField();
    constraints.gridx = 0;
    constraints.gridy = 0;
    this.add(new JLabel("Event Name:"), constraints);
    constraints.gridx = 1;
    constraints.gridwidth = 1;
    this.add(eventNameField, constraints);
    //Location Label
    eventLocationField = new JTextField();
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    this.add(new JLabel("Location:"), constraints);
    constraints.gridx = 1;
    constraints.gridwidth = 1;
    this.add(eventLocationField, constraints);
    //Online Checkbox
    isOnlineCheckbox = new JCheckBox("Is Online");
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 4; // Span two columns
    this.add(isOnlineCheckbox, constraints);
    //Starting Day ComboBox
    String[] daysOfWeek = {
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    startingDayComboBox = new JComboBox<>(daysOfWeek);
    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.gridwidth = 1;
    this.add(new JLabel("Starting Day:"), constraints);
    constraints.gridx = 0;
    constraints.gridy = 4;
    this.add(startingDayComboBox, constraints);
    //Starting Time Field
    startingTimeField = new JTextField();
    constraints.gridx = 0;
    constraints.gridy = 5;
    this.add(new JLabel("Starting Time:"), constraints);
    constraints.gridy = 6;
    this.add(startingTimeField, constraints);
    //Ending Day ComboBox
    endingDayComboBox = new JComboBox<>(daysOfWeek);
    constraints.gridx = 1;
    constraints.gridy = 3;
    this.add(new JLabel("Ending Day:"), constraints);
    constraints.gridx = 4;
    this.add(endingDayComboBox, constraints);
    constraints.gridx = 1;
    constraints.gridy = 4;
    this.add(endingDayComboBox, constraints);
    //Ending Time Field
    endingTimeField = new JTextField();
    constraints.gridx = 1;
    constraints.gridy = 5;
    this.add(new JLabel("Ending Time:"), constraints);
    constraints.gridy = 6;
    this.add(endingTimeField, constraints);
    //Users List
    userList = new JList<>(new DefaultListModel<>());
    userList.setVisibleRowCount(4);
    JScrollPane userListScrollPane = new JScrollPane(userList);
    constraints.gridx = 0;
    constraints.gridy = 8;
    constraints.gridwidth = 3; // Span four columns for list
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1.0;
    constraints.weighty = 1.0;
    this.add(userListScrollPane, constraints);
    constraints.gridx = 0;
    constraints.gridy = 7;
    constraints.gridwidth = 2;
    this.add(new JLabel("Available Users:"), constraints);
    //Reset
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.weighty = 0;
    //Modify Button
    constraints.gridx = 1;
    constraints.gridy = 9;
    constraints.gridwidth = 1;
    this.add(modifyButton, constraints);
    //Remove Button
    constraints.gridx = 0;
    constraints.gridy = 9;
    constraints.gridwidth = 1;
    this.add(removeButton, constraints);
  }

  // Initializes the field for entering the event name.
  private void initializeEventName(GridBagConstraints constraints) {
    //Event Name
    eventNameField = new JTextField();
    constraints.gridx = 0;
    constraints.gridy = 0;
    this.add(new JLabel("Event Name:"), constraints);
    constraints.gridx = 1;
    constraints.gridwidth = 1;
    this.add(eventNameField, constraints);
  }

  //Initializes the field for entering the event location.
  private void initializeEventLocation(GridBagConstraints constraints) {
    //Locati on Label
    eventLocationField = new JTextField();
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    this.add(new JLabel("Location:"), constraints);
    constraints.gridx = 1;
    constraints.gridwidth = 1;
    this.add(eventLocationField, constraints);
  }

  // Initializes the checkbox for specifying whether the event is online.
  private void initializeOnlineCheckbox(GridBagConstraints constraints) {
    //Online Checkbox
    isOnlineCheckbox = new JCheckBox("Is Online");
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 4; // Span two columns
    this.add(isOnlineCheckbox, constraints);
  }

  //Initializes the combo box for selecting the starting day of the event.

  private void initializeStartingDay(GridBagConstraints constraints) {
    //Starting Day ComboBox
    String[] daysOfWeek = {
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    startingDayComboBox = new JComboBox<>(daysOfWeek);
    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.gridwidth = 1;
    this.add(new JLabel("Starting Day:"), constraints);
    constraints.gridx = 0;
    constraints.gridy = 4;
    this.add(startingDayComboBox, constraints);
  }

  // Initializes the field for entering the starting time of the event.
  private void initializeStartingTime(GridBagConstraints constraints) {
    //Starting Time Field
    startingTimeField = new JTextField();
    constraints.gridx = 0;
    constraints.gridy = 5;
    this.add(new JLabel("Starting Time:"), constraints);
    constraints.gridy = 6;
    this.add(startingTimeField, constraints);
  }

  // Initializes the combo box for selecting the ending day of the event.
  private void initializeEndingDay(GridBagConstraints constraints, String[] daysOfWeek) {
    //Ending Day ComboBox
    endingDayComboBox = new JComboBox<>(daysOfWeek);
    constraints.gridx = 1;
    constraints.gridy = 3;
    this.add(new JLabel("Ending Day:"), constraints);
    constraints.gridx = 4;
    this.add(endingDayComboBox, constraints);
    constraints.gridx = 1;
    constraints.gridy = 4;
    this.add(endingDayComboBox, constraints);
  }

  // Initializes the field for entering the ending time of the event.
  private void initializeEndingTime(GridBagConstraints constraints) {
    //Ending Time Field
    endingTimeField = new JTextField();
    constraints.gridx = 1;
    constraints.gridy = 5;
    this.add(new JLabel("Ending Time:"), constraints);
    constraints.gridy = 6;
    this.add(endingTimeField, constraints);
  }

  // Initializes the list for displaying and selecting users.
  private void initializeUserList(GridBagConstraints constraints) {
    //Users List
    userList = new JList<>(new DefaultListModel<>());
    userList.setVisibleRowCount(4);
    JScrollPane userListScrollPane = new JScrollPane(userList);
    constraints.gridx = 0;
    constraints.gridy = 8;
    constraints.gridwidth = 3; // Span four columns for list
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1.0;
    constraints.weighty = 1.0;
    this.add(userListScrollPane, constraints);
    constraints.gridx = 0;
    constraints.gridy = 7;
    constraints.gridwidth = 2;
    this.add(new JLabel("Available Users:"), constraints);
  }

  // Initializes the reset button and its action.
  private void initializeReset(GridBagConstraints constraints) {
    //Reset
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.weighty = 0;
  }

  // Initializes the modify button and its action.
  private void initializeModifyButton(GridBagConstraints constraints) {
    //Modify Button
    constraints.gridx = 1;
    constraints.gridy = 9;
    constraints.gridwidth = 1;
    this.add(modifyButton, constraints);
  }

  // Initializes the remove button and its action.
  private void initializeRemoveButton(GridBagConstraints constraints) {
    //Remove Button
    constraints.gridx = 0;
    constraints.gridy = 9;
    constraints.gridwidth = 1;
    this.add(removeButton, constraints);
  }

  // Modifies the event based on the input provided in the frame.
  private void modifyEvent() {
    if (validateInput()) {
      String eventDetails = getEventDetails();
      System.out.println("Create event: " + eventDetails);
    } else {
      displayError("Error: Missing event information for modification.");
    }
  }

  // Removes the event based on the input provided in the frame.
  private void removeEvent() {
    if (validateInput()) {
      String eventDetails = getEventDetails();
      System.out.println("Remove event: " + eventDetails);
    } else {
      displayError("Error: Missing event information for removal.");
    }
  }

  // Validates the input fields before event modification/removal.

  private boolean validateInput() {
    return !eventNameField.getText().trim().isEmpty() &&
            !startingTimeField.getText().trim().isEmpty() &&
            !endingTimeField.getText().trim().isEmpty();
  }

  // Constructs a string representation of the event details based on the input fields.

  private String getEventDetails() {
    String result = "";
    result += eventNameField.getText().trim() + " ";
    result += eventLocationField.getText().trim() + " ";
    result += isOnlineCheckbox.isSelected() + " ";
    result += startingDayComboBox.getSelectedItem() + " ";
    result += startingTimeField.getText().trim() + " ";
    result += endingDayComboBox.getSelectedItem() + " ";
    result += endingTimeField.getText().trim();
    return result;
  }

  /**
   * Sets the event details in the input fields of the frame.
   *
   * @param event The event whose details are to be displayed in the frame.
   */
  @Override
  public void setEventDetails(Event event) {
    eventNameField.setText(event.getName());
    eventLocationField.setText(event.getLocation());
    isOnlineCheckbox.setSelected(event.isOnline());
    startingDayComboBox.setSelectedItem(event.getStartTime().getDayOfWeek().toString());
    startingTimeField.setText(event.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")));
    endingDayComboBox.setSelectedItem(event.getEndTime().getDayOfWeek().toString());
    endingTimeField.setText(event.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")));

    // Populate the users list
    DefaultListModel<String> listModel = new DefaultListModel<>();
    for (String user : event.getInvitees()) {
      listModel.addElement(user);
    }
    userList.setModel(listModel);
    userList.setSelectedValue(event.getHostId(), true);
  }

  /**
   * Displays the error.
   * @param message the error message.
   */
  @Override
  public void displayError(String message) {
    JOptionPane.showMessageDialog(this, message,
            "Error", JOptionPane.ERROR_MESSAGE);
  }

}