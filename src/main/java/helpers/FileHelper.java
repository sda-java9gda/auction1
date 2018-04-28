package helpers;

import models.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileHelper {
    private static final String SEPARATOR = ";";

    public static void writeToUsersFile(String text, String filePath) {
        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
            writer.print(text + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String toLine(User user) {
        String result = "";
        result += user.getLogin() + SEPARATOR;
        result += user.getPassword();
        return result;
    }

    public static Map<String, User> readFromFile(String filepath) {
        File file = new File(filepath);
        Map<String, User> users = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    break;
                }
                User user = new User(parseEntry(line)[0], parseEntry(line)[1]);
                users.put(user.getLogin(), user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private static String[] parseEntry(String line) {
        return line.split(SEPARATOR);
    }
}
