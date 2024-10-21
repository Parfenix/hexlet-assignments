package exercise;

import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

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
            var usersPage = new UsersPage(USERS);
            ctx.render("users/index.jte", model("page", usersPage));
        });

        app.get("/users/{id}", ctx -> {
            var userId = ctx.pathParamAsClass("id", Integer.class).get();
            var user = USERS.stream()
                    .filter(u -> u.getId() == userId)
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("User not found"));

            var userPage = new UserPage(user);
            ctx.render("users/show.jte", model("page", userPage));
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
