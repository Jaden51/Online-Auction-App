package persistance;

import model.AuctionItemList;
import model.Item;
import model.ItemList;
import model.UserItemList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// CITATION: Code modeled from JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads itemList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public UserItemList readUserList() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUserList(jsonObject);
    }

    public AuctionItemList readAuctionList() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAuctionList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses itemList from JSON object and returns it
    private UserItemList parseUserList(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        UserItemList il = new UserItemList(username);
        addItems(il, jsonObject);
        return il;
    }

    private AuctionItemList parseAuctionList(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        AuctionItemList il = new AuctionItemList(username);
        addItems(il, jsonObject);
        return il;
    }

    // MODIFIES: il
    // EFFECTS: parses items from JSON object and adds them to itemList
    private void addItems(ItemList il, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("userStoreItems");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(il, nextItem);
        }
    }

    // MODIFIES: il
    // EFFECTS: parses item from JSON object and adds it to the itemList
    private void addItem(ItemList il, JSONObject jsonObject) {
        String itemName = jsonObject.getString("itemName");
        double startingPrice = jsonObject.getDouble("startingPrice");
        double minBid = jsonObject.getDouble("minBid");
        double buyout = jsonObject.getDouble("buyout");
        double currentBid = jsonObject.getDouble("currentBid");
        boolean sold = jsonObject.getBoolean("sold");
        Item item = new Item(itemName, startingPrice, minBid, buyout, currentBid, false);
        il.addItem(item);
    }
}
