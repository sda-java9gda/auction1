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

    private List<User> userList = new ArrayList<User>();

    public boolean addUser() {
        Scanner sc = new Scanner(System.in);
        user = new User();
        userList.add(user);
        UserView.giveName();
        user.setName(sc.nextLine());
        UserView.giveSurname();
        user.setSurname(sc.nextLine());

        UserView.giveLogin();
        String login = sc.nextLine();
        if (checkUserExist(login)) {
            return false;
        } else {
            checkUserExist(login);
            UserView.givePassword();
            user.setPassword(sc.nextLine());
            return true;
        }
    }

    public boolean checkUserExist(String login) {
        if (login.equals(userList)) {
            UserView.userExist();
            return true;
        } else {
            user.setName(login);
            return false;
        }
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