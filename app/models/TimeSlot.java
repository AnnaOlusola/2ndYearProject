package models;

import java.util.*;

import java.util.*;
import javax.persistence.*;

import play.data.validation.*;
import play.data.format.*;
import com.avaje.ebean.*;


@Entity
public class TimeSlot extends Model {

@Id
private Long id;


@Constraints.Required
@Formats.DateTime(pattern="dd-MMM-yyyy")
private Date date;

@Constraints.Required
private String day;

@Constraints.Required
private String time;

@Constraints.Required
private String trainer;

@Constraints.Required
private String duration;

@Constraints.Required
private int places;

public TimeSlot() {

}

public TimeSlot(Long id, Date date, String day, String time, String trainer, String duration, int places) {

this.id = id;
this.date = date;
this.day = day;
this.time = time;
this.trainer = trainer;
this.duration = duration;
this.places = places;

}

public Long getId(){
return id;
}

public void setId(Long id){
this.id = id;
}

public Date getDate(){
return date;
}

public void setDate(Date date){
this.date = date;
}

public String getDay(){
return day;
}

public void setDay(String day){
this.day = day;
}

public String getTime(){
return time;
}

public void setTime(String time){
this.time = time;
}

public String getTrainer(){
return trainer;
}

public void setTrainer(String trainer){
this.trainer = trainer;
}

public String getDuration(){
return duration;
}

public void setDuration(String duration){
this.duration = duration;
}

public int getPlaces(){
return places;
}

public void setPlaces(int places){
this.places = places;
}


public static Finder<Long,TimeSlot> find = new Finder<Long,TimeSlot>(TimeSlot.class);

public static List<TimeSlot> findAll() {
return TimeSlot.find.where().orderBy("date asc").findList();
}

}
