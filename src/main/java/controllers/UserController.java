package controllers;

import models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserController {
    User user;

    private List<User> userList = new ArrayList<User>();

    public boolean addUser() {
        Scanner sc = new Scanner(System.in);
        user = new User();
        userList.add(user);
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


//    public User getUserFromFileByLogin(List<String> list, User user){
//        readFromFile()
//        for (String lists: list) {
//            if(user.getLogin().toLowerCase().equals(lists)){
//                return user;
//            }
//        }
//    }

    public List<String> readFromFile(String fileName){
        List<String> list = new ArrayList<String>();
        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}