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
        while (addUserComplete == true) {
            user = new User();
            giveUserName();
            giveUserSurname();
            giveUserLogin();
        }
        userList.add(user);
        return addUserComplete;
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

    public void giveUserLogin() {
        UserView.giveLogin();
        String login = sc.nextLine();
        if (checkUserExist(login)) {
            addUserComplete = false;

        } else {
            user.setLogin(login);
            giveUserPassword();

        }
        FileController.writeToUsersFile(login);
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


    //    public User getUserFromFileByLogin(List<String> list, User user){
//        readFromFile()
//        for (String lists: list) {
//            if(user.getLogin().toLowerCase().equals(lists)){
//                return user;
//            }
//        }
//    }

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