package com.example.demo.email;

import com.example.demo.event.Event;
import com.example.demo.user.User;
import com.example.demo.user.UserCreationDTO;

import java.util.List;

public interface EmailService {

    void sendEmail(String[] toEmail, String subject, String body);
    void sendWelcomeEmail(User user);
    void sendLoginEmail(User user);
    void sendEventNotificationEmail(Event event);
    void sendEventUpdateEmail(Event event);
    void sendEventDeleteEmail(Event event);

    String[] getEmailList(List<String> emails);

}
