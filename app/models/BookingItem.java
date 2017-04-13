package models;
import com.avaje.ebean.*;

import javax.persistence.*;

import java.util.List;

@Entity
    public class BookingItem extends Model {

        @Id
        private Long id;

        @ManyToOne
        private BookingOrder order;

        @ManyToOne
        private YourBooking basket;



        @ManyToOne
        private TimeSlot timeSlot;

        private int quantity;
        // Default constructor
        public  BookingItem() {
        }

        public BookingItem(TimeSlot p) {
            setTimeSlot(p);
            setQuantity(1);

        }

        //Generic query helper
        public static Finder<Long,BookingItem> find = new Finder<Long,BookingItem>(BookingItem.class);

        //Find all Products in the database
        public static List<BookingItem> findAll() {
            return BookingItem.find.all();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public BookingOrder getOrder() {
            return order;
        }

        public void setOrder(BookingOrder order) {
            this.order = order;
        }

        public YourBooking getBasket() {
            return basket;
        }

        public void setBasket(YourBooking basket) {
            this.basket = basket;
        }

        public TimeSlot getTimeSlot() {
            return timeSlot;
        }

        public void setTimeSlot(TimeSlot timeSlot) {
            this.timeSlot = timeSlot;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
