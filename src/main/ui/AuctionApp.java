package ui;

import model.StoreItemList;
import model.UserItemList;

import java.util.Scanner;

public class AuctionApp {
    protected UserItemList userItemList;
    private StoreItemList storeItemList;
    private Scanner keyboard;

    // EFFECTS: runs the auction store
    public AuctionApp() {
        runActionApp();
    }

    // MODIFIES: this
    // process user inputs
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
        userItemList = new UserItemList();
        storeItemList = new StoreItemList();
        keyboard = new Scanner(System.in);
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
                new UploadItem(userItemList);
                break;
            case "s":
                new Store();
                break;
            case "v":
                new UserStore(userItemList);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

}
