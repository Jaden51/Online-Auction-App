package persistence;

import model.Item;
import model.ItemList;
import org.junit.jupiter.api.Test;
import persistance.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// CITATION: code modeled from JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderFileDoesNotExist() {
        JsonReader reader = new JsonReader("./data/noFile.json");
        try {
            ItemList il = reader.readUserList();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyItemList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyUserStore.json");
        try {
            ItemList il = reader.readUserList();
            assertEquals("Jaden Hums", il.getUsername());
            assertEquals(0, il.getList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUserStore.json");
        try {
            ItemList il = reader.readUserList();
            assertEquals("Jaden Hums", il.getUsername());
            List<Item> itemListExpected = il.getList();
            assertEquals(2, itemListExpected.size());
            checkItem("hat", 100, 10, 1000,
                    -1, false, itemListExpected.get(0));
            checkItem("pants", 200, 1, 500,
                    -1, false, itemListExpected.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

