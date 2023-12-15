package com.example.demo.email;

import com.example.demo.event.Event;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final String FROM_EMAIL = "bengieegers@gmail.com";

    public EmailServiceImpl(JavaMailSender mailSender, UserRepository userRepository) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
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
    public void sendWelcomeEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        String subject = "🎉🎆🎊♥️Welcome 2 Q-Events";
        message.setFrom(FROM_EMAIL);
        String body = "Welcome, " + user.getName() + "\n Good of you to join our platform";
        message.setText(body);
        message.setSubject(subject);
        message.setTo(user.getEmail());

        mailSender.send(message);
    }


    @Override
    public void sendLoginEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        String subject = "🤨🧐This you???";
        message.setFrom(FROM_EMAIL);
        String body = "Recent login into ur account: " + user.getName() +
                " on Q-Events. \n I can't do anything about it, just letting you know upfront";
        message.setText(body);

        message.setSubject(subject);
        message.setTo(user.getEmail());

        mailSender.send(message);
        System.out.println("Login notification send");
    }

    @Override
    public void sendEventNotificationEmail(Event event) {
        List<String> toEmail = event.getDeelnemers().stream()
                .map(deelnemer -> userRepository.findById(deelnemer.getId())) // Assuming Deelnemer has a reference to User
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(User::getEmail).toList();

        String[] usersArray = getEmailList(toEmail);

        SimpleMailMessage message = new SimpleMailMessage();
        String subject = "🕔🎌New Event on your schedule";

        String body = "Details for your event: " + event.getTitel() +
                "\n\n Details: " +
                "\n • Titel: " + event.getTitel()+
                "\n • Omschrijving: " + event.getTitel() +
                "\n • Locatie: " + event.getLocatie().toString()+
                "\n • Datum: " + event.getDateTime() +
                "\n\n • Deelnemers: " + toEmail +
                " For your information";

        message.setFrom(FROM_EMAIL);
        message.setText(body);
        message.setSubject(subject);
        message.setTo(usersArray);

        mailSender.send(message);
        System.out.println("Notification Email sent");
    }

    @Override
    public void sendEventUpdateEmail(Event event) {
        List<String> toEmail = event.getDeelnemers().stream()
                .map(deelnemer -> userRepository.findById(deelnemer.getId())) // Assuming Deelnemer has a reference to User
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(User::getEmail).toList();

            String[] usersArray = getEmailList(toEmail);


        SimpleMailMessage message = new SimpleMailMessage();
        String subject = "🙇‍♂️🏃‍♂️Event details changed";


        String body = "Details changed for your meeting: " + event.getTitel() +
                "\n\n New Details: " +
                "\n • Titel: " + event.getTitel()+
                "\n • Omschrijving: " + event.getTitel() +
                "\n • Locatie: " + event.getLocatie().toString()+
                "\n • Datum: " + event.getDateTime() +
                "\n\n • Deelnemers: " + Arrays.toString(usersArray) +
                " For your information";

        message.setFrom(FROM_EMAIL);
        message.setText(body);
        message.setSubject(subject);
        message.setTo(usersArray);

        System.out.println(Arrays.toString(usersArray));
        mailSender.send(message);
        System.out.println("Edit event notification sent");


    }

    @Override
    public void sendEventDeleteEmail(Event event) {
        List<String> toEmail = event.getDeelnemers().stream()
                .map(deelnemer -> userRepository.findById(deelnemer.getId())) // Assuming Deelnemer has a reference to User
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(User::getEmail).toList();

        String[] usersArray = getEmailList(toEmail);

        SimpleMailMessage message = new SimpleMailMessage();
        String subject = "🙇‍♂️🏃‍♂️Event cancelled";

        String body = "The event: " + event.getTitel() + "has been cancelled!" +
                "\n Enjoy your free time on: " + event.getTitel() + ".";

        message.setFrom(FROM_EMAIL);
        message.setText(body);
        message.setSubject(subject);
        message.setTo(usersArray);

        mailSender.send(message);
        System.out.println("Delete notification Email sent");


    }

    @Override
    public String[] getEmailList(List<String> emails) {
        String[] usersArray = new String[emails.size()];
        for( int i = 0; i< emails.size(); i++){
            usersArray[i] = emails.get(i);
        }
        return  usersArray;
    }


}



