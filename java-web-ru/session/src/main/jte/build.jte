@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage
@param LoginPage page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
<h1>Login</h1>

<form action="${NamedRoutes.loginPath()}" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <button type="submit">Login</button>
</form>

@if(page != null && page.getError() != null)
    <div style="color: red;">
        <p>${page.getError()}</p>
    </div>
@endif
</body>
</html>
