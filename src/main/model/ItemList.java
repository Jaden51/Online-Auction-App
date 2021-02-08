package model;

public interface ItemList {
    // MODIFIES: this
    // EFFECTS: removes an item from the item list if the status is
    //          set to sold
    void removeItem();

    // MODIFIES: this
    // EFFECTS: adds an item from the item list
    void addItem();

    // MODIFIES: this
    // EFFECTS: looks in the array to find the item the user wants
    //          to check status of
    Item getItem(int index);
}
