package com.example.demo.user;

import com.example.demo.event.Event;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/find/{name}")
    public Optional<User> getUserByName(@PathVariable ("name") String name){
        return userService.findUser(name);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }


    @PostMapping()
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping()
    public User updateUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @DeleteMapping()
    public Integer deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return 1;
    }

    //Runs after dependency injection has been performed
    @PostConstruct
    void init(){
        List<Event> events = new ArrayList<>();
        List<Event> ownEvents = new ArrayList<>();
        String password = "1234";
        String encryptedPassword = passwordEncoder.encode(password);
        User user1 = new User(1,
                "Ben Franklin", "bfranklin@gmail.com",
                encryptedPassword, ownEvents, events)
        ;
        User user2 = new User(2,
                "Ben Egers", "beagers@qualogy.com",
                encryptedPassword, ownEvents, events)
        ;
        User user3 = new User(100,
                        "Rajeev", "rharpal@qualogy.com",
                        encryptedPassword, ownEvents, events);
        User user4 = new User(4,
                        "Jimmy Karpatoe", "jkarpatoe@qualogy.com",
                        encryptedPassword, ownEvents, events)
                ;
        User user5 = new User(5,
                        "Miguel Lachman", "milachman@qualogy.com",
                        encryptedPassword, ownEvents, events)
                ;

//        userService.createUser(user1);
//        userService.createUser(user2);
//        userService.createUser(user3);
//        userService.createUser(user4);
//        userService.createUser(user5);
    }
}
