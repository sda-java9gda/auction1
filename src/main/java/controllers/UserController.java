package controllers;

import exceptions.NoSuchUserException;
import exceptions.WrongPasswordException;
import models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserController {
    private User user;
    private FileController fc = new FileController();
    Scanner sc = new Scanner(System.in);
    private static final String PATHNAME = "src/main/resources/users.txt";
    private Map<String, User> users = new HashMap<>();
    public Map<String, User> getUsers() {
        return users;
    }

    public void addUser(String login, String password) {
        user = new User(login, password);

        //to nie jest kontroler - w userze
        String input = fc.toLine(user);
        FileController.writeToUsersFile(input, PATHNAME);
    }

    public boolean checkIfLoginPresent(String string, Map<String, User> users) {

        //contains
        for (String login : users.keySet()) {
            if (login.equals(string)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfLoginAndPasswordAreConnected(String login, String password, Map<String, User> users) {
        return users.get(login).getPassword().equals(password);
    }

    public void verify(String login, String password, Map<String, User> users) throws NoSuchUserException, WrongPasswordException {

        //zmienic na jeden wyjatek, zeby nie bylo widac, ze konto istnieje
       if(!checkIfLoginPresent(login,users)){
           throw new NoSuchUserException();
       }
       if (!checkIfLoginAndPasswordAreConnected(login, password, users)) {
            throw new WrongPasswordException();
        }
    }
}