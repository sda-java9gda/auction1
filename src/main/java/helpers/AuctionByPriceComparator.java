package helpers;

import models.Auction;

import java.util.Comparator;

public class AuctionByPriceComparator implements Comparator<Auction> {

    @Override
    public int compare(Auction o1, Auction o2) {
        if(o1.getPrice()>o2.getPrice()) {
            return +1;
        }
        else if (o1.getPrice()==o2.getPrice()) {
            return 0;
        }
        else return -1;
    }
}
