package models;

public class Auction {
    private String name;
    private String description;
    private User settingUser;
    private User biddingUser;
    private int numberOfBiddings = 0;

    public Auction() {
    }

    public Auction(String name, String description, User settingUser) {
        this.name = name;
        this.description = description;
        this.settingUser = settingUser;
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

    public User getSettingUser() {
        return settingUser;
    }

    public void setSettingUser(User settingUser) {
        this.settingUser = settingUser;
    }

    public User getBiddingUser() {
        return biddingUser;
    }

    public void setBiddingUser(User biddingUser) {
        this.biddingUser = biddingUser;
    }

    public int getNumberOfBiddings() {
        return numberOfBiddings;
    }

    public void setNumberOfBiddings(int numberOfBiddings) {
        this.numberOfBiddings = numberOfBiddings;
    }
}
