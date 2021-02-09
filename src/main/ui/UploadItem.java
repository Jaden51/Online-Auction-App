package ui;

import model.Item;

import java.util.Scanner;

public class UploadItem {
    private Item item;
    private Scanner keyboard = new Scanner(System.in);
    private String itemName;
    private double startingPrice;
    private double minBid;
    private double buyout;

    // EFFECTS: runs the upload item menu
    public UploadItem() {
        itemName = inputItemName();
        startingPrice = inputStartingPrice();
        minBid = inputMinBid();
        buyout = inputBuyout();

        item = new Item(itemName, startingPrice, minBid, buyout);
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
        return keyboard.nextDouble();
    }

    // MODIFIES: this
    // EFFECTS: gets the items minimum bid increments
    private double inputMinBid() {
        System.out.println("Enter minimum bid increments");
        return keyboard.nextDouble();
    }

    // MODIFIES: this
    // EFFECTS: gets the items buyout price
    private double inputBuyout() {
        System.out.println("Enter the items buyout");
        return keyboard.nextDouble();
    }

}
