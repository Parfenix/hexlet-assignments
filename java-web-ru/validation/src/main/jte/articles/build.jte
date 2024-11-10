@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
@if(page.getErrors() != null)
    <ul>
        @for(var validator : page.getErrors().values())
            @for(var error : validator)
                <li>${error.getMessage()}</li>
            @endfor
        @endfor
    </ul>
@endif

<form action="/articles" method="post">
    <div>
        <label>
            title
            <input type="text" required name="title" value="${page.getTitle()}" />
        </label>
    </div>
    <div>
        <label>
            content
            <textarea type="email" required name="content" value="${page.getContent()}"></textarea>
        </label>
    </div>

    <input type="submit" value="Зарегистрировать" />
</form>
</body>
</html>