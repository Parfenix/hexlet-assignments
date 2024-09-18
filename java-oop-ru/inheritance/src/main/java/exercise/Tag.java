package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    protected String name;
    protected Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    protected String renderAttributes() {
        return attributes.entrySet()
                .stream()
                .map(entry -> String.format(" %s=\"%s\"", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining());

    }

    @Override
    public abstract String toString();
}
// END
