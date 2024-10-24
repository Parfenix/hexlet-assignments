@import exercise.dto.users.UserPage
@param UserPage page


@template.layout.page(content = @`

    <h1>User Details</h1>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Name: ${page.getUser().getFirstName()}</h5>
            <h5 class="card-title">Last name: ${page.getUser().getLastName()}</h5>
            <p class="card-text">Email: ${page.getUser().getEmail()}</p>
            <p class="card-text">ID: ${page.getUser().getId()}</p>
        </div>
    </div>
    <a href="/users" class="btn btn-secondary mt-4">Back to Users List</a>
`)




