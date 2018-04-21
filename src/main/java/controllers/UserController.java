package controllers;

import models.User;
import views.UserView;

import java.util.Scanner;

public class UserController {
    User user;
    Scanner sc = new Scanner(System.in);

    public boolean addUser() {
        user = new User();
        setUserLogin();
        while (checkUserExist(user.getLogin())) {
            setUserLogin();
        }
        setUserName();
        setUserSurname();
        FileController.writeToUsersFile();
        return true;
    }

    public void setUserName() {
        UserView.giveName();
        String name = sc.nextLine();
        user.setName(name);
        FileController.writeToUsersFile(name);
    }

    public void setUserSurname() {
        UserView.giveSurname();
        String surname = sc.nextLine();
        user.setSurname(surname);
        FileController.writeToUsersFile(surname);
    }

    public void setUserLogin() {
        UserView.giveLogin();
        String login = sc.nextLine();
        if (checkUserExist(login)) {
        } else {
            user.setLogin(login);
            FileController.writeToUsersFile(login);
            setUserPassword();
        }
    }

    public void setUserPassword() {
        UserView.givePassword();
        String password = sc.nextLine();
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
    }
}