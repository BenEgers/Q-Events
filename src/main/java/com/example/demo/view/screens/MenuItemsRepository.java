package com.example.demo.view.screens;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class MenuItemsRepository {

    private final List<String> mainMenuItems = new ArrayList<>();

    private final List<String> dashboardMenuItems = new ArrayList<>();

    private final List<String> accountMenuItems = new ArrayList<>();

    private final List<String> editEventMenuItems = new ArrayList<>();

    {
        mainMenuItems.add("1. Login");
        mainMenuItems.add("2. Sign Up");
        mainMenuItems.add("0. Exit");
    }

    {
        dashboardMenuItems.add("1. Create new event");
        dashboardMenuItems.add("2. View users in ur organization");
        dashboardMenuItems.add("3. View all Events");
        dashboardMenuItems.add("4. Edit your info");
        dashboardMenuItems.add("0. Log out");
    }

    {
       accountMenuItems.add("1. Edit your name");
       accountMenuItems.add("2. Edit your password");
       accountMenuItems.add("0. Go Back");
    }
    {
        editEventMenuItems.add("1. Edit title");
        editEventMenuItems.add("2. Edit datum");
        editEventMenuItems.add("3. Edit locatie");
        editEventMenuItems.add("4. Edit omschrijving");
        editEventMenuItems.add("5. Go back");
    }
}
