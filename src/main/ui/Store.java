package ui;

import model.Item;
import model.ItemList;

import javax.swing.*;

// General class for both the general auction store and the personal
// user store. Has the methods for item display to make things more clear
// and abstract and a method to show all the items currently in both stores
public abstract class Store {

    protected Item itemSelected;
    protected ItemList userItemList;
    protected ItemList auctionItemList;

    protected JButton button;
    protected JList list;
    protected JScrollPane scrollPane;

    protected JLabel nameLabel;
    protected JLabel startingPriceLabel;
    protected JLabel currentBidLabel;
    protected JLabel minBidLabel;
    protected JLabel buyoutLabel;

    // EFFECTS: initializes all stores and creates the components
    public Store(ItemList userItemList, ItemList auctionItemList, JComponent parent) {
        initializeFields(userItemList, auctionItemList);
        createComponents(parent);
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createComponents(JComponent parent);

    // EFFECTS: updates the lists to reflect JSON files
    public abstract void updateLists(ItemList userItemList, ItemList auctionItemList);

    // EFFECTS: initializes fields for the stores
    protected void initializeFields(ItemList userItemList, ItemList auctionItemList) {
        this.userItemList = userItemList;
        this.auctionItemList = auctionItemList;
    }

    // MODIFIES: this, parent
    // EFFECTS: clears labels when switching tabs
    public void updateLabels() {
        nameLabel.setText("");
        startingPriceLabel.setText("");
        currentBidLabel.setText("");
        minBidLabel.setText("");
        buyoutLabel.setText("");
    }

    // MODIFIES: this, parent
    // EFFECTS: creates labels to display item information
    protected void createLabels(JComponent parent) {
        nameLabel = new JLabel();
        parent.add(nameLabel);

        startingPriceLabel = new JLabel();
        parent.add(startingPriceLabel);

        currentBidLabel = new JLabel();
        parent.add(currentBidLabel);

        minBidLabel = new JLabel();
        parent.add(minBidLabel);

        buyoutLabel = new JLabel();
        parent.add(buyoutLabel);
    }

    // MODIFIES: parent
    // EFFECTS: updates labels to show item information when items selected
    protected void displayItemInfo(String itemName, ItemList itemList) {
        for (Item i : itemList.getList()) {
            if (itemName == i.getItemName()) {
                nameLabel.setText("Item Name: " + i.getItemName());
                startingPriceLabel.setText("Starting price: $" + i.getStartingPrice());
                if (i.getCurrentBid() == Item.NO_BID_PRICE) {
                    currentBidLabel.setText("Current bid: No bids");
                } else {
                    currentBidLabel.setText("Current bid: $" + i.getCurrentBid());
                }
                minBidLabel.setText("Minimum bid: $" + i.getMinBid());
                buyoutLabel.setText("Buyout: $" + i.getBuyout());
                itemSelected = i;
            }
        }
    }

    // EFFECTS: turns lists into DefaultListModel to be used by components
    protected DefaultListModel toListModel(ItemList itemList) {
        DefaultListModel listModel = new DefaultListModel();
        for (Item i : itemList.getList()) {
            listModel.addElement(i.getItemName());
        }

        return listModel;
    }

}
