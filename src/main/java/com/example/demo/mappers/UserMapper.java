package com.example.demo.mappers;

import com.example.demo.user.User;
import com.example.demo.user.UserCreationDTO;
import com.example.demo.user.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserCreationDTO userCreationDTO) {;
        String name = userCreationDTO.getName();
        String email = userCreationDTO.getEmail();
        String password = userCreationDTO.getPassword();

        return new User(name, email, password);
    }
    public User toUser(UserDTO userDTO) {
        Integer id = userDTO.getId();
        String name = userDTO.getName();
        String email = userDTO.getEmail();

        return new User(id, name, email);
    }

    public UserDTO toUserDto (User user ) {
        Integer id = user.getId();
        String name = user.getName();
        String email = user.getEmail();

        return new UserDTO(id, name, email);
    }
}
