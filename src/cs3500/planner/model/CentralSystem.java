package cs3500.planner.model;

import java.util.HashMap;
import java.util.Map;

/**
 * CentralSystem class manages user schedules within the planner system.
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
}
