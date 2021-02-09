package model;

import java.util.ArrayList;
import java.util.List;

public class StoreItemList implements ItemList {
    private List<Item> itemList;

    public StoreItemList() {
        itemList = new ArrayList<>();
    }

    @Override
    public void removeItem(Item item) {

    }

    @Override
    public void addItem(Item item) {

    }

    @Override
    public Item getItem(int index) {
        return null;
    }

    @Override
    public int listSize() {
        return 0;
    }
}
