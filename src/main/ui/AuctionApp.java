package ui;

import model.AuctionItemList;
import model.Item;
import model.ItemList;
import model.UserItemList;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.util.Scanner;

// The main page of the auction app. This class initializes the
// data and shows the main menu. The menu displayed here
// can lead users to the other parts of the app
public class AuctionApp extends Store {
    private static final String JSON_STORE = "./data/userStore.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
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
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        keyboard = new Scanner(System.in);
        userItemList = new UserItemList("jh51");
        storeItemList = new AuctionItemList("general");
        addItemsAlreadyInStore();
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
                break;
            case "s":
                new Store(storeItemList);
                break;
            case "v":
                new Store(userItemList);
                break;
            case "l":
                loadItems();
                break;
            case "k":
                saveItems();
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

    // MODIFIES: this
    // EFFECTS: loads the user store from file
    private void loadItems() {
        try {
            userItemList = (UserItemList) jsonReader.read();
            System.out.println("Loaded " + userItemList.getUsername() + " from " + JSON_STORE);
        } catch (Exception e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveItems() {
        try {
            jsonWriter.open();
            jsonWriter.write(userItemList);
            jsonWriter.close();
            System.out.println("Saved " + userItemList.getUsername() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


}
