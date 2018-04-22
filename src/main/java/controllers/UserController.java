package controllers;

import exceptions.LoginUsedException;
import models.User;
import views.UserView;

import java.util.Scanner;

public class UserController {
    User user;
    Scanner sc = new Scanner(System.in);

    public boolean addUser(String login, String password) {
        user = new User(login,password);
        try {
            setUserLogin(login);
            FileController.writeToUsersFile(login);
        } catch (LoginUsedException e) {
            UserView.userExist();
            return false;
        }
        setUserPassword(password);
        FileController.writeToUsersFile();
        return true;
    }

    public void setUserLogin(String login) throws LoginUsedException {

        while (checkUserExist(login)) {
            throw new LoginUsedException();
        }

    }

    public void setUserPassword(String userPassword) {
        UserView.givePassword();
        user.setPassword(userPassword);
        FileController.writeToUsersFile(userPassword);
    }

    public boolean checkUserExist(String login) {
        if (FileController.checkIfLoginPresent(login)) {
            return true;
        } else return false;
    }

    public static boolean verify(String login, String password) {
        //TODO
        return true;
    }
}
