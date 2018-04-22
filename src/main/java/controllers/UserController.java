package controllers;

import exceptions.LoginUsedException;
import models.User;
import views.UserView;

import java.util.Scanner;

public class UserController {
    User user;
    Scanner sc = new Scanner(System.in);
    private static final String PATHNAME = "src/main/resources/users.txt";

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
        FileController.writeToUsersFile(PATHNAME);
        return true;
    }

    public void setUserLogin(String login) throws LoginUsedException {
        if (checkUserExist(login)) {
            throw new LoginUsedException();
        }
        FileController.writeToUsersFile(login,PATHNAME);
    }

    public void setUserPassword(String userPassword) {
        user.setPassword(userPassword);
        FileController.writeToUsersFile(userPassword,PATHNAME);
    }

    public boolean checkUserExist(String login) {
        if (FileController.checkIfLoginPresent(login,PATHNAME)) {
            return true;
        } else return false;
    }

    public static boolean verify(String login, String password) {
        if(FileController.checkIfLoginAndPasswordAreConnected(login,password,PATHNAME)){
            return true;
        } else {
            return false;
    }
}
}

