package exercise.dto.posts;

import java.util.List;
import exercise.model.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;


// BEGIN
@AllArgsConstructor
@Getter
public class PostsPage {
    private  List<Post> posts;       // Список постов на текущей странице
    private  int currentPage;        // Номер текущей страницы
}

// END


