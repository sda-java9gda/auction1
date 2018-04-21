package controler;

import model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserControler {
    private List<User> userList = new ArrayList<User>();

    public void addUser (User user){
        userList.add(user);
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
