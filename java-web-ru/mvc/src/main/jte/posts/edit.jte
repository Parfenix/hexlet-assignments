@import exercise.dto.posts.EditPostPage
@import exercise.util.NamedRoutes
@import java.util.Collections
@param EditPostPage page

@template.layout.page(
    content = @`
        <main>
            <div class="container">
                <h1 class="my-4">Редактировать пост</h1>

                <form action="${NamedRoutes.updatePostPath(String.valueOf(page.getId()))}" method="post">
                    <div class="mb-3">
                        <lavel for="name" class="form-label">Название</lavel>
                        <input type="text" class="form-control ${page.getErrors().containsKey("name") ? "is-invalid" : ""}"
                               id="name" name="name" value="${page.getName()}" required>
                        <div class="invalid-feedback">
                            ${page.getErrors().getOrDefault("name", Collections.emptyList()).toString()}
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="body" class="form-label">Содержимое</label>
                        <textarea class="form-control ${page.getErrors().containsKey("body") ? "is-invalid" : ""}"
                                  id="body" name="body" rows="5" required>${page.getBody()}</textarea>
                        <div class="invalid-feedback">
                            ${page.getErrors().getOrDefault("body", Collections.emptyList()).toString()}
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary">Сохранить изменения</button>
                </form>
            </div>
        </main>
`
)
