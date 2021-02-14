package ui;

import model.Item;
import model.AuctionItemList;
import model.UserItemList;

import java.util.List;

// General class for both the general auction store and the personal
// user store. Has the methods for item display to make things more clear
// and abstract and a method to show all the items currently in both stores
public class Store {

    public Store(UserItemList itemList) {
        new UserStore(itemList);
    }

    public Store(AuctionItemList itemList) {
        new AuctionStore(itemList);
    }

    public Store() {}

    // MODIFIES: this
    // EFFECTS: shows the items the user is currently putting up for auction
    protected void showItems(List<Item> itemList) {
        int index = 1;
        if (itemList.size() == 0) {
            System.out.println("No items found");
        } else {
            for (Item i : itemList) {
                System.out.print(index + ". ");
                displayOneItem(i);
                index++;
            }
        }
    }

    // EFFECTS: menu display for one item
    protected void displayOneItem(Item i) {
        String formatDouble = "%s%-15.2f";
        String formatString = "%s%-15s";

        System.out.printf(formatString, "Item Name: ", i.getItemName());
        System.out.println();
        System.out.printf(formatDouble, "Starting price: $", i.getStartingPrice());

        if (i.getCurrentBid() == -1) {
            System.out.printf(formatString, "Current bid: ", "No bids");
        } else {
            System.out.printf(formatDouble, "Current bid: $", i.getCurrentBid());
        }

        System.out.printf(formatDouble, "Minimum bid: $", i.getMinBid());
        System.out.printf(formatDouble, "Buyout: $", i.getBuyout());
        System.out.println();
    }

}
