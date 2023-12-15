package com.example.demo.event;

import com.example.demo.enums.Locatie;
import com.example.demo.meeting_files.FileEntity;
import com.example.demo.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events_jv")
public class Event {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, length = 75)
    private String titel;
    @Column(nullable = false, length = 450)
    private String omschrijving;
    @Column(nullable = false)
    private Locatie locatie;
    @Column(name = "datum_en_tijd")
    private String dateTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "organizer_id")
    private User organizer;
    @ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "deelnemers")
    private Set<User> deelnemers;

    public Event(String titel, String omschrijving, Locatie locatie, String dateTime, User organizer) {
        this.titel = titel;
        this.omschrijving = omschrijving;
        this.locatie = locatie;
        this.dateTime = dateTime;
        this.organizer = organizer;
    }

}
