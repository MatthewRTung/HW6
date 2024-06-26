package cs3500.planner.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;

import java.awt.Rectangle;
import java.awt.Point;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cs3500.planner.model.CentralSystem;
import cs3500.planner.model.Event;
import cs3500.planner.xml.XMLConfigurator;

/**
 * CentralSystemFrame to create the central system gui grid that displays the schedule.
 */
public class CentralSystemFrame extends JFrame implements CentralSystemView {
  private JPanel schedulePanel;
  private JComboBox<String> userDropDown;
  private final List<Event> events;
  private final CentralSystem model;
  private final Map<Rectangle, Event> eventRectangles;
  private EventFrame currentFrame;
  private Point dragStart;
  private Point dragEnd;

  /**
   * Constructor for the CentralSystemFrame.
   * @param model the central system model
   */
  public CentralSystemFrame(CentralSystem model) {
    super("Planner Central System");
    this.model = model;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    events = new ArrayList<>();
    eventRectangles = new HashMap<>();
    currentFrame = null;
    dragStart = null;
    dragEnd = null;
    initializeMenu();
    initializeSchedulePanel();
    eventListener();
    initializeControlPanel();
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void repaint() {
    super.repaint();
    if (schedulePanel != null) {
      schedulePanel.repaint();
    }
  }

  @Override
  public void updateView() {
    //get the user and handle null users
    Object selectedItem = userDropDown.getSelectedItem();
    String selectedUser = (selectedItem != null) ? selectedItem.toString() : null;
    //remove action listener
    ActionListener[] listeners = userDropDown.getActionListeners();
    for (ActionListener listener : listeners) {
      userDropDown.removeActionListener(listener);
    }
    //update drop-down
    userDropDown.removeAllItems();
    userDropDown.addItem("<none>");
    for (String userName : model.getUserName()) {
      userDropDown.addItem(userName);
    }
    //restore action listener
    for (ActionListener listener : listeners) {
      userDropDown.addActionListener(listener);
    }
    //set the selected item if it exists
    if (selectedUser != null && Objects.equals(userDropDown.getItemCount(), selectedUser)) {
      userDropDown.setSelectedItem(selectedUser);
    }
    //load the schedule if its valid
    if (selectedUser != null && !"<none>".equals(selectedUser)) {
      loadUserSchedule(selectedUser);
    } else {
      events.clear();
      schedulePanel.repaint();
    }
  }

  @Override
  public void displayError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  //helper method to initialize the menu bar
  private void initializeMenu() {
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem loadMenuItem = new JMenuItem("Add calendar");
    loadMenuItem.addActionListener(this::loadXMLAction);
    JMenuItem saveMenuItem = new JMenuItem("Save calendars");
    saveMenuItem.addActionListener(this::saveSchedulesAction);
    fileMenu.add(loadMenuItem);
    fileMenu.add(saveMenuItem);
    menuBar.add(fileMenu);
    this.setJMenuBar(menuBar);
  }

  //helper method to initialize the schedule panel
  private void initializeSchedulePanel() {
    schedulePanel = new JPanel() {
      protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawGrid(graphics);
        drawEvents(graphics);
        drawSelection(graphics);
      }
    };
    schedulePanel.setPreferredSize(new Dimension(800, 600));
    this.add(schedulePanel, BorderLayout.CENTER);
  }

