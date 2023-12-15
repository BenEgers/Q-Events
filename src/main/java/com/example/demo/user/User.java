package com.example.demo.user;

import com.example.demo.event.Event;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_jv")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

