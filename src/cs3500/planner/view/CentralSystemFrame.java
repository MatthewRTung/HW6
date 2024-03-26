package cs3500.planner.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.io.File;

import cs3500.planner.model.ReadOnlyCentralSystemModel;

public class CentralSystemFrame extends JFrame implements CentralSystemView {
  private JPanel schedulePanel;
  private JComboBox<String> userDropDown;
  private JButton loadButton;
  private JButton saveButton;

  public CentralSystemFrame(ReadOnlyCentralSystemModel model) {
    super("Planner Central System");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    initializeMenu();
    initializeSchedulePanel();
    initializeControlPanel();

    this.pack();
    this.setVisible(true);
  }

  private void initializeMenu() {
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");

    JMenuItem loadMenuItem = new JMenuItem("Load XML");
    loadMenuItem.addActionListener(this::loadXMLAction);

    JMenuItem saveMenuItem = new JMenuItem("Save Schedules");
    saveMenuItem.addActionListener(this::saveSchedulesAction);

    fileMenu.add(loadMenuItem);
    fileMenu.add(saveMenuItem);

    menuBar.add(fileMenu);
    this.setJMenuBar(menuBar);
  }

  private void initializeSchedulePanel() {
    schedulePanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
      }
    };
    schedulePanel.setPreferredSize(new Dimension(800, 600));
    this.add(schedulePanel, BorderLayout.CENTER);
  }

  private void drawGrid(Graphics g) {
    g.setColor(Color.LIGHT_GRAY);
    int rows = 24;
    int cols = 7;
    int cellWidth = schedulePanel.getWidth() / cols;
    int cellHeight = schedulePanel.getHeight() / rows;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        g.drawRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
      }
    }
  }

  private void initializeControlPanel() {
    JPanel controlPanel = new JPanel();
    loadButton = new JButton("Load Schedule");
    saveButton = new JButton("Save Schedule");

    loadButton.addActionListener(this::loadXMLAction);
    saveButton.addActionListener(this::saveSchedulesAction);

    controlPanel.add(loadButton);
    controlPanel.add(saveButton);

    this.add(controlPanel, BorderLayout.SOUTH);
  }

  private void loadXMLAction(ActionEvent e) {
    JFileChooser fileChooser = new JFileChooser();
    int option = fileChooser.showOpenDialog(this);
    if (option == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      System.out.println("Selected file: " + selectedFile.getAbsolutePath());
    }
  }

  private void saveSchedulesAction(ActionEvent e) {
    // Placeholder action for saving schedules
    System.out.println("Save schedules action triggered");
  }

  @Override
  public void updateView() {
    // Method to update the view when model changes
  }

  @Override
  public void displayError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }
}
