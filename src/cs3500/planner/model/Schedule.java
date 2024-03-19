package cs3500.planner.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Schedule class that represents a schedule of events for a single user.
 */
public class Schedule implements ScheduleModel {
  //list of all events in the user's schedule
  private final List<Event> events;

  /**
   * Constructor an empty schedule with no events.
   */
  public Schedule() {
    this.events = new ArrayList<>();
  }

  @Override
  public boolean addEvent(Event event) {
    if (isTimeSlotFree(event.getStartTime(), event.getEndTime())) {
      events.add(event);
      return true;
    }
    return false;
  }

  @Override
  public boolean removeEvent(String eventId) {
    return events.removeIf(event -> event.getName().equals(eventId));
  }

  @Override
  public boolean modifyEvent(String eventId, Event newEvent) {
    for (int i = 0; i < events.size(); i++) {
      if (events.get(i).getName().equals(eventId)) {
        if (isTimeSlotFree(newEvent.getStartTime(), newEvent.getEndTime())
                || events.get(i).getStartTime().equals(newEvent.getStartTime())
                && events.get(i).getEndTime().equals(newEvent.getEndTime())) {
          events.set(i, newEvent);
          return true;
        } else {
          return false;
        }
      }
    }
    return false;
  }

  @Override
  public List<Event> getEventsAt(LocalDateTime dateTime) {
    return events.stream()
            .filter(event -> !event.getStartTime().isAfter(dateTime)
                    && !event.getEndTime().isBefore(dateTime))
            .collect(Collectors.toList());
  }

  @Override
  public List<Event> getAllEvents() {
    return new ArrayList<>(events);
  }

  @Override
  public boolean isTimeSlotFree(LocalDateTime startTime, LocalDateTime endTime) {
    return events.stream()
            .noneMatch(event -> event.getStartTime()
                    .isBefore(endTime) && event.getEndTime().isAfter(startTime));
  }

  @Override
  public Event getEventById(String eventId) {
    return events.stream()
            .filter(event -> event.getName().equals(eventId))
            .findFirst()
            .orElse(null);
  }

  @Override
  public Map<DayOfWeek, List<Event>> getWeeklyEvents() {
    Map<DayOfWeek, List<Event>> weeklyEvents = new EnumMap<>(DayOfWeek.class);
    for (DayOfWeek day : DayOfWeek.values()) {
      weeklyEvents.put(day, new ArrayList<>());
    }
    for (Event event : events) {
      LocalDate eventDate = event.getStartTime().toLocalDate();
      DayOfWeek dayOfWeek = eventDate.getDayOfWeek();
      List<Event> eventsForDay = weeklyEvents.get(dayOfWeek);
      eventsForDay.add(event);
    }
    return weeklyEvents;
  }
}
