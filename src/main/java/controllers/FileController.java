package controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileController {
    private static final String SEPARATOR = ";";

    public static void writeToUsersFile(String text, String filePath) {
        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {

            writer.print(text + SEPARATOR);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void writeToUsersFile(String filePath) {
        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
            writer.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkIfLoginPresent(String string,String filePath) {
        File file = new File(filePath);
        List<String> stringFile = readFromFile(file);
        for (String lines : stringFile) {
            if (lines.equals(string)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> readFromFile(File fileName) {
        List<String> list = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while (true){
                line = bufferedReader.readLine();
                if(line == null) {
                    break;
                }
                 list.addAll(Arrays.asList(line.trim().split(SEPARATOR)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static boolean checkIfLoginAndPasswordAreConnected(String login, String password,String filePath){
        File file = new File(filePath);
        List<String> stringFile = readFromFile(file);
        for (String lines : stringFile) {
            if (lines.contains(login) && lines.contains(password))  {
                return true;
            }
        }
        return false;
    }

}
