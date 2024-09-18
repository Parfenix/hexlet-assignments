package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String body;
    private List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {
        String attrs = renderAttributes();
        String childrenString = children.stream()
                .map(Tag::toString)
                .collect(Collectors.joining());
        String openTag = attrs.isEmpty() ? String.format("<%s>", name) : String.format("<%s%s>", name, attrs);
        return String.format("%s%s%s</%s>", openTag, body, childrenString, name);
    }
}
// END
