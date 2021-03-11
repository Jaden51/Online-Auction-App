package ui;

import model.AuctionItemList;
import model.UserItemList;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.util.Scanner;

// The main page of the auction app. This class initializes the
// data and shows the main menu. The menu displayed here
// can lead users to the other parts of the app
public class AuctionApp {
    UploadItem uploadItem;
    Store userStore;
    Store auctionStore;

    private UserItemList userItemList;
    private AuctionItemList auctionItemList;

    private Scanner keyboard;

    protected static final String JSON_USER_STORE = "./data/userStore.json";
    protected static final String JSON_AUCTION_STORE = "./data/auctionStore.json";

    protected JsonWriter jsonWriterUserStore;
    protected JsonReader jsonReaderUserStore;
    protected JsonWriter jsonWriterAuctionStore;
    protected JsonReader jsonReaderAuctionStore;

    // EFFECTS: runs the app
    public AuctionApp() {
        initializeJson();
        runActionApp();
    }

    // MODIFIES: this
    // EFFECTS: process user inputs
    // Code inspiration from Teller-App
    public void runActionApp() {
        boolean run = true;
        String input;

        initializeFields();

        while (run) {
            menu();
            updateLists();
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
    private void initializeFields() {
        keyboard = new Scanner(System.in);
        userItemList = new UserItemList("jh51");
        loadItemsAuctionStore();

        userStore = new UserStore(userItemList, auctionItemList);
        auctionStore = new AuctionStore(userItemList, auctionItemList);
        uploadItem = new UploadItem(userItemList, auctionItemList);

    }

    // MODIFIES: this
    // EFFECTS: initializes the JSON objects
    private void initializeJson() {
        jsonWriterUserStore = new JsonWriter(JSON_USER_STORE);
        jsonReaderUserStore = new JsonReader(JSON_USER_STORE);
        jsonWriterAuctionStore = new JsonWriter(JSON_AUCTION_STORE);
        jsonReaderAuctionStore = new JsonReader(JSON_AUCTION_STORE);
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
                uploadItem.runUploadItem();
                saveItemsAuctionStore();
                break;
            case "s":
                auctionStore.showItems();
                saveItemsAuctionStore();
                break;
            case "v":
                userStore.showItems();
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
    protected void loadItemsAuctionStore() {
        try {
            auctionItemList = jsonReaderAuctionStore.readAuctionList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to read from file: " + JSON_AUCTION_STORE);
        }
    }

    // EFFECTS: saves the workroom to file
    protected void saveItemsAuctionStore() {
        try {
            jsonWriterAuctionStore.open();
            jsonWriterAuctionStore.write(auctionItemList);
            jsonWriterAuctionStore.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_AUCTION_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the user store from file
    protected void loadItemsUserStore() {
        try {
            userItemList = jsonReaderUserStore.readUserList();
            System.out.println("Loaded " + userItemList.getUsername() + " from " + JSON_USER_STORE);
        } catch (Exception e) {
            System.out.println("Unable to read from file: " + JSON_USER_STORE);
        }
    }

    // EFFECTS: saves the workroom to file
    protected void saveItemsUserStore() {
        try {
            jsonWriterUserStore.open();
            jsonWriterUserStore.write(userItemList);
            jsonWriterUserStore.close();
            System.out.println("Saved " + userItemList.getUsername() + " to " + JSON_USER_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_USER_STORE);
        }
    }

    // EFFECTS: updates the lists in the stores to match the data persistance
    public void updateLists() {
        userStore.updateLists(userItemList, auctionItemList);
        uploadItem.updateLists(userItemList, auctionItemList);
        auctionStore.updateLists(userItemList, auctionItemList);
    }


}
