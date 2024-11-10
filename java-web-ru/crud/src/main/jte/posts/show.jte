@import exercise.dto.posts.PostPage
@param PostPage page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${page.getPost().getName()}</title>
</head>
<body>
<h1>${page.getPost().getName()}</h1>
<p>${page.getPost().getBody()}</p>
<a href="/posts">Назад к постам</a>
</body>
</html>
