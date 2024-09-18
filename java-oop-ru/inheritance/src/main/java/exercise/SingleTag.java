package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {
    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    @Override
    public String toString() {
        String attrs = renderAttributes();
        return attrs.isEmpty() ? String.format("<%s>", name) : String.format("<%s%s>", name, attrs);
    }
}
// END
