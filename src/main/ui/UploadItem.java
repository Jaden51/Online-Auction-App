package ui;

import model.Item;
import model.ItemList;

import java.util.Scanner;

// Menu for a user to upload an item to the store.
// The item uploaded here will show up both in the user store
// and in the auction store.
public class UploadItem extends Store {
    private Item item;
    private Scanner keyboard = new Scanner(System.in);
    private String itemName;
    private double startingPrice;
    private double minBid;
    private double buyout;

    private ItemList userItemList;
    private ItemList auctionItemList;

    // EFFECTS: initializes the item lists
    public UploadItem(ItemList userItemList, ItemList auctionItemList) {
        this.userItemList = userItemList;
        this.auctionItemList = auctionItemList;
    }

    // EFFECTS: displays the items
    @Override
    public void showItems() {
        showItems(userItemList.getList());
    }

    // MODIFIES: this
    // EFFECTS: updates the list to match the data in the data persistence
    @Override
    public void updateLists(ItemList userItemList, ItemList auctionItemList) {
        this.userItemList = userItemList;
        this.auctionItemList = auctionItemList;
    }

    // MODIFIES: this
    // EFFECTS: runs the menu to upload an item
    public void runUploadItem() {
        this.itemName = inputItemName();
        this.startingPrice = inputStartingPrice();
        this.minBid = inputMinBid();
        this.buyout = inputBuyout();

        this.item = new Item(this.itemName, this.startingPrice, this.minBid, this.buyout, Item.NO_BID_PRICE, false);
        userItemList.addItem(this.item);
        auctionItemList.addItem(this.item);

        System.out.println("Your item has been uploaded to the store successfully!");
        displayOneItem(this.item);

    }

    // MODIFIES: this
    // EFFECTS: gets the item name
    private String inputItemName() {
        System.out.println("Enter the items name: ");
        return keyboard.next();
    }

    // MODIFIES: this
    // EFFECTS: gets the item's starting price
    private double inputStartingPrice() {
        System.out.println("Enter starting price: ");
        while (!keyboard.hasNextDouble()) {
            System.out.println("Enter a number value please!");
            keyboard.nextLine();
        }
        return keyboard.nextDouble();
    }

    // MODIFIES: this
    // EFFECTS: gets the items minimum bid increments
    private double inputMinBid() {
        System.out.println("Enter minimum bid increments: ");
        while (!keyboard.hasNextDouble()) {
            System.out.println("Enter a number value please!");
            keyboard.nextLine();
        }
        return keyboard.nextDouble();
    }

    // MODIFIES: this
    // EFFECTS: gets the items buyout price
    private double inputBuyout() {
        System.out.println("Enter the items buyout price: ");
        while (!keyboard.hasNextDouble()) {
            System.out.println("Enter a number value please!");
            keyboard.nextLine();
        }
        double input = keyboard.nextDouble();
        while (input <= this.startingPrice) {
            System.out.println("Enter a buyout greater than the starting price: ");
            input = keyboard.nextDouble();
        }
        return input;
    }



}
