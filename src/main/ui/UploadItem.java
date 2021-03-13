package ui;

import model.Item;
import model.ItemList;

import javax.swing.*;

// Menu for a user to upload an item to the store.
// The item uploaded here will show up both in the user store
// and in the auction store.
public class UploadItem extends Store {
    private Item item;

    private JTextField itemNameField;
    private JTextField startingPriceField;
    private JTextField minBidField;
    private JTextField buyoutField;

    // EFFECTS: initializes the item lists
    public UploadItem(ItemList userItemList, ItemList auctionItemList, JComponent parent) {
        super(userItemList, auctionItemList, parent);
    }

    // MODIFIES: this, parent
    // EFFECTS: creates the button to upload items and adds listener
    @Override
    protected void createComponents(JComponent parent) {
        button = new JButton("Upload Item");
        button.addActionListener(e -> uploadItem(parent));
        createTextFields(parent);
        addToParentButton(parent);
    }

    // MODIFIES: this, parent
    // EFFECTS: creates the text fields for the user to enter an item
    protected void createTextFields(JComponent parent) {
        itemNameField = new JTextField(15);
        itemNameField.setText("Item Name...");

        startingPriceField = new JTextField(15);
        startingPriceField.setText("Starting price...");

        minBidField = new JTextField(15);
        minBidField.setText("Min bid increments...");

        buyoutField = new JTextField(15);
        buyoutField.setText("Buyout...");

        addToParentFields(parent);
    }

    // MODIFIES: parent
    // EFFECTS: adds components to the parent JPanel
    public void addToParentFields(JComponent parent) {
        parent.add(itemNameField);
        parent.add(startingPriceField);
        parent.add(minBidField);
        parent.add(buyoutField);
    }

    // MODIFIES: this
    // EFFECTS: updates the list to match the data in the data persistence
    @Override
    public void updateLists(ItemList userItemList, ItemList auctionItemList) {
        this.userItemList = userItemList;
        this.auctionItemList = auctionItemList;
    }

    // MODIFIES: this, parent
    // EFFECTS: uploads the item to the user store and the auction store
    public void uploadItem(JComponent parent) {
        String itemNameText = itemNameField.getText();
        double startingPriceText = Double.parseDouble(startingPriceField.getText());
        double minBidText = Double.parseDouble(minBidField.getText());
        double buyoutText = Double.parseDouble(buyoutField.getText());

        item = new Item(itemNameText, startingPriceText, minBidText, buyoutText, Item.NO_BID_PRICE, false);
        userItemList.addItem(item);
        auctionItemList.addItem(item);

        JOptionPane.showMessageDialog(parent, "Item uploaded successfully!");
    }

}
