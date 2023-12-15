package com.example.demo.event;

import com.example.demo.mappers.EventMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/events")
public class EventController {

    private final EventService eventService;
    private final EventMapper mapper;

    public EventController(EventService eventService, EventMapper mapper) {
        this.eventService = eventService;
        this.mapper = mapper;
    }

    @GetMapping("/{titel}")
    public Optional<EventDTO> getEvent(@PathVariable("titel") String titel){
        return eventService.findEvent(titel).map(mapper::toEventDto);
    }
    @GetMapping("/find/{id}")
    public Optional<EventDTO> getEventById(@PathVariable("id") Integer id){
        return eventService.findEvent(id).map(mapper::toEventDto);
    }
    @GetMapping("/search/{titel}")
    public List<EventDTO> searchEventContaining(@PathVariable("titel") String titel){
        return eventService.searchEvents(titel).stream().map(mapper::toEventDto).collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    public List<EventDTO> getEventsOfUser(@PathVariable("id") Integer organizerId){
        return eventService.findEventsOfUser(organizerId).stream().map(mapper::toEventDto).collect(Collectors.toList());
    }
    @GetMapping("/deelnemer/{id}")
    public List<EventDTO> getEventsOfDeelnemer(@PathVariable("id") Integer deelnemerId){
        return eventService.findByDeelnemer(deelnemerId).stream().map(mapper::toEventDto).collect(Collectors.toList());
    }
    @GetMapping("/all")
    public List<EventDTO> getAllEvents(){
        return eventService.findAllEvents().stream().map(mapper::toEventDto).collect(Collectors.toList());
    }

    @PostMapping()
    public EventDTO createEvent(@RequestBody EventDTO eventDTO){
        Event eventCreated = eventService.createEvent(mapper.toEvent(eventDTO));
        return mapper.toEventDto(eventCreated);
    }

    @PutMapping("/update/email")
    public EventDTO updateEvent(@RequestBody EventDTO eventDTO){
        Event updatedEevent = eventService.updateEvent(mapper.toEvent(eventDTO));
        return mapper.toEventDto(updatedEevent);
    }
    @PutMapping("/update")
    public EventDTO updateEventNoEmail(@RequestBody EventDTO eventDTO){
        Event updatedEevent = eventService.updateEventNoEmail(mapper.toEvent(eventDTO));
        return mapper.toEventDto(updatedEevent);
    }

    @DeleteMapping("/{id}")
    public Integer deleteEvent(@PathVariable("id") Integer eventId){
        eventService.deleteEvent(eventId);
        return 1;
    }

}
