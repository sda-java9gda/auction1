package controllers;

import exceptions.LoginUsedException;
import models.User;
import views.UserView;

import java.util.Scanner;

public class UserController {
    User user;
    Scanner sc = new Scanner(System.in);
    private static final String PATHNAME = "src/main/resources/users.txt";

<<<<<<< HEAD
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
=======
    public boolean addUser() {
        user = new User();
        setUserLogin();
        setUserName();
        setUserSurname();
        FileController.writeToUsersFile(PATHNAME);
        return true;
    }

    public void setUserName() {
        UserView.giveName();
        String name = sc.nextLine();
        user.setName(name);
        FileController.writeToUsersFile(name, PATHNAME);
    }

    public void setUserSurname() {
        UserView.giveSurname();
        String surname = sc.nextLine();
        user.setSurname(surname);
        FileController.writeToUsersFile(surname,PATHNAME);
    }
>>>>>>> dd2d02b3782f33590782f35885618c7af95f2eef

        while (checkUserExist(login)) {
            throw new LoginUsedException();
        }
<<<<<<< HEAD
=======
        FileController.writeToUsersFile(login,PATHNAME);
        user.setLogin(login);
        setUserPassword();
>>>>>>> dd2d02b3782f33590782f35885618c7af95f2eef

    }

    public void setUserPassword(String userPassword) {
        UserView.givePassword();
<<<<<<< HEAD
        user.setPassword(userPassword);
        FileController.writeToUsersFile(userPassword);
=======
        String password = sc.nextLine();
        user.setPassword(password);
        FileController.writeToUsersFile(password,PATHNAME);
>>>>>>> dd2d02b3782f33590782f35885618c7af95f2eef
    }

    public boolean checkUserExist(String login) {
        if (FileController.checkIfLoginPresent(login,PATHNAME)) {
            return true;
        } else return false;
    }

    public static boolean verify(String login, String password) {
        //TODO
        return true;
    }
}
