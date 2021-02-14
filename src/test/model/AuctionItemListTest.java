package model;

import org.junit.jupiter.api.BeforeEach;

public class AuctionItemListTest extends ItemListTest{
    @BeforeEach
    void setup() {
        itemList = new AuctionItemList();
        item1 = new Item("hat", 100, 10, 1000);
        item2 = new Item("pants", 200, 1, 500);
        item3 = new Item("shoes", 50, 5, 200);
    }
}
