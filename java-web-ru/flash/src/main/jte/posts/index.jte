@import exercise.dto.posts.PostsPage
@import exercise.model.Post
@param PostsPage page

@template.layout.page(
    page = page,
    content = @`
        <h1>All Posts</h1>
        <ul>
            @for(Post post : page.getPosts())
            <li>${post.getName()}</li>
            @endfor
        </ul>
    `
)
