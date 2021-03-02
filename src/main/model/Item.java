package model;

import org.json.JSONObject;
import persistance.Writable;

// Represents an item currently in the auction app ecosystem.
// It has all the features a regular item up for auction would have
// like, its name, starting price, the minimum bid increments and the
// buyout price. When a user places a bid on the item, the bid increases as expected.
public class Item implements Writable {
    public static final int NO_BID_PRICE = -1;

    private String itemName;
    private double startingPrice;
    private double minBid;
    private double buyout;
    private double currentBid;
    private boolean sold;

    // REQUIRES: itemName of non 0 length, starting price > 0, minBid > 0, buyout > 0
    // EFFECTS: sets the name of the item; the price of the item;
    //          the minimum bid increase to place on the item;
    //          and the buy out of the item
    public Item(String itemName, double startingPrice, double minBid, double buyout) {
        this.itemName = itemName;
        this.startingPrice = startingPrice;
        this.currentBid = NO_BID_PRICE;
        this.minBid = minBid;
        this.buyout = buyout;

        this.sold = false;
    }

    // REQUIRES: amount >= minBid
    // MODIFIES: this
    // EFFECTS: increases the current bid of the item.
    //          if the amount is greater than the buyout than the item is sold
    //          if the bid increases (bid amount >= minBid), increase the bid and return true
    //          otherwise return false
    public boolean increaseBid(double amount) {
        if (amount >= minBid) {
            this.currentBid += amount;
            if (this.currentBid >= buyout) {
                this.sold = true;
            }
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: sets the first bid to the starting price amount
    public void setFirstBid() {
        this.currentBid = this.startingPrice;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("itemName", itemName);
        json.put("startingPrice", startingPrice);
        json.put("minBid", minBid);
        json.put("buyout", buyout);
        json.put("currentBid", currentBid);
        json.put("sold", sold);

        return json;
    }

    public String getItemName() {
        return this.itemName;
    }

    public double getStartingPrice() {
        return this.startingPrice;
    }

    public double getCurrentBid() {
        return this.currentBid;
    }

    public double getMinBid() {
        return this.minBid;
    }

    public double getBuyout() {
        return this.buyout;
    }

    public boolean isSold() {
        return this.sold;
    }

}
