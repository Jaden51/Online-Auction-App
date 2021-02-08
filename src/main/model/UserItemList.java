package model;

import java.util.ArrayList;
import java.util.List;

public class UserItemList implements ItemList {
    private List<Item> itemList;

    public UserItemList() {
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
