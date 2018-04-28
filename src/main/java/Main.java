import controllers.AuctionController;
import helpers.FileHelper;
import controllers.UserController;
import exceptions.NoSuchUserException;
import exceptions.WrongPasswordException;
import models.Auction;
import models.User;
import views.AuctionView;
import views.UserView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public enum State {
        INIT,
        REGISTER,
        LOGGING_IN,
        LOGGED_IN,
        EXIT
    }

    public static void main(String[] args) {
        State state = State.INIT;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to SDAllegro!");
        UserController uc = new UserController();
        Map<String, User> users = new HashMap<>();
        String PATHNAME = "src/main/resources/users.txt";
        User user = null;

        while (state != State.EXIT) {
            switch (state) {
                case INIT:
                    users = FileHelper.readFromFile(PATHNAME);
                    System.out.println("Pick one:");
                    System.out.println("1 - Log in");
                    System.out.println("2 - Register");
                    System.out.println("0 - Quit");

                    String answer = sc.nextLine();

                    switch (answer) {
                        case ("1"):
                            state = State.LOGGING_IN;
                            break;

                        case ("2"):
                            state = State.REGISTER;
                            break;

                        case ("0"):
                            state = State.EXIT;
                            break;

                        default:
                            System.out.println("Wrong answer!");
                            state = State.INIT;
                            break;
                    }
                    break;

                case REGISTER: {
                    UserView.giveLogin();
                    String login = sc.nextLine().trim();
                    if (uc.checkIfLoginPresent(login, users)) {
                        UserView.userExist();
                        state = State.REGISTER;
                        break;
                    } else {
                        UserView.givePassword();
                        String password = sc.nextLine().trim();
                        User.addUser(login, password);
                        state = State.INIT;
                        break;
                    }
                }

                case LOGGING_IN: {
                    UserView.giveLogin();
                    String login = sc.nextLine();

                    UserView.givePassword();
                    String password = sc.nextLine();

                    try {
                        uc.verify(login, password, users);
                    } catch (NoSuchUserException e) {
                        UserView.noSuchUser();
                        state = State.INIT;
                        break;
                    } catch (WrongPasswordException e) {
                        UserView.wrongPassword();
                        state = State.INIT;
                        break;
                    }
                    user = new User(login,password);
                    state = State.LOGGED_IN;
                    break;
                }

                case LOGGED_IN: {
                    AuctionController ac = new AuctionController();
                    System.out.println("1 - View all auctions");
                    System.out.println("2 - Find auction");
                    System.out.println("3 - Create an auction");
                    System.out.println("0 - Quit");

                    answer = sc.nextLine();

                    switch (answer) {
                        case ("1"):
                            AuctionView.viewAllAuctions();
                            state = State.LOGGED_IN;
                            break;

                        case ("2"):
//                            Auction auction = new Auction();
//                            if (ac.isFinished(auction)) {
//                                ac.getWinner(auction);
//                            }
                            state = State.LOGGED_IN;
                            break;

                        case ("3"):
                            AuctionView.giveAuctionName();
                            String auctionName = sc.nextLine();

                            AuctionView.giveAuctionDescription();
                            String auctionDescription = sc.nextLine();

                            AuctionView.givePrice();
                            int auctionPrice = Integer.valueOf(sc.nextLine());

                            Auction auction = new Auction(auctionName,auctionDescription,user.getLogin(),auctionPrice);
                            ac.addAuction(AuctionController.getAuctionNumber(),auction);
                            state = State.LOGGED_IN;
                            break;

                        case ("0"):
                            state = State.EXIT;
                            break;

                        default:
                            System.out.println("Wrong answer!");
                            state = State.LOGGED_IN;
                            break;
                    }
                    break;
                }
            }
        }
    }
}