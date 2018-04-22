package models;

public class Auction {
    private String name;
    private String description;
    private User settingUser;
    private User biddingUser;
    private int numberOfBiddings = 0;
    private Integer id = 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Auction() {
    }

    public Auction(String name, String description, User settingUser) {
        this.name = name;
        this.description = description;
        this.settingUser = settingUser;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSettingUser(User settingUser) {
        this.settingUser = settingUser;
    }


    @Override
    public String toString() {
        return "Auction{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", settingUser=" + settingUser +
                ", biddingUser=" + biddingUser +
                ", numberOfBiddings=" + numberOfBiddings +
                ", id=" + id +
                '}';
    }
}
