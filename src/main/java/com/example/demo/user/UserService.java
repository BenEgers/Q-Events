package com.example.demo.user;


import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);

    List<User> findAllUsers();

    List<User> searchByName(String naam);

    Optional<User> findUser(String naam);
    Optional<User> authenticateUser(String naam);

    Optional<User> findUserById(Integer id);

    Optional<User> getUserByEmail(String email);

    boolean emailExist(String email);

    User updateUser(User user);

    void deleteUser(Integer userId);
}
