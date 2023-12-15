package com.example.demo.event;

import com.example.demo.enums.Locatie;
import com.example.demo.meeting_files.FileDTO;
import com.example.demo.meeting_files.FileEntity;
import com.example.demo.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private Integer id;
    private String titel;
    private String omschrijving;
    private Locatie locatie;
    private String dateTime;
    private UserDTO organizer;
    private Set<UserDTO> deelnemers;
}
