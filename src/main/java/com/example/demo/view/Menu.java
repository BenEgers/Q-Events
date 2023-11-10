package com.example.demo.view;

import com.example.demo.email.EmailServiceImpl;
import com.example.demo.enums.Locatie;
import com.example.demo.event.Event;
import com.example.demo.event.EventServiceImpl;
import com.example.demo.user.User;
import com.example.demo.user.UserServiceImpl;
import com.example.demo.view.screens.MenuItemsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Menu {

    private final UserServiceImpl userService;
    private final EventServiceImpl eventService;
    private final EmailServiceImpl emailService;
    private final MenuItemsRepository menuItemsRepository;
    private final PasswordEncoder passwordEncoder;
    Scanner scanner = new Scanner(System.in);
    User user;
    private String[] usersList;

    //Dependency injection
    public Menu(UserServiceImpl userService, EventServiceImpl eventService, EmailServiceImpl emailService, MenuItemsRepository menuItemsRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.eventService = eventService;
        this.emailService = emailService;
        this.menuItemsRepository = menuItemsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //-----------------------------------Menus-----------------------------------//
    public void startMenu() {
        System.out.println(
                """
                                        
                         .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .-----------------. .----------------.  .----------------.\s
                        | .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
                        | |    ___       | || |              | || |  _________   | || | ____   ____  | || |  _________   | || | ____  _____  | || |  _________   | || |    _______   | |
                        | |  .'   '.     | || |              | || | |_   ___  |  | || ||_  _| |_  _| | || | |_   ___  |  | || ||_   \\|_   _| | || | |  _   _  |  | || |   /  ___  |  | |
                        | | /  .-.  \\    | || |    ______    | || |   | |_  \\_|  | || |  \\ \\   / /   | || |   | |_  \\_|  | || |  |   \\ | |   | || | |_/ | | \\_|  | || |  |  (__ \\_|  | |
                        | | | |   | |    | || |   |______|   | || |   |  _|  _   | || |   \\ \\ / /    | || |   |  _|  _   | || |  | |\\ \\| |   | || |     | |      | || |   '.___`-.   | |
                        | | \\  `-'  \\_   | || |              | || |  _| |___/ |  | || |    \\ ' /     | || |  _| |___/ |  | || | _| |_\\   |_  | || |    _| |_     | || |  |`\\____) |  | |
                        | |  `.___.\\__|  | || |              | || | |_________|  | || |     \\_/      | || | |_________|  | || ||_____|\\____| | || |   |_____|    | || |  |_______.'  | |
                        | |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | |
                        | '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
                         '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'\s
                """

        );
        System.out.println("--------------------------");
        System.out.println("Welcome Welcome");
        System.out.println("State Your Business!!");
        menuItemsRepository.getMainMenuItems().forEach(System.out::println);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
               loginFlow();
                break;
            case "2":
                signUpFlow();
            case "0":
                System.out.println("\n See ya soon, Goodbye!");
                System.out.println(
                        """
                                                        
                                           _____                 _ _               \s
                                          / ____|               | | |              \s
                                         | |  __  ___   ___   __| | |__  _   _  ___\s
                                         | | |_ |/ _ \\ / _ \\ / _` | '_ \\| | | |/ _ \\
                                         | |__| | (_) | (_) | (_| | |_) | |_| |  __/
                                          \\_____|\\___/ \\___/ \\__,_|_.__/ \\__, |\\___|
                                                                          __/ |    \s
                                                                         |___/     \s
                                                                
                        """);
                System.exit(0);
                break;
            default:
                System.err.println("Invalid number try again \n");
                startMenu();
                break;
        }


    }
    private void dashboardMenu() {
        System.out.println("--------------------------");
        System.out.println("\n ");
        System.out.println("Welcome, " + user.getName());
        System.out.println("What are you here to do?");
        menuItemsRepository.getDashboardMenuItems().forEach(System.out::println);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                createEventFlow();
                break;
            case "2":
                viewAllUsersFlow();
                break;
            case "3":
                viewAllEventsFlow();
                break;
            case "4":
                editAccountFlow();
                break;
            case "0":
                logOutFlow();
                break;
            default:
                System.err.println("Invalid number try again \n");
                dashboardMenu();
                break;
        }
    }

    //------------------------------------Flows--------------------------------//

    //------------Authentication---------------//
    private void signUpFlow() {
        String name;
        String email;
        String password;
        String hashedPassword;
        System.out.println("--------------------------");
        System.out.println("\n ");
        System.out.println("Great of you to join us!");
        System.out.println("Please enter your name:");
        name = scanner.nextLine();
        System.out.println("Please enter your email:");
        email = scanner.nextLine();
        System.out.println("Please enter your password:");
        password = scanner.nextLine();

        if(userService.emailExist(email)){
            System.out.println("You already have an account");
            System.out.println("Redirecting to login menu");
            loginFlow();
            return;
        }

        hashedPassword = passwordEncoder.encode(password);

        System.out.println("Creating your account....");
        User newUser = new User(name, email, hashedPassword);
        user = userService.createUser(newUser);
        emailService.sendWelcomeEmail(email, user);
        dashboardMenu();

    }

    private void loginFlow() {
        String email;
        String password;
        boolean verified;
        System.out.println("--------------------------");
        System.out.println("\n ");
        System.out.println("Please enter your email: , Enter in \"0\" to Sign Up");
        email = scanner.nextLine();
        if(email.equals("0")){
            signUpFlow();
            return;
        }
        System.out.println("Please enter your password:");
        password = scanner.nextLine();

        Optional<User> userOptional = userService.getUserByEmail(email);
        User userFound;
        if(userOptional.isEmpty()){
            System.out.println("Invalid credentials, Try again...");
            loginFlow();
            return;
        }else{
            userFound = userOptional.get();
        }

        verified = verifyPassword(password, userFound.getPassword());

        if(!verified) {
            System.out.println("Invalid credentials, Try again...");
            loginFlow();
        }

        System.out.println("Loggin in......");
        user = userFound;
        emailService.sendLoginEmail(user.getEmail(), user);
        dashboardMenu();

    }

    private void logOutFlow() {
        System.out.println("--------------------------");
        System.out.println("\n ");
        System.out.println("Loggin out...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            System.exit(1);
        }

        startMenu();
    }

    //---------------User Flows -------------//

    private void viewAllUsersFlow() {
        System.out.println("--------------------------");
        System.out.println("\n ");
        System.out.println("All users:");
        List<User> userList = userService.findAllUsers();
        userList.forEach((user) -> System.out.println(user.getName()));
        System.out.println("Press Any key to go back");
        scanner.nextLine();
        dashboardMenu();
    }

    private void editAccountFlow() {
        System.out.println("--------------------------");
        System.out.println("\n ");
        System.out.println("Edit Account");
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println(" ");
        menuItemsRepository.getAccountMenuItems().forEach(System.out::println);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Enter your new name: ");
                String newName = scanner.nextLine();
                user.setName(newName);
                userService.updateUser(user);
                editAccountFlow();
                break;
            case "2":
                System.out.println("Enter your new password: ");
                String passw1 = scanner.nextLine();
                System.out.println("Enter your new password again: ");
                String passw2 = scanner.nextLine();
                if(!passw1.equals(passw2)){
                    System.out.println("Passwords don't match");
                    editAccountFlow();
                    break;
                }
                String hashedPassword = passwordEncoder.encode(passw1);
                user.setPassword(hashedPassword);
                userService.updateUser(user);
                editAccountFlow();
                break;
            case "0":
                dashboardMenu();
                break;
            default:
                System.err.println("Invalid number try again \n");
                editAccountFlow();
                break;
        }

    }

    //---------------Event Flows --------------//

    private void viewAllEventsFlow() {
        System.out.println("--------------------------");
        System.out.println("\n ");
        System.out.println("All Events:");
        List<Event> eventList = eventService.findAllEvents();
        eventList.forEach((event) -> System.out.println(event.getId() + " => Titel: " + event.getTitel()
                + ", Datum: " + event.getDateTime()
                + ", Locatie: " + event.getLocatie()));
        System.out.println("-------------------");
        System.out.println("Insert id of event you want to edit");
        System.out.println("Enter 0 if you want to go back");
        String choice = scanner.nextLine();
        if (choice.equals("0")) {
            dashboardMenu();
        } else {
            editEventFlow(choice);
        }

        dashboardMenu();
    }

    private void createEventFlow() {
        System.out.println("--------------------------");
        System.out.println("\n ");
        System.out.println("Create Event");
        Event event;
        String titel;
        String datum;
        String omschrijving;
        User organizer = user;

        System.out.println("Enter the title of the meeting:");
        titel = scanner.nextLine();
        System.out.println("Enter the date and time:");
        datum = scanner.nextLine();
        System.out.println("Enter a description of the meeting:");
        omschrijving = scanner.nextLine();

        Locatie locatie = getLocation();

        event = new Event(titel, omschrijving, locatie ,datum, organizer);
        System.out.println("Creating event.....");
        eventService.createEvent(event);
        usersList = getUsersList();
        emailService.sendEventNotificationEmail(usersList, event);
        viewAllEventsFlow();

    }

    private void editEventFlow(String id) {
        System.out.println("--------------------------");
        System.out.println("\n ");
        System.out.println("Edit Event");
        int idNumber = Integer.parseInt(id);
        Event event;
        Optional<Event> eventOptional = eventService.findEvent(idNumber);

        if(eventOptional.isEmpty()){
            System.out.println("No event found with id: "+id);
            viewAllEventsFlow();
            return;
        }else{
            event = eventOptional.get();
        }

        System.out.println("Titel: " + event.getTitel());
        System.out.println("Datum: " + event.getDateTime());
        System.out.println("Locatie: " + event.getLocatie());
        System.out.println("Omschrijving: " + event.getOmschrijving());
        System.out.println(" ");
        menuItemsRepository.getEditEventMenuItems().forEach(System.out::println);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Enter new title: ");
                String newTitle = scanner.nextLine();
                event.setTitel(newTitle);
                eventService.updateEvent(event);

                break;
            case "2":
                System.out.println("Enter new datum: ");
                String newDate = scanner.nextLine();
                event.setDateTime(newDate);
                eventService.updateEvent(event);

                break;
            case "3":
                Locatie locatie = getLocation();
                event.setLocatie(locatie);
                eventService.updateEvent(event);

                break;
            case "4":
                System.out.println("Enter new omschrijving: ");
                String newOmschrijving = scanner.nextLine();
                event.setOmschrijving(newOmschrijving);
                eventService.updateEvent(event);

                break;
            case "5":
                viewAllEventsFlow();
                break;
            default:
                System.err.println("Invalid number try again \n");
                editEventFlow(id);
                break;
        }


        usersList = getUsersList();
        emailService.sendEventUpdateEmail(usersList, event);

    }


    //--------------------------------------Utility Methods--------------------------//

    private Locatie getLocation(){
        Locatie locatie = null;
        System.out.println("Enter the location of the meeting:");
        System.out.println("0 => QZUID");
        System.out.println("1 => QNOORD");
        System.out.println("2 => ONLINE");
        String locatieString = scanner.nextLine();
        switch (locatieString){
            case "0":
                locatie = Locatie.QZUID;
                break;
            case "1":
                locatie = Locatie.QNOORD;
                break;
            case "2":
                locatie = Locatie.ONLINE;
                break;
            default:
                System.err.println("Invalid number, try again... \n");
                getLocation();
                break;
        }

        return locatie;
    }

    public String[] getUsersList(){
        List<User> users = userService.findAllUsers();
        String[] usersArray = new String[users.size()];
        for( int i = 0; i< users.size(); i++){
            usersArray[i] = users.get(i).getEmail();
        }
        return  usersArray;
    }

    private boolean verifyPassword(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }

}
