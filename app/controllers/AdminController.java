package controllers;

import controllers.security.*;

import models.Video;
import models.Diet;
import models.users.Member;
import models.users.User;
import models.BlogPost;
import models.TimeSlot;
import play.db.ebean.Transactional;
import play.mvc.*;
import views.html.*;
import views.html.admin.*;
import views.html.admin.about;
import views.html.admin.contactUs;
import views.html.admin.index;
import views.html.admin.timetable;

import play.api.Environment;
import play.data.*;


import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

import play.mvc.Http.*;
import play.mvc.Http.MultipartFormData.FilePart;
import java.io.File;



// Authenticate user
@Security.Authenticated(Secured.class)
// Authorise user (check if admin)
@With(AuthAdmin.class)
public class AdminController extends Controller {
private FormFactory formFactory;
private Environment env;
@Inject
    public AdminController(FormFactory f, Environment e){
        this.formFactory = f;
        this.env = e;
    }


    @Transactional
       private User getCurrentUser() {
        User u = User.getLoggedIn(session().get("email"));
        return u;
    }
    public Result vMembers() {
        List<Member> memberList = Member.findAll();
        return ok(members.render(getCurrentUser(), memberList));
    }
    @Transactional
    public Result updateMem(String email) {

        Member m;
        Form<Member> memberForm;

        try {
            // Find the product by id
            m = Member.find.byId(email);

            // Create a form based on the Product class and fill using p
            memberForm = formFactory.form(Member.class).fill(m);

        } catch (Exception ex) {
            // Display an error message or page
            return badRequest("error");
        }
        // Render the updateProduct view - pass form as parameter
        return ok(addMember.render(getCurrentUser(), memberForm));
    }


    // Delete Product by id
    @Transactional
    public Result deleteMem(String email) {

        // find product by id and call delete method
        Member.find.ref(email).delete();
        // Add message to flash session
        flash("success", "Member has been deleted");

        // Redirect to products page
        return redirect(routes.AdminController.vMembers());
    }

    public Result addVideo() {

        Form<Video> addVideoForm = formFactory.form(Video.class);

        return ok(addVideo.render(getCurrentUser(), addVideoForm));

    }

    public Result addVideoSubmit(){

        Form<Video> newVideoForm = formFactory.form(Video.class).bindFromRequest();

        if(newVideoForm.hasErrors()){

            return badRequest(addVideo.render(getCurrentUser(), newVideoForm));
        }

        Video newVideo = newVideoForm.get();
        if (newVideo.getUrl() == null) {
            // Save to the database via Ebean (remember Product extends Model)
            newVideo.save();
        }
        // Product already exists so update
        else if (newVideo.getUrl() != null) {
            newVideo.update();
        }

        flash("Success", "You have been registered. You can now Login.");

        return redirect(controllers.routes.AdminController.index());


    }


    @Transactional
    public Result updateVideo(String url) {

        Video v;
        Form<Video> videoForm;

        try {
            // Find the product by id
            v = Video.find.byId(url);

            // Create a form based on the Product class and fill using p
            videoForm = formFactory.form(Video.class).fill(v);

        } catch (Exception ex) {
            // Display an error message or page
            return badRequest("error");
        }
        // Render the updateProduct view - pass form as parameter
        return ok(addVideo.render(getCurrentUser(), videoForm));
    }


    public Result addDiet() {

        Form<Diet> addDietForm = formFactory.form(Diet.class);

        return ok(addDiet.render(getCurrentUser(), addDietForm));

    }

    public Result addDietSubmit(){

        Form<Diet> newDietForm = formFactory.form(Diet.class).bindFromRequest();

        if(newDietForm.hasErrors()){

            return badRequest(addDiet.render(getCurrentUser(), newDietForm));
        }

        Diet newDiet = newDietForm.get();
        if (newDiet.getSrc() == null) {
            // Save to the database via Ebean (remember Product extends Model)
            newDiet.save();
        }
        // Product already exists so update
        else if (newDiet.getSrc() != null) {
            newDiet.update();
        }

        flash("Success", "You have been registered. You can now Login.");

        return redirect(controllers.routes.AdminController.index());


    }

    @Transactional
    public Result updateDiet(String src) {

        Diet d;
        Form<Diet> dietForm;

        try {
            // Find the product by id
            d = Diet.find.byId(src);

            // Create a form based on the Product class and fill using p
            dietForm = formFactory.form(Diet.class).fill(d);

        } catch (Exception ex) {
            // Display an error message or page
            return badRequest("error");
        }
        // Render the updateProduct view - pass form as parameter
        return ok(addDiet.render(getCurrentUser(), dietForm));
    }


    // Render and return  the add new product page
    // The page will load and display an empty add product form


