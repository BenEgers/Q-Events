package com.example.demo.event;

import com.example.demo.user.User;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/events")
public class EventController {

    private final EventServiceImpl eventService;

    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{titel}")
    public Optional<Event> getEvent(@PathVariable("titel") String titel){
        return eventService.findEvent(titel);
    }

    @GetMapping("/all")
    public List<Event> getAllEvents(){
        return eventService.findAllEvents();

    }

    @PostMapping()
    public Event createEvent(@RequestBody Event event){
        return eventService.createEvent(event);
    }

    @PutMapping()
    public Event updateEvent(@RequestBody Event event){
        return eventService.updateEvent (event);
    }
    @DeleteMapping()
    public Integer deleteEvent(@RequestBody Event event){
        eventService.deleteEvent(event);
        return 1;
    }

}
