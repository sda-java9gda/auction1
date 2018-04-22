package controllers;

import models.User;
import views.UserView;

import java.io.File;
import java.util.Scanner;

public class UserController {
    User user;
    Scanner sc = new Scanner(System.in);
    private static final String PATHNAME = "src/main/resources/users.txt";

    public boolean addUser() {
        user = new User();
        setUserLogin();
        setUserName();
        setUserSurname();
        FileController.writeToUsersFile();
        return true;
    }

    public void setUserName() {
        UserView.giveName();
        String name = sc.nextLine();
        user.setName(name);
        FileController.writeToUsersFile(name, new File(PATHNAME));
    }

    public void setUserSurname() {
        UserView.giveSurname();
        String surname = sc.nextLine();
        user.setSurname(surname);
        FileController.writeToUsersFile(surname,new File(PATHNAME));
    }

    public void setUserLogin() {
        UserView.giveLogin();
        String login = sc.nextLine();
        while (checkUserExist(login)) {
            UserView.userExist();
            login = sc.nextLine();
        }
        FileController.writeToUsersFile(login,new File(PATHNAME));
        user.setLogin(login);
        setUserPassword();

    }

    public void setUserPassword() {
        UserView.givePassword();
        String password = sc.nextLine();
        user.setPassword(password);
        FileController.writeToUsersFile(password,new File(PATHNAME));
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
