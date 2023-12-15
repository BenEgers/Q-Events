package com.example.demo.event;

import com.example.demo.email.EmailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;
    private final EmailService emailService;

    public EventServiceImpl(EventRepository eventRepository, EmailService emailService) {
        this.eventRepository = eventRepository;
        this.emailService = emailService;
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> findEvent(String title) {
        return eventRepository.findByTitelIgnoreCase(title);
    }
    @Override
    public Optional<Event> findEvent(Integer id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> findEventsOfUser(Integer organizer) {
        return this.eventRepository.findByOrganizerId(organizer);
    }

    @Override
    public List<Event> findByDeelnemer(Integer deelnemerId) {
        return this.eventRepository.findEventByDeelnemersId(deelnemerId);
    }

    @Override
    public List<Event> searchEvents(String titel) {
        System.out.println(this.eventRepository.findByTitelContainsIgnoreCase(titel));
        return this.eventRepository.findByTitelContainsIgnoreCase(titel);
    }

    @Override
    public Event createEvent(Event event) {
        emailService.sendEventNotificationEmail(event);
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        emailService.sendEventUpdateEmail(event);
        return eventRepository.save(event);
    }
    @Override
    public Event updateEventNoEmail(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Integer eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if(eventOptional.isPresent()){
        emailService.sendEventDeleteEmail(eventOptional.get());
        eventRepository.deleteById(eventId);
        }
    }
}
