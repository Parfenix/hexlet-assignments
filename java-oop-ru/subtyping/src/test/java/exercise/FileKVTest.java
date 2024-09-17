package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import com.fasterxml.jackson.databind.ObjectMapper;
// BEGIN
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // BEGIN
    @Test
    void testGet() {
        KeyValueStorage storage = new FileKV("src/test/resources/file", Map.of("key", "value"));
        assertEquals("value", storage.get("key", "default"));
        assertEquals("default", storage.get("unknown", "default"));
    }

    @Test
    void testSet() {
        KeyValueStorage storage = new FileKV("src/test/resources/file", Map.of("key", "value"));
        storage.set("key2", "value2");
        assertEquals("value2", storage.get("key2", "default"));
    }

    @Test
    void testUnset() {
        KeyValueStorage storage = new FileKV("src/test/resources/file", Map.of("key", "value"));
        storage.set("key2", "value2");
        storage.unset("key2");
        assertEquals("default", storage.get("key2", "default"));
    }

    @Test
    void testToMap() {
        KeyValueStorage storage = new FileKV("src/test/resources/file", Map.of("key", "value"));
        Map<String, String> expected = Map.of("key", "value");
        assertEquals(expected, storage.toMap());
    }
    // END
}
