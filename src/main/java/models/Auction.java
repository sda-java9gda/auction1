package models;

public class Auction {
    private String name;
    private String description;
    private String settingUser;
    private String biddingUser;
    private int numberOfBiddings = 0;
    private String userLogin;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Auction(String name, String description, String settingUser, Integer price) {
        this.name = name;
        this.description = description;
        this.settingUser = settingUser;
        this.price = price;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSettingUser() {
        return settingUser;
    }

    public void setSettingUser(String settingUser) {
        this.settingUser = settingUser;
    }

    public String getBiddingUser() {
        return biddingUser;
    }

    public void setBiddingUser(String biddingUser) {
        this.biddingUser = biddingUser;
    }

    public int getNumberOfBiddings() {
        return numberOfBiddings;
    }

    public void setNumberOfBiddings(int numberOfBiddings) {
        this.numberOfBiddings = numberOfBiddings;
    }

    @Override
    public String toString() {
        return "Auction " +
                "name:'" + name + '\'' +
                ", description:'" + description + '\'' +
                ", settingUser:" + settingUser +
                ", biddingUser:" + biddingUser +
                ", numberOfBiddings:" + numberOfBiddings +
                ", price:" + price;
    }
}
