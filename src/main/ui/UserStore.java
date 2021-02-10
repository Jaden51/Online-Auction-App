package ui;

import model.Item;
import model.UserItemList;

import java.util.List;

public class UserStore {
    List<Item> itemList;

    // EFFECTS: gets the user item list from the user item list class
    public UserStore(UserItemList itemList) {
        this.itemList = itemList.getList();
        showItems();
    }

    // MODIFIES: this
    // EFFECTS: shows the items the user is currently putting up for auction
    private void showItems() {
        String formatDouble = "%s%-15.2f";
        String formatString = "%s%-15s";
        if (itemList.size() == 0) {
            System.out.println("You currently have no items");
        } else {
            for (Item i : this.itemList) {
                System.out.printf(formatString, "Item Name: ", i.getItemName());
                System.out.printf(formatDouble, "Starting price: ", i.getStartingPrice());

                if (i.getCurrentBid() == -1) {
                    System.out.printf(formatString, "Current bid: ", "No bids");
                } else {
                    System.out.printf(formatDouble, "Current bid: ", i.getCurrentBid());
                }

                System.out.printf(formatDouble, "Minimum bid: ", i.getMinBid());
                System.out.printf(formatDouble, "Buyout: ", i.getBuyout());
                System.out.println();
            }
        }
    }

}
