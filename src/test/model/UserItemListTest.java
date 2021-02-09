package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserItemListTest {
    private UserItemList itemList;

    @BeforeEach
    void setup() {
        itemList = new UserItemList();
    }

    @Test
    void testAddItemEmptyList() {
        assertEquals(0, itemList.getItem(0));
    }

}
