import controllers.AuctionController;
import controllers.FileController;
import controllers.UserController;
import exceptions.NoSuchUserException;
import exceptions.WrongPasswordException;
import models.Auction;
import models.User;
import views.AuctionView;
import views.UserView;

import java.util.*;
import java.util.stream.Collectors;

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
        Map<Integer, Auction> auctions = new HashMap<>();
        String PATHNAMEUSERS = "src/main/resources/users.txt";
        String PATHNAMEAUCTIONS = "src/main/resources/auctions.txt";
        User user = null;

        while (state != State.EXIT) {
            switch (state) {
                case INIT:
                    users = FileController.readFromFile(PATHNAMEUSERS);
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
                        uc.addUser(login, password);
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
                        state = State.LOGGING_IN;
                        break;
                    } catch (WrongPasswordException e) {
                        UserView.wrongPassword();
                        state = State.LOGGING_IN;
                        break;
                    }
                    user = new User(login, password);
                    state = State.LOGGED_IN;
                    break;
                }

                case LOGGED_IN: {
                    auctions = FileController.readFromFileAuction(PATHNAMEAUCTIONS);
                    AuctionController ac = new AuctionController();
                    System.out.println("1 - View all auctions");
                    System.out.println("2 - Find auction");
                    System.out.println("3 - Create an auction");
                    System.out.println("0 - Quit");

                    answer = sc.nextLine();

                    switch (answer) {
                        case ("1"):
                            AuctionView.viewAllAuctions(auctions);
                            state = State.LOGGED_IN;
                            break;

                        case ("2"):

                            System.out.println("1 - By username");
                            System.out.println("2 - By Auction name");
                            System.out.println("3 - By Auction price");

                            answer = sc.nextLine();

                            switch (answer) {

                                String line;

                                case ("1"):
                                    AuctionView.getAuctionByUser();
                                    line = sc.nextLine();
                                    List<Auction> auctionsListByUser = AuctionController.getAuctionsByUser(line, auctions);
                                    AuctionView.printAuctionsByUser(auctionsListByUser);
                                    break;

                                case ("2"):
                                    AuctionView.getAuctionByAuctionName();
                                    line = sc.nextLine();
                                    List<Auction> auctionsListByAuctionName = AuctionController.getAuctionsByAuctionName(line,auctions);
                                    AuctionView.printAuctionsByAuctionName(auctionsListByAuctionName);
                                    break;
                                case ("3"):
                                    AuctionView.getAuctionByPrice();
                                    line = sc.nextLine();
                                    List<Auction> auctionsListByPrice = AuctionController.getAuctionsByAuctionName(line,auctions);
                                    AuctionView.printAuctionsByAuctionName(auctionsListByAuctionName);
                                    break;
                            }


//                            AuctionView.getAuctionByUser();


//                            List<Auction> list = auctions.values().stream().filter(x -> x.getSettingUser()
//                                    .equals(line)).collect(Collectors.toList());
//                            System.out.println(list);

                            state = State.LOGGED_IN;
                            break;

                        case ("3"):
                            AuctionView.giveAuctionName();
                            String auctionName = sc.nextLine();

                            AuctionView.giveAuctionDescription();
                            String auctionDescription = sc.nextLine();

                            AuctionView.givePrice();
                            int auctionPrice = Integer.valueOf(sc.nextLine());

                            Auction auction = new Auction(auctionName, auctionDescription, user.getLogin(), auctionPrice);
                            ac.addAuction(AuctionController.setAuctionNumber(), auction);
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