package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.List;

// Abstract class to represent all the methods common to both the AuctionItemList
// and the UserItemList. The AuctionItemList represents the global store where
// everyone can place bids and view items. The UserItemList represents the personal store
// of each user to view their own items they put up for auction.
public abstract class ItemList implements Writable {
    private List<Item> itemList;
    private String username;

    // EFFECTS: creates an ArrayList to store all the items
    public ItemList(String userName) {
        itemList = new ArrayList<>();
        this.username = userName;
    }

    public String getUsername() {
        return this.username;
    }

    // MODIFIES: this
    // EFFECTS: removes an item from the item list if the status is
    //          set to sold
    public void removeItem(Item item) {
        itemList.remove(item);
    }

    // MODIFIES: this
    // EFFECTS: adds an item from the item list
    public void addItem(Item item) {
        itemList.add(item);
    }

    // REQUIRES: the current item list to not be empty
    // MODIFIES: this
    // EFFECTS: looks in the array to find the item the user wants
    //          to check status of
    public Item getItem(int index) {
        return itemList.get(index);
    }

    // MODIFIES: this
    // EFFECTS: gets the current size of the users store
    public int listSize() {
        return itemList.size();
    }

    // EFFECTS: get the list from the users store
    public List<Item> getList() {
        return itemList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("userStoreItems", itemListToJson());
        return json;
    }

    private JSONArray itemListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : getList()) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }
}
