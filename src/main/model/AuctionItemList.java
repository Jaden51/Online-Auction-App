package model;

// Represents the list of items on the general store of the auction,
// or more generally, all the items that any user can view and access.
// The general auction store adds items when a user makes a new auction
// or remove items when an item gets sold.
public class AuctionItemList extends ItemList {
    // EFFECTS: creates a list for the global auction store
    public AuctionItemList(String username) {
        super(username);
    }
}
