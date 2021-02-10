package ui;

import model.Item;
import model.UserItemList;

import java.util.Scanner;

public class UploadItem {
    private Item item;
    private Scanner keyboard = new Scanner(System.in);
    private String itemName;
    private double startingPrice;
    private double minBid;
    private double buyout;

    // EFFECTS: runs the upload item menu
    public UploadItem(UserItemList itemList) {
        this.itemName = inputItemName();
        this.startingPrice = inputStartingPrice();
        this.minBid = inputMinBid();
        this.buyout = inputBuyout();

        this.item = new Item(this.itemName, this.startingPrice, this.minBid, this.buyout);
        itemList.addItem(this.item);
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
