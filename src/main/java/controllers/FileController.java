package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileController {
    private static final String PATHNAME = "src/main/resources/";
    private static final String USERS_FILENAME = PATHNAME + "users.txt";
    private static final String SEPARATOR = ";";

    public static void writeToUsersFile(String text) {
        File file = new File(USERS_FILENAME);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
            writer.print(text + SEPARATOR);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeToUsersFile() {
        File file = new File(USERS_FILENAME);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
            writer.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkIfLoginPresent(String string) {
        File file = new File(USERS_FILENAME);
        List<String> stringFile = readFromFile(file);
        for (String lines : stringFile) {
            if (lines.equals(string)) {
                return true;
            } else
                return false;
        }
        return false;
    }

    public static List<String> readFromFile(File fileName) {
        List<String> list = new ArrayList<>();
        File file = new File(USERS_FILENAME);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