  //helper method to draw the grid
  private void drawGrid(Graphics graphics) {
    graphics.setColor(Color.LIGHT_GRAY);
    int rows = 24;
    int cols = 7;
    int cellWidth = schedulePanel.getWidth() / cols;
    int cellHeight = schedulePanel.getHeight() / rows;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        graphics.drawRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
      }
      if (i % 4 == 0) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, i * cellHeight - 1, schedulePanel.getWidth(), 2);
      }
    }
  }

  //helper method to draw the events
  private void drawEvents(Graphics graphics) {
    eventRectangles.clear();
    int cellWidth = schedulePanel.getWidth() / 7;
    int cellHeight = schedulePanel.getHeight() / 24;
    graphics.setColor(Color.RED);
    for (Event event : events) {
      LocalDateTime startTime = event.getStartTime();
      LocalDateTime endTime = event.getEndTime();
      int dayCol = startTime.getDayOfWeek().getValue() % 7;
      int startHour = startTime.getHour();
      int endHour = endTime.getHour();
      int daySpan = endHour - startHour;
      if (daySpan < 0) {
        daySpan += 7;
      }
      for (int day = 0; day <= daySpan; day++) {
        int column = (dayCol + day) % 7;
        int startY = startHour * cellHeight;
        int endY;
        if (day < daySpan) {
          endY = 24 * cellHeight;
        } else {
          endY = endHour * cellHeight;
        }
        int rectHeight = endY - startY;
        graphics.fillRect(column * cellWidth, startY, cellWidth, rectHeight);
        Rectangle eventRect = new Rectangle(column * cellWidth, startY, cellWidth, rectHeight);
        eventRectangles.put(eventRect, event);
      }
    }
  }

  //helper method to create the bottom panel and buttons
  private void initializeControlPanel() {
    JPanel controlPanel = new JPanel(new GridLayout(1, 0, 5, 0));
    JButton loadButton = new JButton("Create event");
    JButton saveButton = new JButton("Schedule event");
    //takes list of user-names to pick from
    userDropDown = new JComboBox<>();
    userDropDown.addItem("<none>");
    for (String userName : model.getUserName()) {
      userDropDown.addItem(userName);
    }
    userDropDown.addActionListener(e -> {
      String selectedUser = Objects.requireNonNull(userDropDown.getSelectedItem()).toString();
      if (!"<none>".equals(selectedUser)) {
        loadUserSchedule(selectedUser);
      }
    });
    saveButton.addActionListener(e -> {
      EventFrame eventFrame = new EventFrame(model);
      eventFrame.setVisible(true);
    });
    loadButton.addActionListener(e -> {
      EventFrame eventFrame = new EventFrame(model);
      eventFrame.setVisible(true);
    });
    controlPanel.add(userDropDown);
    controlPanel.add(loadButton);
    controlPanel.add(saveButton);
    loadButton.setHorizontalAlignment(SwingConstants.CENTER);
    saveButton.setHorizontalAlignment(SwingConstants.CENTER);
    this.add(controlPanel, BorderLayout.SOUTH);
  }

  //helper method used to load the schedule from an XML file
  private void loadXMLAction(ActionEvent actionEvent) {
    JFileChooser fileChooser = new JFileChooser();
    int option = fileChooser.showOpenDialog(this);
    if (option == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      XMLConfigurator configurator = new XMLConfigurator();
      try {
        String userId = configurator.readScheduleUserId(selectedFile.getAbsolutePath());
        List<Event> events = configurator.readXMLFile(selectedFile.getAbsolutePath());
        if (!model.getUserName().contains(userId)) {
          model.addUser(userId);
          userDropDown.addItem(userId);
        }
        for (Event event : events) {
          model.createEvent(userId, event);
        }
        //refresh view
        userDropDown.setSelectedItem(userId);
        loadUserSchedule(userId);
        updateView();
      } catch (Exception ex) {
        //handle exceptions
        displayError("Failed to load the schedule from the XML file: " + ex.getMessage());
      }
    }
  }

  //helper method used to save the schedule to an XML file(doesn't do anything yet)
  private void loadUserSchedule(String userId) {
    events.clear();
    events.addAll(model.getEventsForUser(userId));
    schedulePanel.repaint();
  }

  //helper method used to save the schedule to an XML file(doesn't do anything yet)
  private void saveSchedulesAction(ActionEvent e) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Calendar");
    fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
    fileChooser.setApproveButtonText("Save");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML files (*.xml)", "xml"));

    int userSelection = fileChooser.showSaveDialog(this);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      if (!fileToSave.getName().toLowerCase().endsWith(".xml")) {
        fileToSave = new File(fileToSave + ".xml");
      }
      System.out.println("Save as file: " + fileToSave.getAbsolutePath());
    }
  }

  //helper method that adds a mouse listener to the schedule panel. if a user clicks
  //on an event, it will open the event details window
  private void eventListener() {
    schedulePanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent mouseEvent) {
        for (Map.Entry<Rectangle, Event> entry : eventRectangles.entrySet()) {
          if (entry.getKey().contains(mouseEvent.getPoint())) {
            Event event = entry.getValue();
            openEventDetails(event);
            break;
          }
        }
      }
    });
  }

  //helper method to
  private void openEventDetails(Event event) {
    //limits so only one frame can be opened and brings it to the front
    if (currentFrame != null && currentFrame.isVisible()) {
      currentFrame.dispose();
    }
    currentFrame = new EventFrame(model);
    currentFrame.setEventDetails(event);
    currentFrame.setVisible(true);
    currentFrame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent windowEvent) {
        currentFrame = null;
      }
    });
    //EventFrame eventFrame = new EventFrame(model);
    //eventFrame.setEventDetails(event);
    //eventFrame.setVisible(true);
  }

  //helper method to highlight board(not fully implemented yet)
  private void eventCreation() {
    schedulePanel.addMouseListener(new MouseAdapter() {

      @Override
      public void mousePressed(MouseEvent e) {
        dragStart = e.getPoint();
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        dragEnd = e.getPoint();
        createEventFromSelection(dragStart, dragEnd);
        dragStart = null;
        dragEnd = null;
      }
    });
    schedulePanel.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        dragEnd = e.getPoint();
        schedulePanel.repaint();
      }
    });
  }

  //helper method to draw the selected rectangle
  private void drawSelection(Graphics g) {
    if (dragStart != null && dragEnd != null) {
      g.setColor(Color.BLUE);
      int x = Math.min(dragStart.x, dragEnd.x);
      int y = Math.min(dragStart.y, dragEnd.y);
      int width = Math.abs(dragStart.x - dragEnd.x);
      int height = Math.abs(dragStart.y - dragEnd.y);
      g.drawRect(x, y, width, height);
    }
  }

  //helper method to create an event based on the user
  private void createEventFromSelection(Point start, Point end) {
    LocalDateTime startTime = pointToDateTime(start);
    LocalDateTime endTime = pointToDateTime(end);
    String eventName = JOptionPane.showInputDialog("Enter Event Name:");
    if (eventName != null && !eventName.isEmpty()) {
      Event newEvent = new Event(eventName, "Location", false, startTime, endTime, false, "Host1");
      model.createEvent("UserID", newEvent);
      events.add(newEvent);
      schedulePanel.repaint();
    }
  }

  //helper method to
  private LocalDateTime pointToDateTime(Point point) {
    int cellWidth = schedulePanel.getWidth() / 7;
    int cellHeight = schedulePanel.getHeight() / 24;
    int column = point.x / cellWidth; // Column where the user clicked
    int hour = point.y / cellHeight;

    // Adjust this to the day your grid starts with, e.g., if it starts with Monday:
    DayOfWeek startOfWeek = DayOfWeek.MONDAY;

    // Adjust the day based on the starting day of your grid
    DayOfWeek dayOfWeek = startOfWeek.plus(column % 7); // Use modulo 7 for safety

    return LocalDate.now()
            .with(TemporalAdjusters.previousOrSame(dayOfWeek)) // This ensures it's the correct day of this week
            .atStartOfDay()
            .plusHours(hour);
  }
}