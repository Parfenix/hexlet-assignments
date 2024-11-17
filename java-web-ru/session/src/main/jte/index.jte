@import exercise.dto.MainPage
@param MainPage page

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hello Hexlet!</title>
</head>
<body>
    <main>
        <h1>Привет, Хекслет!</h1>

        @if(page.getName() == null)
            <a href="/sessions/build">Войти</a>
        @else
            Привет, ${page.getName().toString()}!
            <form action="/sessions/delete" method="post">
                <button type="submit">Выйти</button>
            </form>
        @endif
    </main>
</body>
</html>
