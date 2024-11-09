package exercise;

import io.javalin.Javalin;
import java.util.List;

import exercise.model.User;
import exercise.dto.users.UsersPage;

import io.javalin.rendering.template.JavalinJte;
import org.apache.commons.lang3.StringUtils;

import static io.javalin.rendering.template.TemplateUtil.model;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users", ctx -> {
            var term = ctx.queryParam("term");
            term = (term != null) ? term.toLowerCase() : "";

            System.out.println("Term from query: " + term);
            System.out.println("All users: " + USERS);

            String finalTerm = term;
            List<User> filteredUsers = USERS.stream()
                    .filter(user -> user.getFirstName().toLowerCase().startsWith(finalTerm))
                    .toList();

            UsersPage page = new UsersPage(filteredUsers, finalTerm);

            System.out.println("Term: " + term);
            System.out.println("Filtered users: " + filteredUsers);

            ctx.render("users/index.jte", model("page", page));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
