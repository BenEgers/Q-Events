package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {

        MvcRequestMatcher.Builder mvcRequestMatcher = new MvcRequestMatcher.Builder(introspector);

        http.csrf(csrf -> csrf
                        .ignoringRequestMatchers(toH2Console()).disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(mvcRequestMatcher.pattern("/api/**")).permitAll()
//                        .anyRequest().permitAll()/
                );

        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        http.sessionManagement(session  -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }
}