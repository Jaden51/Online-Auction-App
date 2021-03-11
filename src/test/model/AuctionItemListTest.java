package model;

import org.junit.jupiter.api.BeforeEach;

public class AuctionItemListTest extends ItemListTest{
    @BeforeEach
    void setup() {
        itemList = new AuctionItemList("general");
        item1 = new Item("hat", 100, 10, 1000, Item.NO_BID_PRICE, false);
        item2 = new Item("pants", 200, 1, 500, Item.NO_BID_PRICE, false);
        item3 = new Item("shoes", 50, 5, 200, Item.NO_BID_PRICE, false);
    }
}
