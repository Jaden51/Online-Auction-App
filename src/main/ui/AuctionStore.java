package ui;

import model.Item;
import model.ItemList;

import javax.swing.*;

// Represents the general auction store where all users can view items
// You pick an item based on the index number of said item and can
// than place a bid on that item.
public class AuctionStore extends Store {

    private JTextField placeBidField;

    // EFFECTS: gets the user item list from the user item list class
    public AuctionStore(ItemList userItemList, ItemList auctionItemList, JComponent parent) {
        super(userItemList, auctionItemList, parent);
    }

    // MODIFIES: this, parent
    // EFFECTS: creates the component relative to the store
    @Override
    protected void createComponents(JComponent parent) {
        list = new JList(toListModel(auctionItemList));
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displayItemInfo((String) list.getSelectedValue(), auctionItemList);
            }
        });

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        parent.add(scrollPane);

        createLabels(parent);

        placeBidField = new JTextField();
        parent.add(placeBidField);

        button = new JButton("Place bid");
        button.addActionListener(e -> placeBid(parent));
        parent.add(button);
    }

    // MODIFIES: this, parent
    // EFFECTS: places bid on an item, removes it if it gets sold
    //          and updates all JFrame components
    private void placeBid(JComponent parent) {
        try {
            double bid = Double.parseDouble(placeBidField.getText());
            if (bid < itemSelected.getMinBid()) {
                throw new ToLowBidException();
            }

            if (itemSelected.getCurrentBid() == Item.NO_BID_PRICE) {
                itemSelected.increaseBid(bid + 1);
            } else {
                itemSelected.increaseBid(bid);
            }
            checkSold(parent);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Please enter a number!");
        } catch (ToLowBidException e) {
            JOptionPane.showMessageDialog(parent, "Enter bid higher than minimum bid!");
        }
    }

    // MODIFIES: this, parent
    // EFFECTS: checks if the item is sold and modifies the GUI and lists accordingly
    //          if not sold, show the user if the bid was placed successfully
    private void checkSold(JComponent parent) {
        if (itemSelected.isSold()) {
            auctionItemList.removeItem(itemSelected);
            userItemList.removeItem(itemSelected);
            JOptionPane.showMessageDialog(parent, "Congratulations on your purchase!");
            updateJList();
            scrollPane.setViewportView(list);
            updateLabels();
        } else {
            JOptionPane.showMessageDialog(parent, "Bid placed on " + itemSelected.getItemName() + "!");
            currentBidLabel.setText("Current bid: $" + itemSelected.getCurrentBid());
        }
    }

    // MODIFIES: this, parent
    // EFFECTS: updates the JList component when user updates the stores
    public void updateJList() {
        list.setModel(toListModel(auctionItemList));
    }

    // MODIFIES: parent
    // EFFECTS: clears the text field from bids when user switches tabs
    public void clearTextFields() {
        placeBidField.setText("");
    }

    // MODIFIES: this
    // EFFECTS: updates the lists to match the ones from data persistence
    @Override
    public void updateLists(ItemList userItemList, ItemList auctionItemList) {
        this.auctionItemList = auctionItemList;
        this.userItemList = userItemList;
        updateJList();
    }

    private class ToLowBidException extends Exception {
    }

}
