package com.example.demo.email;

import com.example.demo.event.Event;
import com.example.demo.user.User;

public interface EmailService {

    void sendEmail(String[] toEmail, String subject, String body);
    void sendWelcomeEmail(String toEmail, User user);
    void sendLoginEmail(String toEmail, User user);
    void sendEventNotificationEmail(String[] toEmail, Event event);
    void sendEventUpdateEmail(String[] toEmail, Event event);

}
