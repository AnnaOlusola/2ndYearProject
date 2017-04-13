package models;

        import models.users.Member;
        import play.data.format.*;
        import play.data.validation.*;
        import com.avaje.ebean.*;
        import play.data.format.*;

        import javax.persistence.*;

        import java.util.Date;
        import java.util.List;

@Entity
public class YourBooking extends Model{

  @Id
    private Long id;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.PERSIST)
    private List<BookingItem> yourBookingItems;

    @OneToOne
    private Member member;

    public YourBooking(){

    }

    //Generic query helper
    public static Finder<Long,YourBooking> find = new Finder<Long,YourBooking>(YourBooking.class);

    //Find all Products in the database
    public static List<YourBooking> findAll() {
        return YourBooking.find.all();
    }



    public void addTimeSlot(TimeSlot p) {

        boolean itemFound = false;
        // Check if product already in this basket
        // Check if item in basket
        // Find bookingitem with this product
        // if found increment quantity
        for (BookingItem i : yourBookingItems) {
            if (i.getTimeSlot().getId() == p.getId()) {

                itemFound = true;
                break;
            }
        }
        if (itemFound == false) {
            // Add orderItem to list
            BookingItem newItem = new BookingItem(p);
            // Add to items
            yourBookingItems.add(newItem);
        }
    }

 

    public void removeAllItems() {
        for(BookingItem i: this.yourBookingItems) {
            i.delete();
        }
        this.yourBookingItems = null;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BookingItem> getYourBookingItems() {
        return yourBookingItems;
    }

    public void setYourBookingItems(List<BookingItem> yourBookingItems) {
        this.yourBookingItems = yourBookingItems;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
