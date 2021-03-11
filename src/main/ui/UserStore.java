package ui;

import model.Item;
import model.ItemList;
import model.UserItemList;

import java.io.FileNotFoundException;
import java.util.List;


// Represents the menu for the user store where the user can view their items
public class UserStore extends Store {
    private ItemList userItemList;

    // EFFECTS: initializes the user list
    public UserStore(ItemList userItemList, ItemList auctionItemList) {
        this.userItemList = userItemList;
    }

    // EFFECTS: displays the users items
    @Override
    public void showItems() {
        showItems(userItemList.getList());
    }

    // EFFECTS: updates the users items based on JSON data
    @Override
    public void updateLists(ItemList userItemList, ItemList auctionItemList) {
        this.userItemList = userItemList;
    }

}
