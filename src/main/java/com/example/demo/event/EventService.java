package com.example.demo.event;

import com.example.demo.user.User;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Event createEvent(Event event);

    Optional<Event> findEvent(String title);
    Optional<Event> findEvent(Integer id);

    List<Event> findEventsOfUser(Integer organizerId);
    List<Event> searchEvents(String titel);
    List<Event> findByDeelnemer(Integer deelnemerId);

    List<Event> findAllEvents();
    Event updateEvent(Event event);

    Event updateEventNoEmail(Event event);

    void deleteEvent(Integer eventId);

}
