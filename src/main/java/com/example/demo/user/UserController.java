package com.example.demo.user;

import com.example.demo.mappers.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/find/{name}")
    public Optional<UserDTO> getUserByName(@PathVariable ("name") String name){
        return userService.findUser(name).map(userMapper::toUserDto);
    }
    @GetMapping("/search/{name}")
    public List<UserDTO> searchByName(@PathVariable ("name") String name){
        return userService.searchByName(name).stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }
    @GetMapping("/find/id/{id}")
    public Optional<UserDTO> getUserById(@PathVariable ("id") Integer id){
        return userService.findUserById(id).map(userMapper::toUserDto);
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers(){
        return userService.findAllUsers().stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    @PostMapping()
    public UserDTO createUser(@RequestBody UserCreationDTO user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser =  userService.createUser(userMapper.toUser(user));
        return userMapper.toUserDto(savedUser);
    }

    @PutMapping()
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
    @DeleteMapping("/{id}")
    public Integer deleteUser(@PathVariable("id") Integer userId) {
        userService.deleteUser(userId);
        return 1;
    }

    //Runs after dependency injection has been performed
//    @PostConstruct
//    void init(){
//        String password = "1234";
//        String encryptedPassword = passwordEncoder.encode(password);
//        User user1 = new User(1,
//                "Ben Franklin", "bfranklin@gmail.com",
//                encryptedPassword)
//        ;
//        User user2 = new User(2,
//                "Ben Egers", "beagers@qualogy.com",
//                encryptedPassword)
//        ;
//        User user3 = new User(100,
//                        "Rajeev", "rharpal@qualogy.com",
//                        encryptedPassword);
//        User user4 = new User(4,
//                        "Jimmy Karpatoe", "jkarpatoe@qualogy.com",
//                        encryptedPassword)
//                ;
//        User user5 = new User(5,
//                        "Miguel Lachman", "milachman@qualogy.com",
//                        encryptedPassword)
//                ;
//
//        userService.createUser(user1);
//        userService.createUser(user2);
//        userService.createUser(user3);
//        userService.createUser(user4);
//        userService.createUser(user5);
//    }
}
