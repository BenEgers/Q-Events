package com.example.demo.email;

import com.example.demo.event.Event;
import com.example.demo.user.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;
    private final String FROM_EMAIL = "bengieegers@gmail.com";

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String[] toEmail, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM_EMAIL);
        message.setText(body);
        message.setSubject(subject);
        message.setTo(toEmail);

        mailSender.send(message);
        System.out.println("Email Send");
    }

    @Override
    public void sendWelcomeEmail(String toEmail, User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        String subject = "🎉🎆🎊♥️Welcome 2 Q-Events";
        message.setFrom(FROM_EMAIL);
        String body = "Welcome, " + user.getName() + "\n Good of you to join our platform";
        message.setText(body);
        message.setSubject(subject);
        message.setTo(toEmail);

        mailSender.send(message);
    }


    @Override
    public void sendLoginEmail(String toEmail, User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        String subject = "🤨🧐This you???";
        message.setFrom(FROM_EMAIL);
        String body = "Recent login into ur account: " + user.getName() +
                "on Q-Events. \n I can't do anything about it, just letting you now upfront";
        message.setText(body);

        message.setSubject(subject);
        message.setTo(toEmail);

        mailSender.send(message);
        System.out.println("Login notification send");
    }

    @Override
    public void sendEventNotificationEmail(String[] toEmail, Event event) {
        SimpleMailMessage message = new SimpleMailMessage();
        String subject = "🕔🎌New Event on your schedule";

        String body = "Details for your event: " + event.getTitel() +
                "\n\n Details: " +
                "\n Titel: " + event.getTitel()+
                "\n Omschrijving: " + event.getTitel() +
                "\nLocatie: " + event.getLocatie().toString()+
                "\nDatum: " + event.getTitel() +
                " For your information";

        message.setFrom(FROM_EMAIL);
        message.setText(body);
        message.setSubject(subject);
        message.setTo(toEmail);

        mailSender.send(message);
        System.out.println("Notification Email sent");
    }

    @Override
    public void sendEventUpdateEmail(String[] toEmail, Event event) {
        SimpleMailMessage message = new SimpleMailMessage();
        String subject = "🙇‍♂️🏃‍♂️Event details changed";

        String body = "Details changed for your meeting: " + event.getTitel() +
                "\n\n New Details: " +
                "\n Titel: " + event.getTitel()+
                "\n Omschrijving: " + event.getTitel() +
                "\nLocatie: " + event.getLocatie().toString()+
                "\nDatum: " + event.getTitel() +
                " For your information";

        message.setFrom(FROM_EMAIL);
        message.setText(body);
        message.setSubject(subject);
        message.setTo(toEmail);

        mailSender.send(message);
        System.out.println("Edit notification Email sent");


    }

}
