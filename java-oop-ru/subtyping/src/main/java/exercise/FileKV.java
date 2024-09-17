package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {

    private String filePath;

    public FileKV(String filePath, Map<String, String> initialData) {
        this.filePath = filePath;
        Utils.writeFile(filePath, Utils.serialize(initialData));
    }

    private Map<String, String> readFromFile() {
        String fileContent = Utils.readFile(filePath);
        return Utils.deserialize(fileContent);
    }

    private void writeToFile(Map<String, String> data) {
        String serializedData = Utils.serialize(data);
        Utils.writeFile(filePath, serializedData);
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> data = readFromFile();
        data.put(key, value);
        writeToFile(data);
    }

    @Override
    public void unset(String key) {
        Map<String, String> data = readFromFile();
        data.remove(key);
        writeToFile(data);
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> data = readFromFile();
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(readFromFile());
    }
}
// END
