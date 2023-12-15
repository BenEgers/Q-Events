package com.example.demo.mappers;

import com.example.demo.enums.Locatie;
import com.example.demo.event.Event;
import com.example.demo.event.EventDTO;
import com.example.demo.meeting_files.FileDTO;
import com.example.demo.meeting_files.FileEntity;
import com.example.demo.user.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EventMapper {

    private final UserService userService;

    private final UserMapper userMapper;

    public EventMapper(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public UserDTO toUserDTO(User user) {
        Integer id = user.getId();
        String name = user.getName();
        String email = user.getEmail();

        return new UserDTO(id, name, email);
    }
        public EventDTO toEventDto(Event event) {
        Integer id = event.getId();
        String titel = event.getTitel();
        String omschrijving = event.getOmschrijving();
        Locatie locatie = event.getLocatie();
        String dateTime = event.getDateTime();

        UserDTO organizer = this.userMapper.toUserDto(event.getOrganizer());

        Set<UserDTO> deelnemers = event
        .getDeelnemers()
        .stream().map(userEntity -> userService.findUserById(userEntity.getId()))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(userMapper::toUserDto)
        .collect(Collectors.toSet());

        return new EventDTO(id, titel, omschrijving, locatie, dateTime, organizer, deelnemers);
    }

    public Event toEvent(EventDTO event) {
        Integer id = event.getId();
        String titel = event.getTitel();
        String omschrijving = event.getOmschrijving();
        Locatie locatie = event.getLocatie();
        String dateTime = event.getDateTime();

        User organizer = this.userMapper.toUser(event.getOrganizer());

        Set<User> deelnemers = event
        .getDeelnemers()
        .stream()
        .map(userDTO -> {
            return new User(userDTO.getId());
        })
        .collect(Collectors.toSet());

        return new Event(id, titel, omschrijving, locatie, dateTime, organizer, deelnemers);
    }

    public FileDTO toFileDTO(FileEntity file) {
        Long id = file.getId();
        String fileName = file.getFileName();
        Integer eventId = file.getEventId();
        return new FileDTO(id, fileName, eventId);
    }

    public FileEntity toFile(FileDTO file) {
        Long id = file.getId();
        String fileName = file.getFileName();
        Integer eventId = file.getEventId();
        return new FileEntity(id, fileName, eventId);
    }
}
