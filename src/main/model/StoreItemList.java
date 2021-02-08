package model;

import java.util.ArrayList;
import java.util.List;

public class StoreItemList implements ItemList {
    private List<Item> itemList;

    public StoreItemList() {
        itemList = new ArrayList<>();
    }

    @Override
    public void removeItem() {

    }

    @Override
    public void addItem() {

    }

    @Override
    public Item getItem(int index) {
        return null;
    }
}
