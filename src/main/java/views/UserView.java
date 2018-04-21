package views;


import models.User;

import java.util.List;


public class UserView {

    public static void printUserName(User user) {
        System.out.println("Masz na imię: " + user.getName());
    }

    public static void printUserSurname(User user) {
        System.out.println("Twoje nazwisko to: " + user.getSurname());
    }

    public static void printUserLogin(User user) {
        System.out.println("Witaj " + user.getLogin());
    }


    public static void userExist() {
        System.out.println("Login jest zajety!");
    }


    public static void printAllUsers(List<User> users) {
        for (User user : users) {
            System.out.println(user.getLogin());
        }
    }

    public static void giveName() {
        System.out.println("Podaj imie");
    }

    public static void giveSurname() {
        System.out.println("Podaj nazwisko");
    }

    public static void giveLogin() {
        System.out.println("Podaj login");
    }

    public static void givePassword() {
        System.out.println("Podaj haslo");
    }


}

