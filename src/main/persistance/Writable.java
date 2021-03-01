package persistance;

import org.json.JSONObject;

// CITATION: Code from JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
