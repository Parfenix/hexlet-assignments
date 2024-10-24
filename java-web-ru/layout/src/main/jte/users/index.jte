@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        @if(page.getUsers().isEmpty())
            <p>No users have been added yet</p>
        @else
            <h1>Users List</h1>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Last name</th>
                        <th>Email</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    @for (var user : page.getUsers())
                        <tr>
                            <td>${user.getId()}</td>
                            <td>${user.getFirstName()}</td>
                            <td>${user.getLastName()}</td>
                            <td>${user.getEmail()}</td>
                            <td><a href="/users/${user.getId()}" class="btn btn-primary">View</a></td>
                        </tr>
                    @endfor
                </tbody>
            </table>
        @endif
    `
)
