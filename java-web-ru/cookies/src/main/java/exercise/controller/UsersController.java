package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;

import java.util.Optional;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void register(Context ctx) {
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        String token = Security.generateToken();

        User newUser = new User(firstName, lastName, email, password, token);

        UserRepository.save(newUser);

        ctx.cookie("authToken", token);

        ctx.redirect(NamedRoutes.userPath(newUser.getId()));
    }

    public static void show(Context ctx) {
        Long userId = Long.valueOf(ctx.pathParam("id"));
        String cookieToken = ctx.cookie("authToken");

        User user = UserRepository.find(userId).orElse(null);

        if (user == null || !user.getToken().equals(cookieToken)) {
            ctx.redirect(NamedRoutes.buildUserPath());
            return;
        }

        UserPage page = new UserPage(user);

        ctx.render("users/show.jte", model("page", page));
    }
    // END
}
