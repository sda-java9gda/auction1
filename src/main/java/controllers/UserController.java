package controllers;

import exceptions.NoSuchUserException;
import models.User;
import views.UserView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserController {
    User user;
    FileController fc = new FileController();
    Scanner sc = new Scanner(System.in);
    private static final String PATHNAME = "src/main/resources/users.txt";
    private Map<String, User> users;

    public Map<String, User> getUsers() {
        return users;
    }

    public UserController() {
//        FileController.writeToUsersFile("",PATHNAME);
        this.users = new HashMap<>();
    }

    public void addUser(String login, String password) {
        user = new User(login, password);
        String input = fc.toLine(user);
        FileController.writeToUsersFile(input, PATHNAME);
    }
//
//    public void setUserLogin(String login) throws LoginUsedException {
//        if (checkUserExist(login)) {
//            throw new LoginUsedException();
//        }
//    }
//
//    public boolean checkUserExist(String login) {
//        if (FileController.checkIfLoginPresent(login, users)) {
//            return true;
//        } else return false;
//    }


    public boolean checkIfLoginPresent(String string, Map<String, User> users){
        for (String login : users.keySet()) {
            if (login.equals(string)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfLoginAndPasswordAreConnected(String login, String password, Map<String, User> users){
        if (checkIfLoginPresent(login, users)){
            return users.get(login).getPassword().equals(password);
        }
        return false;
    }

    public boolean verify(String login, String password, Map<String, User> users) throws NoSuchUserException{
        if (checkIfLoginAndPasswordAreConnected(login, password, users)) {

            return true;

        } else {
            UserView.noSuchUser();
            throw new NoSuchUserException();
        }
    }
}

