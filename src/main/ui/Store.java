package ui;

import model.ItemList;

import javax.swing.*;

// General class for both the general auction store and the personal
// user store. Has the methods for item display to make things more clear
// and abstract and a method to show all the items currently in both stores
public abstract class Store {

    protected JButton button;
    protected JList list;
    protected JScrollPane scrollPane;
    protected ItemList userItemList;
    protected ItemList auctionItemList;

    // EFFECTS: initializes all stores and creates the components
    public Store(ItemList userItemList, ItemList auctionItemList, JComponent parent) {
        initializeFields(userItemList, auctionItemList);
        createComponents(parent);
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createComponents(JComponent parent);

    // EFFECTS: updates the lists to reflect JSON files
    public abstract void updateLists(ItemList userItemList, ItemList auctionItemList);

    // MODIFIES: parent
    // EFFECTS: adds the given button to the parent component
    public void addToParentButton(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: initializes fields for the stores
    protected void initializeFields(ItemList userItemList, ItemList auctionItemList) {
        this.userItemList = userItemList;
        this.auctionItemList = auctionItemList;
    }

}
