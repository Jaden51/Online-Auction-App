package model;

public interface ItemList {
    // MODIFIES: this
    // EFFECTS: removes an item from the item list
    void removeItem();

    // MODIFIES: this
    // EFFECTS: adds an item from the item list
    void addItem();
}
