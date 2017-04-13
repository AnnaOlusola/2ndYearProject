
package controllers;

import models.users.Member;
import models.users.User;
import play.db.ebean.Transactional;
import play.mvc.*;
import play.data.*;
import views.html.*;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;


public class UserController extends Controller {



private FormFactory formFactory;

@Inject
public UserController(FormFactory f) {

this.formFactory = f;
}
    @Transactional
    public User getCurrentUser(){
        User u = User.getLoggedIn(session().get("email"));
        return u;
    }

   
    public Result index() {
        return ok(index.render(getCurrentUser()));
    }

    public Result about() {
        return ok(about.render(getCurrentUser()));
    }

    public Result contactUs() { 
	return ok (contactUs.render(getCurrentUser()));
    }

    public Result timetable() {
	 return ok (timetable.render(getCurrentUser()));
    }


public Result addMember() {

Form<Member> addMemberForm = formFactory.form(Member.class);

return ok(addMember.render(getCurrentUser(), addMemberForm));

}

public Result addMemberSubmit(){

Form<Member> newMemberForm = formFactory.form(Member.class).bindFromRequest();

if(newMemberForm.hasErrors()){

return badRequest(addMember.render(getCurrentUser(), newMemberForm));
}

Member newMember = newMemberForm.get();
        if (newMember.getEmail() == null) {
            // Save to the database via Ebean (remember Product extends Model)
            newMember.save();
        }
        // Product already exists so update
        else if (newMember.getEmail() != null) {
            newMember.update();
        }

flash("Success", "You have been registered. You can now Login.");

return redirect(controllers.routes.UserController.index());


}
    }
