@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Users List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="my-4">Users List</h1>

    @if(page.getUsers().isEmpty())
        <p>No users available.</p>
    @else
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            @for(User user : page.getUsers())
                <tr>
                    <td>${user.getId()}</td>
                    <td>${user.getFirstName()}</td>
                    <td>${user.getLastName()}</td>
                    <td>${user.getEmail()}</td>
                    <td>
                        <a href="/users/${user.getId()}" class="btn btn-primary">View</a>
                    </td>
                </tr>
            @endfor
            </tbody>
        </table>
    @endif
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
