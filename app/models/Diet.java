package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Diet extends Model{

    @Id
    private String id;
    private String src;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public static Model.Finder<String,Diet> find = new Model.Finder<String,Diet>(Diet.class);
    public static List<Diet> findAll() {
        return Diet.find.all();
    }

}
