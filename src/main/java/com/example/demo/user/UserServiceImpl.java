package com.example.demo.user;

import com.example.demo.email.EmailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl  implements  UserService{

    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public User createUser(User user) {
        User newUser = userRepository.save(user);
        if(Objects.equals(newUser.getEmail(), user.getEmail())){
            this.emailService.sendWelcomeEmail(newUser);
        }
        return newUser;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchByName(String naam) {
        return  userRepository.findByNameContainsIgnoreCase(naam);
    }

    @Override
    public Optional<User> findUser(String naam) {
        return userRepository.findByNameIgnoreCase(naam);
    }

    @Override
    public Optional<User> authenticateUser(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public boolean emailExist(String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);

    }


}
