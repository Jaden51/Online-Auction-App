package model;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemList {
    private List<Item> itemList;

    public ItemList() {
        itemList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: removes an item from the item list if the status is
    //          set to sold
    public void removeItem(Item item) {
        itemList.remove(item);
    }

    // MODIFIES: this
    // EFFECTS: adds an item from the item list
    public void addItem(Item item) {
        itemList.add(item);
    }

    // MODIFIES: this
    // EFFECTS: looks in the array to find the item the user wants
    //          to check status of
    public Item getItem(int index) {
        return itemList.get(index);
    }

    // MODIFIES: this
    // EFFECTS: gets the current size of the users store
    public int listSize() {
        return itemList.size();
    }

    // EFFECTS: get the list from the users store
    public List<Item> getList() {
        return itemList;
    }
}
