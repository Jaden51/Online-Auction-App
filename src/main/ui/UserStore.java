package ui;

import model.Item;
import model.ItemList;

import java.util.List;

// Represents the menu for the user store where the user can view their items
public class UserStore extends Store {
    List<Item> itemList;

    // EFFECTS: gets the user item list from the user item list class
    public UserStore(ItemList itemList) {
        this.itemList = itemList.getList();
        showItems(this.itemList);
    }

}
