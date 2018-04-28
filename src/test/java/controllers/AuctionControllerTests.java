package controllers;

import models.Auction;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AuctionControllerTests {
    AuctionController ac = new AuctionController();
    Auction auction = new Auction("Name","Description","User",1,5345345);
    Map<Integer, Auction> auctions = new HashMap<>();

    @Test
    public void shouldNotBeEmpty(){
        ac.addAuction(auctions,1,auction);

        assertThat(auctions).isNotEmpty();
    }

    @Test
    public void shouldAddAuction(){
        ac.addAuction(auctions,1,auction);

        Auction actual = auctions.get(1);

        assertThat(actual).isEqualTo(auction);
    }

//    @Test
//    public void shouldGetAuctionsByUser(){
//        User user1 = new User("","");
//        User user2 = new User("","");
//        Auction auction1 = new Auction("Auction1","1",user1,1);
//        ac.addAuction(auctions,1,auction1);
//        ac.addAuction(auctions,2,auction2);
//        ac.addAuction(auctions,3,auction3);
//
//        AuctionController.getAuctionsByUser()
//    }
}