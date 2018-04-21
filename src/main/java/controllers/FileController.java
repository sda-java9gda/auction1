package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class FileController {
    private static File users = new File("users.txt");

    public static void writeToUsersFile(String text) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(users, true))) {
            writer.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
