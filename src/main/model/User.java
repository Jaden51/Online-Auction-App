package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Item> auctionItems;

    public User() {
        auctionItems = new ArrayList<>();
    }

    public List<Item> getAuctionItems() {
        return auctionItems;
    }

    // MODIFIES: this
    // EFFECTS: if the user puts an item up for auction
    //          add the item to their auction items list.
    public void addItems() {

    }
}
