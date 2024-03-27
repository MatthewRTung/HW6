package cs3500.planner;

import java.util.List;

import cs3500.planner.model.CentralSystem;
import cs3500.planner.model.Event;
import cs3500.planner.model.ReadOnlyCentralSystemModel;
import cs3500.planner.model.Schedule;
import cs3500.planner.view.CentralSystemFrame;
import cs3500.planner.view.EventFrame;
import cs3500.planner.model.ReadOnlyScheduleModel;
import cs3500.planner.xml.XMLConfigurator;

public class PlannerRunner {
  public static void main(String[] args) {
    //For the event frame
    //ReadOnlyScheduleModel cs = new Schedule();
    //EventFrame eventFrame = new EventFrame(cs);
    //eventFrame.setVisible(true);
    CentralSystem model = new CentralSystem();
    CentralSystemFrame frame = new CentralSystemFrame(model);
    frame.setVisible(true);
    // Create an instance of XMLConfigurator
//    XMLConfigurator configurator = new XMLConfigurator();
//
//    // Path to the XML file with event data
//    String xmlFilePath = "/Users/matthewtung/Downloads/OOD/HW6/prof.xml";
//
//    // Parse the XML file into a list of Event objects
//    List<Event> events = configurator.readXMLFile(xmlFilePath);
//
//    // Create a new CentralSystem model
//    CentralSystem centralSystem = new CentralSystem();
//
//    // Extract the user ID from the XML file and add it to CentralSystem
//    String userIdFromXML = configurator.readScheduleUserId(xmlFilePath);
//    System.out.println("User ID from XML: " + userIdFromXML);
//    centralSystem.addUser(userIdFromXML);
//
//    // Add each event from the XML to the CentralSystem for the extracted user ID
//    for (Event event : events) {
//      boolean eventAdded = centralSystem.createEvent(userIdFromXML, event);
//      System.out.println("Event added for " + userIdFromXML + ": " + eventAdded);
//    }
//
//    // Instantiate the main system frame with the CentralSystem model
//    CentralSystemFrame frame = new CentralSystemFrame(centralSystem);
//
//    // Set the frame visible to update and display the events
//    frame.setVisible(true);
//
//    // Trigger the view to update and reflect the loaded events
//    frame.updateView();
//
//    // Optionally, print the events for the user to confirm they're added correctly
//    List<Event> userEvents = centralSystem.getEventsForUser(userIdFromXML);
//    for (Event userEvent : userEvents) {
//      System.out.println("User Event for " + userIdFromXML + ": " + userEvent.getName());
//    }
  }
}
