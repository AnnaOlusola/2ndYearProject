package models;
import models.users.Member;
import com.avaje.ebean.*;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
@Entity
public class BookingOrder extends Model{

    @Id
    private Long id;

    private Date bookingDate;

    // Order contains may items.
    // mappedBy makes this side of the mapping the owner
    // so foreign key will be placed in resulting OrderItem table
    // All changes, including deletes will be cascaded
    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<BookingItem> items;

    @ManyToOne
    private Member member;

    // Default constructor
    public  BookingOrder() {
        setBookingDate(new Date());
    }

    //Generic query helper
    public static Finder<Long,BookingOrder> find = new Finder<Long,BookingOrder>(BookingOrder.class);

    //Find all Products in the database
    public static List<BookingOrder> findAll() {
        return BookingOrder.find.all();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public List<BookingItem> getItems() {
        return items;
    }

    public void setItems(List<BookingItem> items) {
        this.items = items;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
