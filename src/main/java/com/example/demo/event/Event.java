package com.example.demo.event;

import com.example.demo.enums.Locatie;
import com.example.demo.user.User;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "events")
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
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "organizer_id")
    private User organizer;
    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "deelnemer")
    private Set<User> deelnemers;
    @Column(name = "attachment_url")
    private String attachmentUrl;

    public Event() {
    }

    public Event(Integer id, String titel, String omschrijving, Locatie locatie, String dateTime, User organizer, Set<User> deelnemers, String attachmentUrl) {
        this.id = id;
        this.titel = titel;
        this.omschrijving = omschrijving;
        this.locatie = locatie;
        this.dateTime = dateTime;
        this.organizer = organizer;
        this.deelnemers = deelnemers;
        this.attachmentUrl = attachmentUrl;
    }

    public Event(String titel, String omschrijving, Locatie locatie, String dateTime, User organizer) {
        this.titel = titel;
        this.omschrijving = omschrijving;
        this.locatie = locatie;
        this.dateTime = dateTime;
        this.organizer = organizer;
    }

    public Event(String titel, String omschrijving, Locatie locatie, String dateTime, User organizer, Set<User> deelnemers, String attachmentUrl) {
        this.titel = titel;
        this.omschrijving = omschrijving;
        this.locatie = locatie;
        this.dateTime = dateTime;
        this.organizer = organizer;
        this.deelnemers = deelnemers;
        this.attachmentUrl = attachmentUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public Set<User> getDeelnemers() {
        return deelnemers;
    }

    public void setDeelnemers(Set<User> deelnemers) {
        this.deelnemers = deelnemers;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", titel='" + titel + '\'' +
                ", omschrijving='" + omschrijving + '\'' +
                ", locatie=" + locatie +
                ", dateTime=" + dateTime +
                ", organizer=" + organizer +
                ", deelnemers=" + deelnemers +
                ", attachmentUrl='" + attachmentUrl + '\'' +
                '}';
    }
}
