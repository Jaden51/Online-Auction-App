package ui;

import model.AuctionItemList;
import model.Item;

import java.util.List;
import java.util.Scanner;

public class AuctionStore extends Store {
    AuctionItemList auctionItemList;
    List<Item> itemList;
    Item itemPicked;
    Scanner keyboard = new Scanner(System.in);

    // EFFECTS: gets the user item list from the user item list class
    public AuctionStore(AuctionItemList itemList) {
        this.auctionItemList = itemList;
        this.itemList = itemList.getList();
        showItems(this.itemList);
        pickItem();
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
                    placeBid(i);
                    break;
                }
            } else {
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: if the user wishes, they can bet on the item they previously picked
    private void placeBid(int i) {
        this.itemPicked = itemList.get(i - 1);
        displayOneItem(this.itemPicked);
        double input;

        System.out.println("Place bid: (any other key to quit)");

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

}
