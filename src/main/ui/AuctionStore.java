package ui;

import model.Item;
import model.ItemList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;
import java.util.Scanner;

// Represents the general auction store where all users can view items
// You pick an item based on the index number of said item and can
// than place a bid on that item.
public class AuctionStore extends Store {
    List<Item> itemList;
    private Scanner keyboard;

    // EFFECTS: gets the user item list from the user item list class
    public AuctionStore(ItemList userItemList, ItemList auctionItemList, JComponent parent) {
        super(userItemList, auctionItemList, parent);
        itemList = this.auctionItemList.getList();
        keyboard = new Scanner(System.in);
    }

    protected DefaultListModel toListModel() {
        DefaultListModel listModel = new DefaultListModel();
        for (Item i : auctionItemList.getList()) {
            listModel.addElement(i.getItemName());
        }
        return listModel;
    }

    @Override
    protected void createComponents(JComponent parent) {
        button = new JButton("Place bid");
        button.setEnabled(false);
        list = new JList(toListModel());
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displayItemInfo((String) list.getSelectedValue());
            }
        });
        addToParentLists(parent);
        addToParentButton(parent);
    }

    private void displayItemInfo(String itemName) {
        System.out.println(itemName);
    }

    public void updateJList() {
        list.setModel(toListModel());
    }

    // MODIFIES: this
    // EFFECTS: updates the lists to match the ones from data persistence
    @Override
    public void updateLists(ItemList userItemList, ItemList auctionItemList) {
        this.auctionItemList = auctionItemList;
        this.userItemList = userItemList;
    }

}
