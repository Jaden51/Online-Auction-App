package ui;

import model.Item;
import model.ItemList;

import java.util.List;

public class UserStore extends Store {
    List<Item> itemList;

    // EFFECTS: gets the user item list from the user item list class
    public UserStore(ItemList itemList) {
        this.itemList = itemList.getList();
        showItems(this.itemList);
    }

}
