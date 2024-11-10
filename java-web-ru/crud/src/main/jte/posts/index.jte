@import exercise.dto.posts.PostsPage
@param PostsPage page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Posts</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Posts</h1>
    <ul class="list-group">
        @for(var post : page.getPosts())
        <li class="list-group-item">
            <a href="/posts/${post.getId()}">
                ${post.getName()}
            </a>
        </li>
        @endfor
    </ul>

    <!-- Пагинация -->
    <nav aria-label="Page navigation">
        <ul class="pagination mt-3">
            <li class="page-item ${page.getCurrentPage() <= 1 ? "disabled" : ""}">
                <a class="page-link" href="${exercise.util.NamedRoutes.postsPath()}?page=${page.getCurrentPage() - 1}">Previous</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="${exercise.util.NamedRoutes.postsPath()}?page=${page.getCurrentPage() + 1}">Next</a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>

