package ui;

import model.StoreItemList;
import model.UserItemList;

import java.util.Scanner;

public class AuctionApp {
    private UserItemList userItemList;
    private StoreItemList storeItemList;
    private Scanner keyboard;

    public AuctionApp() {
        runActionApp();
    }

    private void runActionApp() {
        boolean run = true;
        String input;

        System.out.println("Welcome to Auction App!");

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

    private void initialize() {
        userItemList = new UserItemList();
        storeItemList = new StoreItemList();
        keyboard = new Scanner(System.in);
    }

    private void menu() {
        System.out.println("\nSelect: ");
        System.out.println("p -> Place item on auction store");
        System.out.println("s -> Search store");
        System.out.println("v -> View your items");
        System.out.println("q -> Quit");
    }

    private void processInput(String input) {
        switch (input) {
            case "p":
                System.out.println("Bringing up place item menu");
                break;
            case "s":
                System.out.println("Bringing store");
                break;
            case "v":
                System.out.println("Bringing up your items");
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

}
