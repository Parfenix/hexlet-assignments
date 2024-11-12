package exercise.dto.posts;

import java.util.List;
import java.util.Map;

import exercise.model.Post;
import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// BEGIN
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditPostPage {
    private Long id;
    private String name;
    private String body;
    private Map<String, List<ValidationError<?>>> errors;

    public EditPostPage(Post post) {
        this.id = post.getId();
        this.name = post.getName();
        this.body = post.getBody();
        this.errors = null;
    }
}
// END
