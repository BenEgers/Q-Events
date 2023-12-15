package com.example.demo.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    Optional<Event> findByTitelIgnoreCase(String title);
    List<Event> findByTitelContainsIgnoreCase(String titel);
    List<Event> findByOrganizerId(Integer organizerId);

    List<Event> findEventByDeelnemersId(Integer deelnemerId);

}
