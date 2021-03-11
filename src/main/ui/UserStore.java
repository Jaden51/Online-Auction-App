package ui;

import model.Item;
import model.ItemList;
import model.UserItemList;

import java.io.FileNotFoundException;
import java.util.List;


// Represents the menu for the user store where the user can view their items
public class UserStore extends Store {
    private ItemList userItemList;

    public UserStore(ItemList userItemList, ItemList auctionItemList) {
        this.userItemList = userItemList;
    }

    @Override
    public void showItems() {
        showItems(userItemList.getList());
    }

    @Override
    public void updateLists(ItemList userItemList, ItemList auctionItemList) {
        this.userItemList = userItemList;
    }

}
