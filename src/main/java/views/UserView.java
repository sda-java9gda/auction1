package views;


import models.User;

import java.util.List;


public class UserView {

    public static void printUserLogin(User user) {
        System.out.println("Hello " + user.getLogin());
    }


    public static void userExist() {
        System.out.println("Login is taken! Please try again.");
    }


    public static void printAllUsers(List<User> users) {
        for (User user : users) {
            System.out.println(user.getLogin());
        }
    }
    public static void noSuchUser(){
        System.out.println("No such user or wrong login/password"); }

    public static void giveLogin() {
        System.out.println("Enter your login");
    }

    public static void givePassword() {
        System.out.println("Enter your password");
    }


}

