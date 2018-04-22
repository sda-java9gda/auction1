import controllers.AuctionController;
import controllers.UserController;
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
                    System.out.println("Input login");
                    String login = sc.nextLine();

                    System.out.println("Input password");
                    String password = sc.nextLine();


                    if (UserController.verify(login, password)) {
                        state = State.LOGGED_IN;
                    } else {
                        System.out.println("Wrong login or password.");
                        state = State.INIT;
                    }
                    break;
                }

                case LOGGED_IN: {
                    System.out.println("1 - View all auctions");
                    System.out.println("2 - Take a bid");
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
                            state = State.LOGGED_IN;
                            break;

                        case ("3"):
                            AuctionController ac = new AuctionController();
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
