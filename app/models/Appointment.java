package models;

import java.sql.Time;
import java.util.*;
import javax.persistence.*;

import models.users.Admin;
import models.users.Member;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.*;

@Entity
public class Appointment extends Model{
@Id
private String appointmentId;
    @Constraints.Required
    private Time time;
    @Constraints.Required
    private Date date;

    private String memberName;
    private String memberId;
    private String staffName;

    @ManyToOne
    private Admin admin;


    @ManyToOne
    private Member member;


    public Time getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
