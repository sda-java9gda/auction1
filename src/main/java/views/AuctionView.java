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

    public static void viewAllAuctions(Map<Integer,Auction> auctions) {
        for (Integer key : auctions.keySet()) {
            System.out.println(key + " " + auctions.get(key));
        }
    }

    public static void getAuction(List<String> auction) {
        System.out.println(Arrays.asList(auction));
    }

    public static void getAuctionByUser(){
        System.out.println("Entry username");
    }

    public static void getAuctionByAuctionName(){
        System.out.println("Entry Auction name");
    }

    public static void getAuctionByPrice(){
        System.out.println("Entry Auction price");
    }

    public static void givePrice() {
        System.out.println("Auction price.");
    }

    public static void printAuctionsByUser(List<Auction> auctions){
        for (Auction auction: auctions) {
            System.out.println(auction);
        }
    }

    public static void printAuctionsByAuctionName(List<Auction> auctions){
        for (Auction auction: auctions) {
            System.out.println(auction);
        }
    }
}
