package com.example.demo.user;

import com.example.demo.event.Event;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "organizer")
    private List<Event> ownEvents;

    @ManyToMany(mappedBy = "deelnemers")
    private List<Event> events;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(Integer id, String name, String email, String password, List<Event> ownEvents, List<Event> events) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.ownEvents = ownEvents;
        this.events = events;
    }

    public User(String name, String email, String password, List<Event> ownEvents, List<Event> events) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.ownEvents = ownEvents;
        this.events = events;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Event> getOwnEvents() {
        return ownEvents;
    }

    public void setOwnEvents(List<Event> ownEvents) {
        this.ownEvents = ownEvents;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ownEvents=" + ownEvents +
                ", events=" + events +
                '}';
    }
}


