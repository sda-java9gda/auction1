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
    private Scanner sc = new Scanner(System.in);
    private static Map<Integer,Auction> auctionMap = new HashMap<>();

    public void addAuction(){
        auction = new Auction();
        setAuctionName();
        setAuctionDescription();
        auction.setSettingUser(this.user);

        auctionMap.put(auction.getId(),auction);
        Integer tmp =auction.getId();
        auction.setId(tmp++);
    }

    private void setAuctionDescription() {
        String name = sc.nextLine();
        AuctionView.giveAuctionDescription();
        auction.setDescription(name);
    }

    private void setAuctionName() {
        String name = sc.nextLine();
        AuctionView.giveAuctionName();
        auction.setName(name);
    }

    public static Map<Integer, Auction> getAuctionMap() {
        return auctionMap;
    }

    public boolean isFinished(Auction auction){
        return (auction.getNumberOfBiddings() == 3);
    }

    public User getWinner(Auction auction){
        return auction.getBiddingUser();
    }
}
