package views;

import controllers.AuctionController;
import models.Auction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AuctionView {

    public static void giveAuctionName() {
        System.out.println("Enter auction name.");
    }

    public static void giveAuctionDescription() {
        System.out.println("Enter auction details.");
    }

    public static void viewAllAuctions() {
        Map<Integer, Auction> auctions = AuctionController.getAuctionMap();
        for (Auction auction : auctions.values()) {
            System.out.println(auction);
            System.out.println();
        }
    }
    public static void getAuction(List<String> auction) {
        System.out.println(Arrays.asList(auction));
    }

    public static void givePrice() {
        System.out.println("Auction price.");
    }
}
