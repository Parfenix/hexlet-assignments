package exercise;

import java.util.Map;
import java.util.Map.Entry;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> data = storage.toMap();

        for (String key : data.keySet()) {
            storage.unset(key);
        }

        for (Map.Entry<String, String> entry : data.entrySet()) {
            storage.set(entry.getValue(), entry.getKey());
        }
    }
}
// END
