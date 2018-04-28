package controllers;

import models.Auction;
import models.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileController {
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

    public String toLine(Auction auction) {
        String result = "";
        result += AuctionController.getAuctionNumber() + SEPARATOR;
        result += auction.getName() + SEPARATOR;
        result += auction.getDescription() + SEPARATOR;
        result += auction.getSettingUser() + SEPARATOR;
        result += auction.getPrice();
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

    public static Map<Integer, Auction> readFromFileAuction(String filepath) {
        File file = new File(filepath);
        Map<Integer, Auction> auctions = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    break;
                }
                Auction auction = new Auction(parseEntry(line)[1], parseEntry(line)[2],
                        parseEntry(line)[3],Integer.valueOf(parseEntry(line)[4]));
                auctions.put(Integer.parseInt(parseEntry(line)[0]),auction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return auctions;
    }

    public static Integer readBiggestAuctionNumber(String filepath) {
        File file = new File(filepath);
        int biggestNumber = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    break;
                }
                biggestNumber = Integer.valueOf(parseEntry(line)[0]);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return biggestNumber;
    }

    public static String[] parseEntry(String line) {
        return line.split(SEPARATOR);
    }
}
