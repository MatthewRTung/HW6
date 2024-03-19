package cs3500.planner.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Event class that represents a single event in the planner.
 */
public class Event implements EventModel, Comparable<EventModel> {
  private final String name;
  private final LocalDateTime startTime;
  private final LocalDateTime endTime;
  private final String location;
  private final boolean isOnline;
  private final List<String> userList;
  private final boolean isHybrid;
  private final String hostId;

  /**
   * Constructor for the Event class.
   * @param name name of the event
   * @param location location of the event
   * @param isOnline whether the event is online or not
   * @param startTime the start time of the event
   * @param endTime the end time of the event
   */
  public Event(String name, String location, boolean isOnline, LocalDateTime startTime,
               LocalDateTime endTime, boolean isHybrid, String hostId) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Event name cannot be null or empty.");
    }
    if (location == null || (!isOnline && location.trim().isEmpty())) {
      throw new IllegalArgumentException("Event location cannot be null or empty " +
              "if the event is online.");
    }
    if (startTime == null) {
      throw new IllegalArgumentException("Event start time cannot be null.");
    }
    //Class invariant
    //Here we are checking that the start time is before the end time. This makes sure that the
    //class invariant is maintained when the object is created.
    //The check ensures that the object cannot be created with an invalid time range where the
    //end time is before the start time which enforces the class invariant and enforced in the
    //constructor.
    if (endTime == null || endTime.isBefore(startTime)) {
      throw new IllegalArgumentException("Event end time cannot be null or before the start time.");
    }
    if (hostId == null || hostId.trim().isEmpty()) {
      throw new IllegalArgumentException("Event host ID cannot be null or empty.");
    }
    this.name = name;
    this.location = location;
    this.isOnline = isOnline;
    this.startTime = startTime;
    this.endTime = endTime;
    this.userList = new ArrayList<>();
    this.isHybrid = isHybrid;
    this.hostId = hostId;
  }

  @Override
  public boolean overlaps(Event event) {
    return !startTime.isAfter(event.getEndTime()) && !endTime.isBefore(event.getStartTime());
  }

  @Override
  public void addInvitee(String userId) {
    if (!userList.contains(userId)) {
      userList.add(userId);
    }
  }

  @Override
  public void removeInvitee(String userId) {
    userList.remove(userId);
  }

  @Override
  public String getLocation() {
    if (isHybrid) {
      return "Hybrid: Online and at " + location;
    } else if (isOnline) {
      return "Online: " + location;
    } else {
      return location;
    }
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public LocalDateTime getStartTime() {
    return startTime;
  }

  @Override
  public LocalDateTime getEndTime() {
    return endTime;
  }

  @Override
  public List<String> getInvitees() {
    return new ArrayList<>(userList);
  }

  @Override
  public boolean isOnline() {
    return isOnline;
  }

  @Override
  public boolean isUserInvited(String userId) {
    return userList.contains(userId);
  }

  @Override
  public boolean isCurrentlyHappening() {
    LocalDateTime now = LocalDateTime.now();
    return now.isAfter(startTime) && now.isBefore(endTime);
  }

  @Override
  public int compareTo(EventModel other) {
    return this.startTime.compareTo(other.getStartTime());
  }

  @Override
  public String getHostId() {
    return hostId;
  }

  //method to see if an event is both online and in person
  private boolean isHybrid() {
    return isHybrid;
  }

  //TODO: Implement automatic event scheduling
  //Implement a way to check if a physical location is available when scheduling an event
  //Ensure that logic throughout the system can handle an even being both online and in person
  //Maybe use ID instead of name.
}
