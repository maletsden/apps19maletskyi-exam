package json;

import java.util.LinkedHashMap;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private LinkedHashMap<String, Json> json;

    public JsonObject(JsonPair... jsonPairs) {
        json = new LinkedHashMap<>();
        for (JsonPair jsonPair: jsonPairs) {
            add(jsonPair);
        }
    }

    @Override
    public String toJson() {
        StringBuilder jsonString = new StringBuilder("{");

        for (String key: json.keySet()) {
            jsonString.append(key).append(": ").append(find(key).toJson()).append(", ");
        }

        // slice last comma
        if (jsonString.length() > 1) {
            jsonString.setLength(jsonString.length() - 2);
        }

        jsonString.append("}");
        return jsonString.toString();
    }

    public void add(JsonPair jsonPair) {
        json.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return json.getOrDefault(name, null);
    }

    public boolean contains(String name) {
        return find(name) != null;
    }

    public JsonObject projection(String... names) {
        JsonObject jsonObject = new JsonObject();

        for (String name: names) {
            Json value = find(name);
            if (value == null) {
                continue;
            }
            jsonObject.add(new JsonPair(name, value));
        }
        return jsonObject;
    }
}
