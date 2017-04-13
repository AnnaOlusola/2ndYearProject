package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import models.users.Member;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.*;
import play.data.format.*;

import javax.persistence.*;
import java.util.List;

@Entity
public class Video extends Model {

    @Id
    private String id;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public static Finder<String,Video> find = new Finder<String,Video>(Video.class);
    public static List<Video> findAll() {
        return Video.find.all();
    }

}
