@import exercise.dto.users.UsersPage
@param UsersPage page

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Users</title>
</head>
<body>
<h1>Users</h1>

<form action="/users" method="get">
    <input type="search" name="term" value="${page.getTerm()}" />
    <input type="submit" value="Search" />
</form>


@for(var user : page.getUsers())
    <div>
        <h2>${user.getFirstName()} ${user.getLastName()}</h2>
        <p>${user.getEmail()}</p>
    </div>
@endfor

</body>
</html>

