package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


import java.util.Arrays;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
//                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
//                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
//
//        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
//                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsFilter(urlBasedCorsConfigurationSource);
//    }


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

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .requestMatchers("/api/**")
//                .permitAll();
//        return http.build();
//    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("*"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("api/**", configuration);
//        return source;
//    }
}