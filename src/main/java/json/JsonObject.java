package json;

import java.util.ArrayList;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private ArrayList<JsonPair> jsnObj;

    public JsonObject(JsonPair... jsonPairs) {
        jsnObj = new ArrayList<>();
        for (JsonPair pair : jsonPairs) {
            jsnObj.add(pair);
        }
    }

    @Override
    public String toJson() {
        String json = "{";
        for (int i = 0; i < jsnObj.size(); i++) {
            if (i != jsnObj.size() - 1) {
                json += jsnObj.get(i).key + ": ";
                json += jsnObj.get(i).value.toJson() + ", ";
            }
            else {
                json += jsnObj.get(i).key + ": ";
                json += jsnObj.get(i).value.toJson();
            }
        }
        json += "}";
        return json;
    }

    public void add(JsonPair jsonPair) {
        jsnObj.add(jsonPair);
    }

    public boolean contains(String name) {
        for (JsonPair pair : jsnObj) {
            if (pair.key.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Json find(String name) {
        for (JsonPair pair: jsnObj) {
            if (pair.key.equals(name)) {
                return pair.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject project = new JsonObject();
        for (String name : names) {
            Json val = find(name);
            if (val != null) {
                project.add(new JsonPair(name, val));
            }
        }
        return project;
    }
}
