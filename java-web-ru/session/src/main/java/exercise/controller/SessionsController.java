package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionsController {

    // BEGIN
    private static final Logger logger = LoggerFactory.getLogger(SessionsController.class);

    public static void build(Context ctx) {
        ctx.render("build.jte");
    }

    public static void create(Context ctx) {
        try {
            var nickname = ctx.formParam("name");
            var password = ctx.formParam("password");

            var userOptional = UsersRepository.findByName(nickname);

            if (userOptional.isEmpty() || !userOptional.get().getPassword().equals(encrypt(password))) {
                var page = new LoginPage(nickname, "Wrong username or password");
                ctx.status(401);
                ctx.render("build.jte", model("page", page));
                return;
            }

            ctx.sessionAttribute("currentUser", nickname);
            ctx.redirect(NamedRoutes.rootPath());
        } catch (Exception e) {
            logger.error("Error during login attempt", e);
            ctx.status(500).result("Internal Server Error");
        }
    }

    public static void delete(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
