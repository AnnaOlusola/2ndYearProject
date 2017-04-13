package models;
import java.util.*;
import javax.persistence.*;
import play.data.format.*;

import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class BlogPost extends Model{
@Id
    private Long id;
    @Constraints.Required
    private String title;
    @Constraints.Required
    private String category;
    @Constraints.Required
    @Column(columnDefinition = "TEXT")
    private String text;
@Constraints.Required
@Formats.DateTime(pattern="dd-MMM-yyyy")
private Date posted;
 
    public BlogPost() {

    }

    public BlogPost(Long id, String title, String category, String text) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.setText(text);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public void setCategory(String category) {
        this.category = category;
    }

public void setPosted(Date date){
this.posted = date;
}

public Date getPosted(){
return posted;
}

    public static Finder<Long, BlogPost> find = new Finder<Long, BlogPost>(BlogPost.class);

    public static List<BlogPost> findAll(String filter){
        return BlogPost.find.where()
                .ilike("title", "%" + filter + "%")
                .orderBy("posted desc")
                .findList();
    }

}
