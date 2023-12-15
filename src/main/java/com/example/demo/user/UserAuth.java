package com.example.demo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {

        private String email;
        private String password;

        public String getEncryptedPassword(String password, PasswordEncoder passwordEncoder) {
                return passwordEncoder.encode(password);
        }
}
