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
<form action="/users" method="post">
    <div>
        <label>
            Имя
            <input type="text" name="firstName" />
        </label>
    </div>
    <div>
        <label>
            Фамилия
            <input type="text" name="lastName" />
        </label>
    </div>
    <div>
        <label>
            Email
            <input type="email" required name="email" />
        </label>
    </div>
    <div>
        <label>
            Пароль
            <input type="password" required name="password" />
        </label>
    </div>
    <input type="submit" value="Зарегистрировать" />
</form>
</body>
</html>