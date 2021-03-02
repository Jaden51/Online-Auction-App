package ui;

import model.AuctionItemList;
import model.Item;
import model.UserItemList;

import java.util.Scanner;

// The main page of the auction app. This class initializes the
// data and shows the main menu. The menu displayed here
// can lead users to the other parts of the app
public class AuctionApp {
    protected UserItemList userItemList;
    private AuctionItemList storeItemList;
    private Scanner keyboard;

    // EFFECTS: runs the auction store
    public AuctionApp() {
        runActionApp();
    }

    // MODIFIES: this
    // EFFECTS: process user inputs
    // Code inspiration from Teller-App
    private void runActionApp() {
        boolean run = true;
        String input;

        initialize();

        while (run) {
            menu();
            input = keyboard.next().toLowerCase();

            if (input.equals("q")) {
                run = false;
            } else {
                processInput(input);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes fields
    private void initialize() {
        keyboard = new Scanner(System.in);
        userItemList = new UserItemList("user");
        storeItemList = new AuctionItemList("general");

        addItemsAlreadyInStore();
    }

    // EFFECTS: displays the stores menu
    private void menu() {
        System.out.println("\nSelect: ");
        System.out.println("p -> Place item on auction store");
        System.out.println("s -> Search store");
        System.out.println("v -> View your items");
        System.out.println("q -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: read the user input to bring up new menus if needed
    private void processInput(String input) {
        switch (input) {
            case "p":
                new UploadItem(userItemList, storeItemList);
                break;
            case "v":
                new Store(userItemList);
                break;
            case "s":
                new Store(storeItemList);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds items to the auction store to begin to app so the user
    //          can bid on pre-existing items
    private void addItemsAlreadyInStore() {
        Item item1 = new Item("Mike Trout Rookie Card", 1000, 100, 100000);
        Item item2 = new Item("Worn out nike pants", 10, 1, 100);
        Item item3 = new Item("Chicago Cubs hat", 20, 3, 200);

        item1.setFirstBid();
        item1.increaseBid(1000);
        item3.setFirstBid();

        storeItemList.addItem(item1);
        storeItemList.addItem(item2);
        storeItemList.addItem(item3);
    }

}
