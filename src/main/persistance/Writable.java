package persistance;

import org.json.JSONObject;

// Models everything that can be written into a JSON file
// CITATION: Code modeled from JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
