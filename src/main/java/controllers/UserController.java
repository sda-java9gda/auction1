package controllers;

import model.User;
import views.UserView;

import java.util.Scanner;

public class UserController {
    User user;

    public boolean addUser() {
        Scanner sc = new Scanner(System.in);
        user = new User();
        System.out.println("Podaj imię :");
        user.setName(sc.nextLine());
        System.out.println("Podaj nazwisko :");
        user.setSurname(sc.nextLine());
        System.out.println("Podaj login :");
        String login = sc.nextLine();
//        if (login.equals()) {
//            System.out.println("Login jest zajety");
//            return false;
//        } else {
//            user.setLogin(login);
//            System.out.println("Podaj haslo :");
//            user.setPassword(sc.nextLine());
        return true;
    }
}