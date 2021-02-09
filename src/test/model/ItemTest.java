package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item item;

    @BeforeEach
    void setup() {
        item = new Item("hat", 100, 10, 1000);
    }

    @Test
    void testIncreaseBidNotBuyout() {
        item.increaseBid(10);
        assertEquals(110, item.getCurrentBid());
        assertFalse(item.isSold());
    }

    @Test
    void testIncreaseBidNotAboveMinBid() {
        assertFalse(item.increaseBid(5));
        assertEquals(-1, item.getCurrentBid());

        assertTrue(item.increaseBid(20));
        assertEquals(120, item.getCurrentBid());
    }

    @Test
    void testIncreaseBidBuyout() {
        assertTrue(item.increaseBid(10000));
        assertTrue(item.isSold());
    }

    @Test
    void testItemStatusAfterBid() {
        item.increaseBid(20);
        assertEquals("hat", item.getItemName());
        assertEquals(100, item.getStartingPrice());
        assertEquals(10, item.getMinBid());
        assertEquals(1000, item.getBuyout());
    }

}
