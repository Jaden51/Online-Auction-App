package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserItemListTest {
    private UserItemList itemList;
    private Item item1;
    private Item item2;
    private Item item3;

    @BeforeEach
    void setup() {
        itemList = new UserItemList();
        item1 = new Item("hat", 100, 10, 1000);
        item2 = new Item("pants", 200, 1, 500);
        item3 = new Item("shoes", 50, 5, 200);
    }

    @Test
    void testAddItemEmptyList() {
        itemList.addItem(item1);
        assertEquals(1, itemList.listSize());
    }

    @Test
    void testAddItemMultiple() {
        itemList.addItem(item1);
        itemList.addItem(item2);
        itemList.addItem(item3);
        assertEquals(3, itemList.listSize());
    }

    @Test
    void testRemoveOneItem() {
        itemList.addItem(item1);
        itemList.removeItem(item1);
        assertEquals(0, itemList.listSize());
    }

    @Test
    void testRemoveMultipleItems() {
        itemList.addItem(item1);
        itemList.addItem(item2);
        itemList.addItem(item3);

        itemList.removeItem(item1);
        itemList.removeItem(item3);

        assertEquals(1, itemList.listSize());
    }

    @Test
    void testGetItem() {
        itemList.addItem(item1);
        itemList.addItem(item2);
        itemList.addItem(item3);

        assertEquals(item1, itemList.getItem(0));
        assertEquals(item3, itemList.getItem(2));
        assertEquals(3, itemList.listSize());
    }

}
