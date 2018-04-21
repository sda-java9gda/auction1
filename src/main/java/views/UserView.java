package views;


import models.User;


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

    public static void userExist(){        System.out.println("Login jest zajety!"); }

//    public static void printAllUsers(List<User> users){
//        for (user : users) {
//            System.out.println(user);
//
//        }
}