    @Transactional
    public Result blog(String filter) {
        List<BlogPost> postsList = BlogPost.findAll(filter);
        return ok(blog.render(getCurrentUser(), postsList, env, filter));
    }

@Transactional
public Result addPost() {
        Form<BlogPost> addPostForm = formFactory.form(BlogPost.class);

        return ok(addPost.render(addPostForm, getCurrentUser()));
    }
@Transactional
    public Result addPostSubmit(){
        String saveImageMsg;
        Form<BlogPost> newPostForm = formFactory.form(BlogPost.class).bindFromRequest();

        if(newPostForm.hasErrors()){
            return badRequest(addPost.render(newPostForm, getCurrentUser()));
        }

        BlogPost newPost = newPostForm.get();

        if (newPost.getId() == null) {
            newPost.save();
        }
        else if (newPost.getId() != null) {
            newPost.update();
        }

        MultipartFormData data = request().body().asMultipartFormData();
        FilePart image = data.getFile("upload");

        saveImageMsg = saveFile(newPost.getId(), image);

        flash("success", "Blog Post has been created/ updated" + saveImageMsg);

        return redirect(routes.AdminController.index());
    }
@Transactional
    public String saveFile(Long id, FilePart image){
        if (image != null) {
            String mimeType = image.getContentType();
            if (mimeType.startsWith("image/")) {
                File file = (File) image.getFile();
                ConvertCmd cmd = new ConvertCmd();
                IMOperation op = new IMOperation();
                op.addImage(file.getAbsolutePath());
                op.resize(300, 200);
                op.addImage("public/images/postImages/" + id +".jpg");
                try{
                    cmd.run(op);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return " and image saved";
            }
        }
        return "image file missing";
    }
@Transactional
    public Result deletePost(Long id){

        BlogPost.find.ref(id).delete();

        flash("success", "Post has been deleted");

        return redirect(routes.AdminController.index());
    }
@Transactional
    public Result updatePost(Long id){
        BlogPost p;
        Form<BlogPost> postForm;

        try{
            p = BlogPost.find.byId(id);

            postForm = formFactory.form(BlogPost.class).fill(p);

        } catch (Exception ex) {
            return badRequest("error");
        }

        return ok(addPost.render(postForm, getCurrentUser()));
    }


    public Result appointments() { return ok (appointments.render(getCurrentUser()));}

    public Result video() {
        List<Video> addVideo = Video.findAll();
        return ok(video.render(getCurrentUser(), addVideo, env));
    }

    public Result diet() {
        List<Diet> addDiet = Diet.findAll();
        return ok(diet.render(getCurrentUser(), addDiet, env));
    }


    public Result index() { return ok (index.render(getCurrentUser()));}

    public Result progress() { return ok (progress.render(getCurrentUser()));}

    public Result about() { return ok (about.render(getCurrentUser()));}

    public Result contactUs() { return ok (contactUs.render(getCurrentUser()));}

    public Result progressList() {return ok (progressList.render(getCurrentUser()));}



    public Result timetable() { return ok (timetable.render(getCurrentUser()));}
@Transactional
    public Result timeSlots() {
List<TimeSlot> timeSlotsList = TimeSlot.findAll();

return ok(timeSlots.render(getCurrentUser(), timeSlotsList));
}
@Transactional
public Result addTimeSlot() {

Form<TimeSlot> addTimeSlotForm = formFactory.form(TimeSlot.class);

return ok(addTimeSlot.render(getCurrentUser(), addTimeSlotForm));
}
@Transactional
public Result addTimeSlotSubmit(){

Form<TimeSlot> newTimeSlotForm = formFactory.form(TimeSlot.class).bindFromRequest();

if(newTimeSlotForm.hasErrors()) {

return badRequest(addTimeSlot.render(getCurrentUser(), newTimeSlotForm));
}

TimeSlot p = newTimeSlotForm.get();
if(p.getId() == null) {
p.save();
}

else if (p.getId() != null) {
p.update();
}

flash("success", "Time Slot has been created/updated");

return redirect(controllers.routes.AdminController.timeSlots());

}
@Transactional
public Result deleteTimeSlot(Long id) {

TimeSlot.find.ref(id).delete();

flash("success", "Time Slot has been deleted");

return redirect (controllers.routes.AdminController.timeSlots());
}
@Transactional
public Result updateTimeSlot(Long id) {

TimeSlot p;
Form<TimeSlot>timeSlotForm;

try{
p = TimeSlot.find.byId(id);

timeSlotForm =formFactory.form(TimeSlot.class).fill(p);

} catch(Exception ex) {

return badRequest("Error");
}

return ok(addTimeSlot.render(getCurrentUser(), timeSlotForm));
}
}
