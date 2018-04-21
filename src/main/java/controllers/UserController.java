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

    private List<User> userList = new ArrayList<>();

    public boolean addUser() {
        while (addUserComplete == true) {
            user = new User();
            setUserName();
            setUserSurname();
            setUserLogin();
        }
        userList.add(user);
        return addUserComplete;
    }

    public void setUserName() {
        UserView.giveName();
        user.setName(sc.nextLine());
    }

    public void setUserSurname() {
        UserView.giveSurname();
        user.setSurname(sc.nextLine());
    }

    public void setUserLogin() {
        UserView.giveLogin();
        String login = sc.nextLine();
        if (checkUserExist(login)) {
            addUserComplete = false;

        } else {
            user.setLogin(login);
            setUserPassword();

        }
    }

    public void setUserPassword() {
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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}