package cs3500.planner.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CentralSystem class that is used as the main system of the planner system. Allows for
 * managing a collection of user schedules.
 */
public class CentralSystem implements CentralSystemModel {
  //Maps user Ids to their ScheduleModel objects
  private final Map<String, Schedule> userSchedules;

  /**
   * Constructs a new CentralSystem object with an empty schedule.
   */
  public CentralSystem() {
    userSchedules = new HashMap<>();
  }

  @Override
  public void addUser(String userId) {
    if (!userSchedules.containsKey(userId)) {
      userSchedules.put(userId, new Schedule());
    }
  }

  @Override
  public boolean removeUser(String userId) {
    if (userSchedules.containsKey(userId)) {
      userSchedules.remove(userId);
      return true;
    }
    return false;
  }

  @Override
  public Schedule getUserSchedule(String userId) {
    if (userId == null || userId.isEmpty()) {
      throw new IllegalArgumentException("User ID cannot be empty or null.");
    }
    return userSchedules.get(userId);
  }

  @Override
  public boolean createEvent(String userId, Event event) {
    Schedule schedule = userSchedules.get(userId);
    if (schedule != null && schedule.isTimeSlotFree(event.getStartTime(), event.getEndTime())) {
      return schedule.addEvent(event);
    }
    return false;
  }

  @Override
  public boolean modifyEvent(String userId, String eventId, Event updatedEvent) {
    Schedule schedule = userSchedules.get(userId);
    if (schedule != null) {
      Event existingEvent = schedule.getEventById(eventId);
      if (existingEvent != null) {
        if (!existingEvent.getHostId().equals(updatedEvent.getHostId())) {
          return false;
        }
        return schedule.modifyEvent(eventId, updatedEvent);
      }
    }
    return false;
  }

  @Override
  public boolean deleteEvent(String userId, String eventId) {
    ScheduleModel schedule = userSchedules.get(userId);
    if (schedule != null) {
      return schedule.removeEvent(eventId);
    }
    return false;
  }

  @Override
  public Map<String, Schedule> getAllSchedules() {
    return new HashMap<>(userSchedules);
  }

  @Override
  public List<String> getUserName() {
    return new ArrayList<>(userSchedules.keySet());
  }

  @Override
  public boolean doesEventConflict(Event event, String userName) {
    Schedule userSchedule = userSchedules.get(userName);
    if (userSchedule == null) {
      throw new IllegalArgumentException("User does not exist.");
    }
    return !userSchedule.isFree(event);
  }

  @Override
  public List<Event> getEventsForUser(String userName) {
    Schedule userSchedule = userSchedules.get(userName);
    if (userSchedule == null) {
      throw new IllegalArgumentException("User does not exist.");
    }
    return userSchedule.getEvents();
  }
}