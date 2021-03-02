package persistence;

import model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

// CITATION: code modeled form JsonSerializationDemo
public class JsonTest {
    protected void checkItem(String name, double startingPrice, double minBid, double buyout,
                               double currentBid, boolean sold, Item item) {
        assertEquals(name, item.getItemName());
        assertEquals(startingPrice, item.getStartingPrice());
        assertEquals(minBid, item.getMinBid());
        assertEquals(buyout, item.getBuyout());
        assertEquals(currentBid, item.getCurrentBid());
        assertEquals(sold, item.isSold());
    }
}
