package com.example.demo.event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Event createEvent(Event event);

    Optional<Event> findEvent(String title);
    Optional<Event> findEvent(int id);
    List<Event> findAllEvents();
    Event updateEvent(Event event);

    void deleteEvent(Event event);

}
