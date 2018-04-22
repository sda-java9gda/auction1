import controllers.AuctionController;
import controllers.UserController;
import exceptions.NoSuchUserException;
import models.Auction;
import models.User;
import views.AuctionView;
import views.UserView;

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

        while (state != State.EXIT) {
            switch (state) {
                case INIT:
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
                    UserController uc = new UserController();

                    UserView.giveLogin();
                    String login = sc.nextLine().trim();
                    UserView.givePassword();
                    String password = sc.nextLine().trim();
                    uc.addUser(login,password);

                    state = State.INIT;
                    break;
                }

                case LOGGING_IN: {
                    UserController uc = new UserController();
                    User user;
                    System.out.println("Input login");
                    String login = sc.nextLine();

                    System.out.println("Input password");
                    String password = sc.nextLine();


                    try {
                        if (uc.verify(login, password)) {
                            user = new User(login,password);
                            state = State.LOGGED_IN;
                        } else {
                            System.out.println("Wrong login or password.");
                            state = State.INIT;
                        }
                    } catch (NoSuchUserException e) {
                        e.printStackTrace();
                    }
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
                            //TODO
                            Auction auction = new Auction();
                            if (ac.isFinished(auction)) {
                                ac.getWinner(auction);
                            }
                            state = State.LOGGED_IN;
                            break;

                        case ("3"):
                            Auction auction = new Auction();
                            ac.addAuction();
                            state = State.LOGGED_IN;
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
                }
            }
        }
    }


}
