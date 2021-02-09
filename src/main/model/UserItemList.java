package model;

import java.util.ArrayList;
import java.util.List;

public class UserItemList implements ItemList {
    private List<Item> itemList;

    public UserItemList() {
        itemList = new ArrayList<>();
    }

    @Override
    public void removeItem(Item item) {
        itemList.remove(item);
    }

    @Override
    public void addItem(Item item) {
        itemList.add(item);
    }

    @Override
    public Item getItem(int index) {
        return itemList.get(index);
    }

    @Override
    public int listSize() {
        return itemList.size();
    }

}
