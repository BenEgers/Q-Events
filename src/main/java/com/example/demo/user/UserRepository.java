package com.example.demo.user;

import com.example.demo.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNameIgnoreCase(String naam);
    Optional<User> findByEmailIgnoreCase(String email);
    List<User> findByNameContainsIgnoreCase(String naam);
    boolean existsByEmailIgnoreCase(String email);


}
