package ui;

import model.Item;
import model.ItemList;

import javax.swing.*;

// Represents the menu for the user store where the user can view their items
public class UserStore extends Store {

    // EFFECTS: initializes the user list
    public UserStore(ItemList userItemList, ItemList auctionItemList, JComponent parent) {
        super(userItemList, auctionItemList, parent);
    }

    // EFFECTS: turns lists into lists model to be used by components
    protected DefaultListModel toListModel() {
        DefaultListModel listModel = new DefaultListModel();
        for (Item i : userItemList.getList()) {
            listModel.addElement(i.getItemName());
        }

        return listModel;
    }

    public void updateJList() {
        list.setModel(toListModel());
    }

    @Override
    protected void createComponents(JComponent parent) {
        list = new JList(toListModel());
        addToParentLists(parent);
    }

    // EFFECTS: updates the users items based on JSON data
    @Override
    public void updateLists(ItemList userItemList, ItemList auctionItemList) {
        this.userItemList = userItemList;
        this.auctionItemList = auctionItemList;
    }

}
