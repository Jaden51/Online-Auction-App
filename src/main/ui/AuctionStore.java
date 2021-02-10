package ui;

import model.Item;
import model.AuctionItemList;
import model.ItemList;

import java.util.List;

public class AuctionStore extends Store {
    List<Item> itemList;

    // EFFECTS: gets the user item list from the user item list class
    public AuctionStore(ItemList itemList) {
        this.itemList = itemList.getList();
        showItems(this.itemList);
    }

}
