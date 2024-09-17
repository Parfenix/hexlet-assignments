package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private String text;
    private TagInterface childTag;

    public LabelTag(String text, TagInterface childTag) {
        this.text = text;
        this.childTag = childTag;
    }

    @Override
    public String render() {
        return String.format("<label>%s%s</label>", text, childTag.render());
    }
}
// END
