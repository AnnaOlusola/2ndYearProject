package controllers.security;
import play.*;
import play.mvc.Http.*;
import models.*;
import models.users.*;
import play.mvc.Result;
import play.mvc.Security;

import static tyrex.util.Logger.security;

//this returns user id if a user is logged in if not loggedin redirect to login page
public class Secured extends Security.Authenticator{
    public String getUsername(Context ctx ){
        return ctx.session().get("email");
    }
    public Result onUnauthorized(Context ctx)
    {
        return redirect(controllers.security.routes.LoginController.login());
    }
}