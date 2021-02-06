package model;

import ui.AuctionApp;

public class Item {
    private String itemName;
    private double startingPrice;
    private double minBid;
    private double buyOut;
    private double currentBid;

    private int id;
    private boolean sold;

    // REQUIRES: itemName of non 0 length
    // EFFECTS: sets the name of the item; the price of the item;
    //          the minimum bid increase to place on the item;
    //          and the buy out of the item
    public Item(String itemName, double startingPrice, double minBid, double buyOut) {
        this.itemName = itemName;
        this.startingPrice = startingPrice;
        this.currentBid = startingPrice;
        this.minBid = minBid;
        this.buyOut = buyOut;

        this.sold = false;
    }

    public double getCurrentBid() {
        return this.currentBid;
    }

    public double getMinBid() {
        return this.minBid;
    }

    public double getBuyOut() {
        return this.buyOut;
    }

    public double getItemId() {
        return this.id;
    }

    // REQUIRES: amount >= minBid
    // MODIFIES: this
    // EFFECTS: increases the current bid of the item.
    //          if the amount is greater than the buyout
    //          than the item is sold
    public void increaseBid(double amount) {

    }

    // MODIFIES: this
    // EFFECTS: changes the sold status of the item from
    //          false to true and removes the item from the sellers
    //          and buyers item lists
    public void sellItem() {
        this.sold = true;
    }

}
