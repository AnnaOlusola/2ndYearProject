package models;
import models.users.Member;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.*;
import play.data.format.*;

import javax.persistence.*;

import java.util.Date;
@Entity
public class Progress extends Model{

    @Id
    @Formats.DateTime(pattern="dd-MMM-yyyy")
    private Date date;
    @Constraints.Required
    private double height;
    @Constraints.Required
    private double weight;
    @Constraints.Required
    private double waist;
    @Constraints.Required
    private double thigh;

    @ManyToOne
    private Member member;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getThigh() {
        return thigh;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }
}