package ui;

import model.AuctionItemList;
import model.Item;
import model.UserItemList;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.util.Scanner;

// The main page of the auction app. This class initializes the
// data and shows the main menu. The menu displayed here
// can lead users to the other parts of the app
public class AuctionApp extends Store {
    private static final String JSON_USER_STORE = "./data/userStore.json";
    private static final String JSON_AUCTION_STORE = "./data/auctionStore.json";
    private JsonWriter jsonWriterUserStore;
    private JsonReader jsonReaderUserStore;
    private JsonWriter jsonWriterAuctionStore;
    private JsonReader jsonReaderAuctionStore;
    protected UserItemList userItemList;
    protected AuctionItemList storeItemList;
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
        loadItemsAuctionStore();

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
        jsonWriterUserStore = new JsonWriter(JSON_USER_STORE);
        jsonReaderUserStore = new JsonReader(JSON_USER_STORE);
        jsonWriterAuctionStore = new JsonWriter(JSON_AUCTION_STORE);
        jsonReaderAuctionStore = new JsonReader(JSON_AUCTION_STORE);
        keyboard = new Scanner(System.in);
        userItemList = new UserItemList("jh51");
        storeItemList = new AuctionItemList("general");
    }

    // EFFECTS: displays the stores menu
    private void menu() {
        System.out.println("\nSelect: ");
        System.out.println("p -> Place item on auction store");
        System.out.println("s -> Search store");
        System.out.println("v -> View your items");
        System.out.println("l -> Load your items");
        System.out.println("k -> Save your items");
        System.out.println("q -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: read the user input to bring up new menus if needed
    private void processInput(String input) {
        switch (input) {
            case "p":
                new UploadItem(userItemList, storeItemList);
                saveItemsAuctionStore();
                break;
            case "s":
                new Store(storeItemList);
                break;
            case "v":
                new Store(userItemList);
                break;
            case "l":
                loadItemsUserStore();
                break;
            case "k":
                saveItemsUserStore();
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the user store from file
    private void loadItemsUserStore() {
        try {
            userItemList = jsonReaderUserStore.readUserList();
            System.out.println("Loaded " + userItemList.getUsername() + " from " + JSON_USER_STORE);
        } catch (Exception e) {
            System.out.println("Unable to read from file: " + JSON_USER_STORE);
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveItemsUserStore() {
        try {
            jsonWriterUserStore.open();
            jsonWriterUserStore.write(userItemList);
            jsonWriterUserStore.close();
            System.out.println("Saved " + userItemList.getUsername() + " to " + JSON_USER_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_USER_STORE);
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveItemsAuctionStore() {
        try {
            jsonWriterAuctionStore.open();
            jsonWriterAuctionStore.write(storeItemList);
            jsonWriterAuctionStore.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_AUCTION_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the user store from file
    private void loadItemsAuctionStore() {
        try {
            storeItemList = jsonReaderAuctionStore.readAuctionList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to read from file: " + JSON_AUCTION_STORE);
        }
    }


}
