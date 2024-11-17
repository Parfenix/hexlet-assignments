package exercise.dto.posts;

import java.util.List;
import exercise.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import exercise.dto.BasePage;
import lombok.Setter;

// BEGIN
@Getter
@Setter
public class PostsPage extends BasePage {
    private final List<Post> posts;

    public PostsPage(List<Post> posts) {
        this.posts = posts;
    }
}
// END
