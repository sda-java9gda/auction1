package models;

public class Auction {
    private String name;
    private String description;
    private String settingUser;
    private String biddingUser;
    private int numberOfBiddings = 0;
    private int price;
    private Integer auctionId;
    private Integer categoryId;

    public Auction(String name, String description, String settingUser, int price, Integer auctionId, Integer categoryID) {
        this.name = name;
        this.description = description;
        this.settingUser = settingUser;
        this.price = price;
        this.auctionId = auctionId + 1000;
        this.categoryId = categoryID;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public int getPrice() {
        return price;
    }

    public Integer getAuctionId() {
        return auctionId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSettingUser() {
        return settingUser;
    }

    public int getNumberOfBiddings() {
        return numberOfBiddings;
    }

    @Override
    public String toString() {
        return name +
                ", " + description +
                ", settingUser: " + settingUser +
                ", price: " + price +
                ", highest bid: " + ((biddingUser == null) ? "none" : biddingUser);
    }
}
