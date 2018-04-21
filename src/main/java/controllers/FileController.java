package controllers;

import views.UserView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileController {
    private static File users = new File("users.txt");

    public static void writeToUsersFile(String text) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(users, true))) {
            writer.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static boolean checkIfLoginPresent(String string){
        List<String> stringFile = readFromFile(users);
        for (String lines: stringFile) {
            if (lines.equals(string)) {
                return true;
            } else
                return false;
        }
        return false;

        }

    public static List<String> readFromFile(File fileName) {
        List<String> list = new ArrayList<String>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(users);
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
