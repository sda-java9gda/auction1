import controllers.AuctionController;
import controllers.UserController;
import exceptions.NoSuchUserException;
import exceptions.WrongPasswordException;
import helpers.FileHelper;
import models.Auction;
import models.TreeNode;
import models.User;
import views.AuctionView;
import views.TreeNodeView;
import views.UserView;

import java.util.HashMap;
import java.util.List;
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
        Map<Integer, Auction> auctions = new HashMap<>();
        final String PATHNAME_USERS = "src/main/resources/users.txt";
        final String PATHNAME_AUCTIONS = "src/main/resources/auctions.txt";
        User user = null;
        TreeNode<Auction> root = new TreeNode<>(0, "Categories");
        TreeNode<Auction> child1 = new TreeNode<>(1, "Computers");
        TreeNode<Auction> child11 = new TreeNode<>(11, "Laptops");
        TreeNode<Auction> child12 = new TreeNode<>(12, "PC");
        TreeNode<Auction> child2 = new TreeNode<>(2, "Cars");
        TreeNode<Auction> child21 = new TreeNode<>(21, "SUVs");
        root.addChild(child1);
        root.addChild(child2);
        child1.addChild(child11);
        child1.addChild(child12);
        child2.addChild(child21);

        while (state != State.EXIT) {
            switch (state) {
                case INIT:
                    users = FileHelper.readFromFile(PATHNAME_USERS);
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
                    user = new User(login, password);
                    state = State.LOGGED_IN;
                    break;
                }

                case LOGGED_IN: {
                    auctions = FileHelper.readFromFileAuction(PATHNAME_AUCTIONS);
                    for (Auction auction:auctions.values()) {
                        root.searchById(auction.getCategoryId()).addAuction(auction);
                    }
                    AuctionController ac = new AuctionController();
                    System.out.println("1 - View all auctions");
                    System.out.println("2 - Find auction");
                    System.out.println("3 - Create an auction");
                    System.out.println("4 - View category tree");
                    System.out.println("5 - View all auctions in category");
                    System.out.println("0 - Quit");

                    answer = sc.nextLine();

                    switch (answer) {
                        case ("1"):
                            AuctionView.viewAllAuctions(auctions);
                            state = State.LOGGED_IN;
                            break;

                        case ("2"):
                            System.out.println("1 - By username");
                            System.out.println("2 - By auction name");
                            System.out.println("3 - By auction price");
                            System.out.println("4 - By auction Id");

                            answer = sc.nextLine();

                            switch (answer) {
                                case ("1"): {
                                    AuctionView.getAuctionByUser();
                                    String line = sc.nextLine();
                                    List<Auction> auctionsListByUser = AuctionController.getAuctionsByUser(line, auctions);
                                    AuctionView.printAuctionsBy(auctionsListByUser);
                                    break;
                                }

                                case ("2"): {
                                    AuctionView.getAuctionByAuctionName();
                                    String line = sc.nextLine();
                                    List<Auction> auctionsListByAuctionName = AuctionController.getAuctionsByAuctionName(line, auctions);
                                    AuctionView.printAuctionsBy(auctionsListByAuctionName);
                                    break;
                                }

                                case ("3"): {
                                    AuctionView.getAuctionByBeginningPrice();
                                    String beginningPrice = sc.nextLine();
                                    AuctionView.getAuctionByEndingPrice();
                                    String endingPrice = sc.nextLine();
                                    List<Auction> auctionsListByPrice =
                                            AuctionController.getAuctionsByPrice(beginningPrice, endingPrice, auctions);
                                    AuctionView.printAuctionsBy(auctionsListByPrice);
                                    break;
                                }

                                case ("4"): {
                                    AuctionView.getAuctionByAuctionId();
                                    String auctionId = sc.nextLine();
                                    System.out.println(AuctionController.getAuctionById(Integer.parseInt(auctionId), auctions));
                                    break;

                                }

                                default: {
                                    System.out.println("Wrong answer!");
                                    break;
                                }
                            }
                            state = State.LOGGED_IN;
                            break;

                        case ("3"): {
                            AuctionView.giveAuctionName();
                            String auctionName = sc.nextLine();

                            AuctionView.giveAuctionDescription();
                            String auctionDescription = sc.nextLine();

                            AuctionView.givePrice();
                            int auctionPrice = Integer.valueOf(sc.nextLine());

                            int categoryId = 0;
                            do {
                                AuctionView.getCategoryId();
                                categoryId = sc.nextInt();
                            } while (!root.searchById(categoryId).isLeaf());

                            Auction auction =
                                    new Auction(auctionName, auctionDescription, user.getLogin(),
                                            auctionPrice, AuctionController.getAuctionNumber(), categoryId);
                            root.searchById(categoryId).addAuction(auction);

                            ac.addAuction(auctions, AuctionController.setAuctionNumber(), auction);
                            FileHelper fileHelper = new FileHelper();
                            String input = fileHelper.toLine(auction);
                            FileHelper.writeToUsersFile(input, PATHNAME_AUCTIONS);
                            state = State.LOGGED_IN;
                            break;
                        }

                        case ("4"): {
                            TreeNodeView tnv = new TreeNodeView();
                            tnv.viewCategories(root);
                            System.out.println();
                            state = State.LOGGED_IN;
                            break;
                        }

                        case ("5"): {
                            AuctionView.getCategoryId();
                            int categoryId = sc.nextInt();
                            String ignored = sc.nextLine();
                            TreeNode<Auction> category = root.searchById(categoryId);
                            AuctionView.viewAuctions(category);
                            state = State.LOGGED_IN;
                            break;
                        }

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
                case EXIT:
                    break;
            }
        }
    }
}