@import exercise.dto.users.UserPage
@param UserPage page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="my-4">User Details</h1>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Name: ${page.getUser().getFirstName() + " " + page.getUser().getLastName()}</h5>
            <p class="card-text">Email: ${page.getUser().getEmail()}</p>
            <p class="card-text">ID: ${page.getUser().getId()}</p>
        </div>
    </div>

    <a href="/users" class="btn btn-secondary mt-4">Back to Users List</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
