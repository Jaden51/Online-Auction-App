package ui;

import model.Item;
import model.ItemList;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.IOException;
import java.util.Scanner;

import java.util.List;

// Represents the menu for the user store where the user can view their items
public class UserStore extends Store {
    private static final String JSON_STORE = "./data/userStore.json";
    private ItemList userItemList;
    private List<Item> itemList;
    private Scanner keyboard;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: gets the user item list from the user item list class
    public UserStore(ItemList itemList) {
        userItemList = itemList;
        this.itemList = itemList.getList();
        if (!showItems(this.itemList)) {
            loadItemsMenu();
        }
    }

    private void loadItemsMenu() {
        String input;
        keyboard = new Scanner(System.in);
        System.out.println("Would you like to load your items (y/n)");
        input = keyboard.next();
        input = input.toLowerCase();

        if (input.equals("y")) {
            loadItems();
        }
    }

    private void loadItems() {
        try {
            userItemList = jsonReader.read();
            System.out.println("Loaded " + userItemList.getUsername() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
