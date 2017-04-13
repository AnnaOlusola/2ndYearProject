package controllers;
import play.db.ebean.Transactional;
import play.mvc.*;
import views.html.*;
import controllers.security.*;
import models.users.*;
import models.*;
import views.html.member.*;
import views.html.member.about;
import views.html.member.contactUs;
import views.html.member.timetable;
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
@With(AuthMem.class)


public class MemberController extends Controller {


private FormFactory formFactory;
private Environment env;
@Inject
    public MemberController(FormFactory f, Environment e){
        this.formFactory = f;
        this.env = e;
    }


    @Transactional
    public User getCurrentUser(){
        User u = User.getLoggedIn(session().get("email"));
        return u;
    }
@Transactional
    public Result appointments() {
        List<TimeSlot> timeSlotsList = TimeSlot.findAll();
        return ok (views.html.member.appointments.render(getCurrentUser(), timeSlotsList));}
@Transactional
    public Result addToYourBooking(Long id){
        TimeSlot p = TimeSlot.find.byId(id);

        Member member = (Member)User.getLoggedIn(session().get("email"));

        if(member.getBasket() == null) {

            member.setBasket(new YourBooking());
            member.getBasket().setMember(member);
            member.update();
        }

        member.getBasket().addTimeSlot(p);
        member.update();

        return ok(views.html.member.yourBooking.render(getCurrentUser(), member));
    }

    @Transactional
    public Result showYourBooking(){
        return ok(views.html.member.yourBooking.render(getCurrentUser(), (Member) getCurrentUser()));
    }
@Transactional
    public Result emptyBasket(){
        Member m = (Member) getCurrentUser();
        m.getBasket().removeAllItems();
        m.getBasket().update();

        return ok(views.html.member.yourBooking.render(getCurrentUser(), m));
    }

    @Transactional
    public Result bookAppointment() {
        Member m = (Member) getCurrentUser();

        BookingOrder order = new BookingOrder();

        order.setMember(m);

        order.setItems(m.getBasket().getYourBookingItems());

        order.save();

        for(BookingItem i: order.getItems()) {
            i.setOrder(order);

            i.setBasket(null);

            i.update();
        }

        order.update();


        m.getBasket().setYourBookingItems(null);
        m.getBasket().update();

        return ok(orderConfirmed.render(m,order));
    }

@Transactional
    public Result blog(String filter) {
        List<BlogPost> postsList = BlogPost.findAll(filter);
        return ok(blog.render(getCurrentUser(), postsList, env, filter));
    }

    public Result diet() { return ok (diet.render(getCurrentUser()));}

    public Result about() { return ok (about.render(getCurrentUser()));}

    public Result contactUs() { return ok (contactUs.render(getCurrentUser()));}

    public Result timetable() { return ok (timetable.render(getCurrentUser()));}

    public Result video() { return ok (video.render(getCurrentUser()));}

    public Result index() { return ok (views.html.member.index.render(getCurrentUser()));}

    public Result progress() { return ok (views.html.member.progress.render(getCurrentUser()));}

}
