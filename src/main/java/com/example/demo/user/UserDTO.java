package com.example.demo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;
    private String name;
    private String email;

    public UserDTO(Integer id) {
        this.id = id;
    }

    public UserDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
