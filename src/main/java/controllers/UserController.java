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
    boolean addUserComplete = true;

    private List<User> userList = new ArrayList<User>();

    public boolean addUser() {
        user = new User();
        while (giveUserLogin()) {
            giveUserLogin();
            UserView.userExist();
        }
        giveUserName();
        giveUserSurname();
        userList.add(user);
        return true;
    }

    public void giveUserName() {
        UserView.giveName();
        user.setName(sc.nextLine());
    }

    public void giveUserSurname() {
        UserView.giveSurname();
        user.setSurname(sc.nextLine());
    }

    public boolean giveUserLogin() {
        UserView.giveLogin();
        String login = sc.nextLine();
        if (checkUserExist(login)) {
            return true;

        } else {
            user.setLogin(login);
            giveUserPassword();
            return false;
        }
    }

    public void giveUserPassword() {
        UserView.givePassword();
        user.setPassword(sc.nextLine());
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

    public List<String> readFromFile(String fileName) {
        List<String> list = new ArrayList<String>();
        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}