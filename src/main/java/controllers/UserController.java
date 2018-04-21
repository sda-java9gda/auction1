package controllers;

import models.User;
import views.UserView;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserController {
    User user;
    Scanner sc = new Scanner(System.in);

    private List<User> userList = new ArrayList<User>();

    public boolean addUser() {
        user = new User();
        
        while (giveUserLogin()) {
            giveUserLogin();
        }

        giveUserName();
        giveUserSurname();
        userList.add(user);
        return true;
    }

    public void giveUserName() {
        String name = sc.nextLine();
        UserView.giveName();
        user.setName(name);
        FileController.writeToUsersFile(name);
    }

    public void giveUserSurname() {
        String surname = sc.nextLine();
        UserView.giveSurname();
        user.setSurname(surname);
        FileController.writeToUsersFile(surname);
    }

    public boolean giveUserLogin() {
        UserView.giveLogin();
        String login = sc.nextLine();
        if (checkUserExist(login)) {
            return true;

        } else {
            user.setLogin(login);
            giveUserPassword();
            FileController.writeToUsersFile(login);
            return false;
        }
    }

    public void giveUserPassword() {
        String password = sc.nextLine();
        UserView.givePassword();
        user.setPassword(password);
        FileController.writeToUsersFile(password);
    }


    public boolean checkUserExist(String login) {
        for (User user : userList) {
            if (user.getLogin().equals(login)) {
                UserView.userExist();
                return true;
            } else {
                user.setName(login);
                return false;
            }
        }
        return false;
    }


}