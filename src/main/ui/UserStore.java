package ui;

import model.ItemList;

import javax.swing.*;

// Represents the menu for the user store where the user can view their items
public class UserStore extends Store {

    // EFFECTS: initializes the user list
    public UserStore(ItemList userItemList, ItemList auctionItemList, JComponent parent) {
        super(userItemList, auctionItemList, parent);
    }

    // MODIFIES: this, parent
    // EFFECTS: creates the components relative to the user store
    @Override
    protected void createComponents(JComponent parent) {
        list = new JList(toListModel(userItemList));
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displayItemInfo((String) list.getSelectedValue(), userItemList);
            }
        });
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        parent.add(scrollPane);
        createLabels(parent);
    }

    // MODIFIES: this, parent
    // EFFECTS: updates the JList to always display updated information
    public void updateJList() {
        list.setModel(toListModel(userItemList));
    }

    // EFFECTS: updates the users items based on JSON data
    @Override
    public void updateLists(ItemList userItemList, ItemList auctionItemList) {
        this.userItemList = userItemList;
        this.auctionItemList = auctionItemList;
        updateJList();
    }

}
