package controllers;

import models.User;
import views.UserView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserController {
    User user;
    Scanner sc = new Scanner(System.in);

    private List<User> userList = new ArrayList<>();

    public boolean addUser() {
        user = new User();

        while (setUserLogin()) {
            setUserLogin();
        }
        setUserName();
        setUserSurname();
        userList.add(user);
        return true;
    }

    public void setUserName() {
        String name = sc.nextLine();
        UserView.giveName();
        user.setName(name);
        FileController.writeToUsersFile(name);
    }

    public void setUserSurname() {
        String surname = sc.nextLine();
        UserView.giveSurname();
        user.setSurname(surname);
        FileController.writeToUsersFile(surname);
    }

    public boolean setUserLogin() {

        UserView.giveLogin();
        String login = sc.nextLine();
        if (checkUserExist(login)) {
            return true;
        } else {
            user.setLogin(login);
            setUserPassword();
            FileController.writeToUsersFile(login);
            return false;
        }
    }

    public void setUserPassword() {
        String password = sc.nextLine();
        UserView.givePassword();
        user.setPassword(password);
        FileController.writeToUsersFile(password);
    }


    public boolean checkUserExist(String login) {
        if (FileController.checkIfLoginPresent(login)) {
            UserView.userExist();
            return true;
        } else {
            user.setName(login);
            return false;
        }

        //Sprawdzenie z userList
//        for (User user : userList) {
//            if (user.getLogin().equals(login)) {
//                UserView.userExist();
//                return true;
//            } else {
//                user.setName(login);
//                return false;
//            }
//        }
//        return false;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}