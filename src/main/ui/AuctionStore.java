package ui;

import model.AuctionItemList;
import model.Item;
import model.ItemList;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

// Represents the general auction store where all users can view items
// You pick an item based on the index number of said item and can
// than place a bid on that item.
public class AuctionStore extends Store {
    List<Item> itemList;
    Item itemPicked;
    private Scanner keyboard;

    private ItemList auctionItemList;
    private ItemList userItemList;

    // EFFECTS: gets the user item list from the user item list class
    public AuctionStore(ItemList userItemList, ItemList auctionItemList) {
        this.auctionItemList = auctionItemList;
        this.itemList = auctionItemList.getList();
        keyboard = new Scanner(System.in);
    }

    @Override
    public void updateLists(ItemList userItemList, ItemList auctionItemList) {
        this.auctionItemList = auctionItemList;
        this.userItemList = userItemList;
    }

    // EFFECTS: process user input
    private void pickItem() {
        System.out.println("Pick an item number you wish to bid on (any other key to quit): ");
        int i;

        while (true) {
            String input = keyboard.next();
            if (checkInput(input)) {
                i = Integer.parseInt(input);
                if (i > itemList.size()) {
                    System.out.println("Item not found, please select again (any other key to quit): ");
                } else {
                    placeBidMenu(i);
                    break;
                }
            } else {
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: if the user wishes, they can bet on the item they previously picked
    private void placeBidMenu(int i) {
        this.itemPicked = itemList.get(i - 1);
        displayOneItem(this.itemPicked);
        char firstBidInput;

        if (this.itemPicked.getCurrentBid() == Item.NO_BID_PRICE) {
            System.out.println("Would you like to place the first bid (y/n): ");
            firstBidInput = Character.toLowerCase(keyboard.next().charAt(0));
            while (firstBidInput != 'y' && firstBidInput != 'n') {
                System.out.println("Would you like to place the first bid (y/n): ");
                firstBidInput = Character.toLowerCase(keyboard.next().charAt(0));
            }
            if (firstBidInput == 'y') {
                this.itemPicked.setFirstBid();
                System.out.println("First bid placed on " + this.itemPicked.getItemName());
            }
        } else {
            placeBid();
        }
    }

    // MODIFIES: this
    // EFFECTS: Takes the item previously selected by the user
    //          and than the user can place a bid on that item.
    //          Includes error checking
    private void placeBid() {
        double input;
        System.out.println("Place bid: ");
        while (!keyboard.hasNextDouble()) {
            System.out.println("Enter a number value please!");
            keyboard.nextLine();
        }

        while (true) {
            input = keyboard.nextDouble();
            if (input < this.itemPicked.getMinBid()) {
                System.out.println("Please enter a value greater than the minimum bid!");
            } else {
                break;
            }
        }
        this.itemPicked.increaseBid(input);
        if (this.itemPicked.isSold()) {
            auctionItemList.removeItem(this.itemPicked);
            System.out.println("Congratulations on your purchase!");
        } else {
            System.out.println("Bid placed on " + this.itemPicked.getItemName());
        }
    }

    // EFFECTS: error check method so the app doesn't crash no matter
    //          the user input
    private static boolean checkInput(String input) {
        try {
            int i = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    // EFFECTS: shows the items in the general store
    @Override
    public void showItems() {
        showItems(this.itemList);
        pickItem();
    }

}
