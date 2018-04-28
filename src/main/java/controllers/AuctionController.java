package controllers;

import models.Auction;
import models.User;
import views.AuctionView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AuctionController {
    private Auction auction;
    private User user;
    private static Map<Integer,Auction> auctionMap = new HashMap<>();
    private static int auctionNumber = 0;

    public synchronized static int getAuctionNumber() {
        auctionNumber++;
        return auctionNumber;
    }

    public static void setAuctionNumber(int auctionNumber) {
        AuctionController.auctionNumber = auctionNumber;
    }

    public void addAuction(Integer number, Auction auction){
        auctionMap.put(number,auction);
    }

    private void setAuctionDescription(String description) {
        AuctionView.giveAuctionDescription();
        auction.setDescription(description);
    }

    private void setAuctionName(String name) {
        AuctionView.giveAuctionName();
        auction.setName(name);
    }

    public static Map<Integer, Auction> getAuctionMap() {
        return auctionMap;
    }

    public boolean isFinished(Auction auction){
        return (auction.getNumberOfBiddings() == 3);
    }

//    public User getWinner(Auction auction){
//        return auction.getBiddingUser();
//    }
}
