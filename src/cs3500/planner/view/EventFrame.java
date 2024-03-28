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
<<<<<<< HEAD
 * The GUI that creates the event frame for users to input event details.
=======
 * EventFrame provides a graphical user interface for viewing and editing event details.
 * Users can modify the event name, location, timing, and other attributes.
>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
 */
public class EventFrame extends JFrame implements EventView {
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
<<<<<<< HEAD
   * Constructor for EventFrame to initialize the fields for the class.
   * @param centralSystem the central system model
=======
   * Constructs an EventFrame with a reference to the central system model.
   * Initializes the UI components and sets up event handlers.
   *
   * @param centralSystem The central system model this frame interacts with.
>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
   */
  public EventFrame(CentralSystem centralSystem) {
    super("Event Planner");
    removeButton = new JButton("Remove Event");
    modifyButton = new JButton("Modify Event");
    modifyButton.addActionListener(e -> modifyEvent());
    removeButton.addActionListener(e -> removeEvent());
    initializeComponents();
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.pack();
    this.setVisible(true);
  }

<<<<<<< HEAD
  //helper method to initialize the components
=======
  // Initializes the components used in the frame.
>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
  private void initializeComponents() {
    this.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(5, 5, 5, 5);
<<<<<<< HEAD
    initializeEventName(constraints);
    initializeEventLocation(constraints);
    initializeOnlineCheckbox(constraints);
    initializeStartingTime(constraints);
    initializeStartingDay(constraints);
    initializeEndingTime(constraints);
    initializeEndingDay(constraints);
    initializeUserList(constraints);
    initializeRemoveButton(constraints);
    initializeModifyButton(constraints);
    initializeReset(constraints);
  }

  //helper method to add/modify an event in the system(not implemented yet)
=======
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
    constraints.gridwidth = 3;
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

<<<<<<< HEAD
  // Initializes the field for entering the event name.
=======
<<<<<<< HEAD
  //helper method to add/modify an event in the system(not implemented yet)
=======
>>>>>>> 0087827707e68fffb845bfa316eac8043a96aae5
>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
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

<<<<<<< HEAD
  //helper method to create the event location button
=======
  //Initializes the field for entering the event location.
>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
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

<<<<<<< HEAD
  //helper method to create the is online checkbox
=======
  // Initializes the checkbox for specifying whether the event is online.
>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
  private void initializeOnlineCheckbox(GridBagConstraints constraints) {
    //Online Checkbox
    isOnlineCheckbox = new JCheckBox("Is Online");
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 4; // Span two columns
    this.add(isOnlineCheckbox, constraints);
  }

<<<<<<< HEAD
  //helper method to create the starting day box
=======
  //Initializes the combo box for selecting the starting day of the event.

>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
  private void initializeStartingDay(GridBagConstraints constraints) {
    //Starting Day ComboBox
    String[] daysOfWeek = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    startingDayComboBox = new JComboBox<>(daysOfWeek);
    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.gridwidth = 1;
    this.add(new JLabel("Starting Day:"), constraints);
    constraints.gridx = 0;
    constraints.gridy = 4;
    this.add(startingDayComboBox, constraints);
  }

<<<<<<< HEAD
  //helper method to create the starting time box
=======
  // Initializes the field for entering the starting time of the event.
>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
  private void initializeStartingTime(GridBagConstraints constraints) {
    //Starting Time Field
    startingTimeField = new JTextField();
    constraints.gridx = 0;
    constraints.gridy = 5;
    this.add(new JLabel("Starting Time:"), constraints);
    constraints.gridy = 6;
    this.add(startingTimeField, constraints);
  }

<<<<<<< HEAD
  //helper method to create the end day box
  private void initializeEndingDay(GridBagConstraints constraints) {
=======
  // Initializes the combo box for selecting the ending day of the event.
  private void initializeEndingDay(GridBagConstraints constraints, String[] daysOfWeek) {
>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
    //Ending Day ComboBox
    String[] daysOfWeek = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
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

<<<<<<< HEAD
  //helper method to create the end time box
=======
  // Initializes the field for entering the ending time of the event.
>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
  private void initializeEndingTime(GridBagConstraints constraints) {
    //Ending Time Field
    endingTimeField = new JTextField();
    constraints.gridx = 1;
    constraints.gridy = 5;
    this.add(new JLabel("Ending Time:"), constraints);
    constraints.gridy = 6;
    this.add(endingTimeField, constraints);
  }

<<<<<<< HEAD
  //helper method to create the list of invited useres
=======
  // Initializes the list for displaying and selecting users.
>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
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

<<<<<<< HEAD
  //helper method to reset the frame
=======
  // Initializes the reset button and its action.
>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
  private void initializeReset(GridBagConstraints constraints) {
    //Reset
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.weighty = 0;
  }

<<<<<<< HEAD
  //helper method to add/modify button
=======
  // Initializes the modify button and its action.
>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
  private void initializeModifyButton(GridBagConstraints constraints) {
    //Modify Button
    constraints.gridx = 1;
    constraints.gridy = 9;
    constraints.gridwidth = 1;
    this.add(modifyButton, constraints);
  }

<<<<<<< HEAD
  //helper method to remove button
=======
  // Initializes the remove button and its action.
>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
  private void initializeRemoveButton(GridBagConstraints constraints) {
    //Remove Button
    constraints.gridx = 0;
    constraints.gridy = 9;
    constraints.gridwidth = 1;
    this.add(removeButton, constraints);
  }

<<<<<<< HEAD
  //helper method to add/modify an event to the system(not implemented yet)
=======
<<<<<<< HEAD
  // Modifies the event based on the input provided in the frame.
=======
  //need to implement this
>>>>>>> bc7cd44b9ed190dbe88ac4c37a47fe3c127e1086
>>>>>>> 0087827707e68fffb845bfa316eac8043a96aae5
>>>>>>> 5dcc095b6bb7ce2ee355f4fd70988f8bd94280ef
  private void modifyEvent() {
    if (validateInput()) {
      String eventDetails = getEventDetails();
      System.out.println("Create event: " + eventDetails);
    } else {
      displayError("Error: Missing event information for modification.");
    }
  }

<<<<<<< HEAD
  // Removes the event based on the input provided in the frame.
=======
  //helper to remove an event from the system(not implemented yet)
>>>>>>> 0087827707e68fffb845bfa316eac8043a96aae5
  private void removeEvent() {
    if (validateInput()) {
      String eventDetails = getEventDetails();
      System.out.println("Remove event: " + eventDetails);
    } else {
      displayError("Error: Missing event information for removal.");
    }
  }

<<<<<<< HEAD
  // Validates the input fields before event modification/removal.

=======
  //helper method to ensure that we have valid inputs
>>>>>>> 0087827707e68fffb845bfa316eac8043a96aae5
  private boolean validateInput() {
    return !eventNameField.getText().trim().isEmpty() &&
            !startingTimeField.getText().trim().isEmpty() &&
            !endingTimeField.getText().trim().isEmpty();
  }

<<<<<<< HEAD
  // Constructs a string representation of the event details based on the input fields.

=======
  //helper method to get the event details
>>>>>>> 0087827707e68fffb845bfa316eac8043a96aae5
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
<<<<<<< HEAD
=======

>>>>>>> 0087827707e68fffb845bfa316eac8043a96aae5

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