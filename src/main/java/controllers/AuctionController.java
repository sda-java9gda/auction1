package controllers;

import helpers.FileHelper;
import models.Auction;
import helpers.AuctionByPriceComparator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AuctionController {
    private static final String PATHNAME = "src/main/resources/auctions.txt";
    private static Map<Integer, Auction> auctionMap = new HashMap<>();
    private static int auctionNumber = FileHelper.readBiggestAuctionNumber(PATHNAME);
    private FileHelper fileHelper = new FileHelper();

    public synchronized static int getAuctionNumber() {
        return auctionNumber;
    }

    public static void setAuctionNumber(int auctionNumber) {
        AuctionController.auctionNumber = auctionNumber;
    }

    public synchronized static int setAuctionNumber() {
        auctionNumber++;
        return auctionNumber;
    }

    public void addAuction(Map<Integer, Auction> auctions, Integer number, Auction auction) {
        auctions.put(number, auction);
    }

    public static Map<Integer, Auction> getAuctionMap() {
        return auctionMap;
    }

    public boolean isFinished(Auction auction) {
        return (auction.getNumberOfBiddings() == 3);
    }

    public static List<Auction> getAuctionsByUser(String user, Map<Integer, Auction> auctions) {
        return auctions.values().stream().filter(x -> x.getSettingUser()
                .equals(user)).collect(Collectors.toList());
    }

    public static List<Auction> getAuctionsByAuctionName(String name, Map<Integer, Auction> auctions) {
        return auctions.values().stream().filter(x -> x.getName()
                .equals(name)).collect(Collectors.toList());
    }

    public static List<Auction> getAuctionsByPrice(String beginningPrice, String endPrice, Map<Integer, Auction> auctions) {
        return auctions.values().stream()
                .filter(x -> x.getPrice() >= Integer.valueOf(beginningPrice)
                        && x.getPrice()<=Integer.valueOf(endPrice))
                .sorted(new AuctionByPriceComparator())
                .collect(Collectors.toList());
    }

    public static Auction getAuctionById(Integer auctionId, Map<Integer, Auction> auctions){
        return auctions.get(auctionId-999);
    }
}
