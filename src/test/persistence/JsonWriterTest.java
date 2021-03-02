package persistence;

import model.Item;
import model.ItemList;
import model.UserItemList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// CITATION: code modeled from JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    private ItemList itemList;
    private Item item1;
    private Item item2;

    @BeforeEach
    void setup() {
        itemList = new UserItemList("Jaden Hums");
        item1 = new Item("hat", 100, 10, 1000);
        item2 = new Item("pants", 200, 1, 500);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyUserStore() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUserStore.json");
            writer.open();
            writer.write(itemList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUserStore.json");
            itemList = reader.read();
            assertEquals("Jaden Hums", itemList.getUsername());
            assertEquals(0, itemList.getList().size());
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    void testWriterGeneralUserStore() {
        try {
            itemList.addItem(item1);
            itemList.addItem(item2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUserStore.json");
            writer.open();
            writer.write(itemList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUserStore.json");
            itemList = reader.read();
            assertEquals("Jaden Hums", itemList.getUsername());
            List<Item> itemListExpected = itemList.getList();
            assertEquals(2, itemListExpected.size());
            checkItem("hat", 100, 10, 1000,
                    -1, false, itemListExpected.get(0));
            checkItem("pants", 200, 1, 500,
                    -1, false, itemListExpected.get(1));
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

}
