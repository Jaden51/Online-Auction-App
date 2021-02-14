package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class ItemListTest {
    ItemList itemList;
    Item item1;
    Item item2;
    Item item3;

    abstract void setup();

    @Test
    void testAddItemEmptyList() {
        itemList.addItem(item1);
        assertEquals(1, itemList.listSize());
    }

    @Test
    void testAddItemMultiple() {
        addItemsToTestList();
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
        addItemsToTestList();

        itemList.removeItem(item1);
        itemList.removeItem(item3);

        assertEquals(1, itemList.listSize());
    }

    @Test
    void testGetItem() {
        addItemsToTestList();

        assertEquals(item1, itemList.getItem(0));
        assertEquals(item3, itemList.getItem(2));
        assertEquals(3, itemList.listSize());
    }

    @Test
    void testGetListEmpty() {
        assertEquals(0, itemList.getList().size());
    }

    @Test
    void testGetListWithElements() {
        addItemsToTestList();
        assertEquals(3, itemList.getList().size());
    }

    void addItemsToTestList() {
        itemList.addItem(item1);
        itemList.addItem(item2);
        itemList.addItem(item3);
    }
}
