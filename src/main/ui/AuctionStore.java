package ui;

import model.Item;
import model.ItemList;

import javax.swing.*;

// Represents the general auction store where all users can view items
// You pick an item based on the index number of said item and can
// than place a bid on that item.
public class AuctionStore extends Store {

    private JLabel nameLabel;
    private JLabel startingPriceLabel;
    private JLabel currentBidLabel;
    private JLabel minBidLabel;
    private JLabel buyoutLabel;

    private JTextField placeBidField;

    // EFFECTS: gets the user item list from the user item list class
    public AuctionStore(ItemList userItemList, ItemList auctionItemList, JComponent parent) {
        super(userItemList, auctionItemList, parent);
    }

    // EFFECTS: creates a list model to be used by the JList
    protected DefaultListModel toListModel() {
        DefaultListModel listModel = new DefaultListModel();
        for (Item i : auctionItemList.getList()) {
            listModel.addElement(i.getItemName());
        }
        return listModel;
    }

    // MODIFIES: this, parent
    // EFFECTS: creates the component relative to the store
    @Override
    protected void createComponents(JComponent parent) {
        list = new JList(toListModel());
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displayItemInfo((String) list.getSelectedValue());
            }
        });

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        parent.add(scrollPane);

        createLabels(parent);

        button = new JButton("Place bid");
        button.setEnabled(false);
        addToParentButton(parent);
    }

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

    // MODIFIES: this, parent
    // EFFECTS: displays the item information when the user selects it
    //          enables the place bid button
    private void displayItemInfo(String itemName) {
        for (Item i : auctionItemList.getList()) {
            if (itemName == i.getItemName()) {
                nameLabel.setText("Item Name: " + i.getItemName());
                startingPriceLabel.setText("Starting price: $" + i.getStartingPrice());
                currentBidLabel.setText("Current bid: $" + i.getCurrentBid());
                minBidLabel.setText("Minimum bid: $" + i.getMinBid());
                buyoutLabel.setText("Buyout: $" + i.getBuyout());
            }
        }
    }

    // MODIFIES: this, parent
    // EFFECTS: updates the JList component when user updates the stores
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
