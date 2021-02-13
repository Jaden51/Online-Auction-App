package ui;

import model.Item;
import model.ItemList;

import java.util.List;
import java.util.Scanner;

public class AuctionStore extends Store {
    List<Item> itemList;
    Item itemPicked;
    Scanner keyboard = new Scanner(System.in);

    // EFFECTS: gets the user item list from the user item list class
    public AuctionStore(ItemList itemList) {
        this.itemList = itemList.getList();
        showItems(this.itemList);
        pickItem();
    }

    // EFFECTS: process user input
    private void pickItem() {
        System.out.println("Pick an item number you wish to bid on (any other key to quit): ");
        String input = keyboard.next();
        int i;

        if (checkInput(input)) {
            i = Integer.parseInt(input);
            placeBidMenu(i);
        }
    }

    // MODIFIES: this
    // EFFECTS: if the user wishes, they can bet on the item they previously picked
    private void placeBidMenu(int i) {
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

    }

    // EFFECTS: error check method so the app doesn't crash if the user
    //          inputs a string or a integer
    private static boolean checkInput(String input) {
        try {
            int i = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
