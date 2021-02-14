package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item item1;
    private Item item2;

    @BeforeEach
    void setup() {
        item1 = new Item("hat", 100, 10, 1000);
        item2 = new Item("pants", 100, 0, 1000);
    }

    @Test
    void testIncreaseBidNotBuyout() {
        item1.increaseBid(10);
        assertEquals(110, item1.getCurrentBid());
        assertFalse(item1.isSold());
    }

    @Test
    void testIncreaseBidNotAboveMinBid() {
        assertFalse(item1.increaseBid(5));
        assertEquals(-1, item1.getCurrentBid());

        assertTrue(item1.increaseBid(20));
        assertEquals(120, item1.getCurrentBid());
    }

    @Test
    void testIncreaseBidBuyout() {
        assertTrue(item1.increaseBid(10000));
        assertTrue(item1.isSold());
    }

    @Test
    void testItemStatusAfterBid() {
        item1.increaseBid(20);
        assertEquals("hat", item1.getItemName());
        assertEquals(100, item1.getStartingPrice());
        assertEquals(10, item1.getMinBid());
        assertEquals(1000, item1.getBuyout());
    }

    @Test
    void testIncreaseBidToVerifyStartingPrice() {
        item2.increaseBid(0);
        assertEquals(item2.getStartingPrice(), item2.getCurrentBid());
    }

}
