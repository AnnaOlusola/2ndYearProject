package models.users;
import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.Model;
import models.Appointment;
import models.BookingOrder;
import models.Progress;
import models.YourBooking;
import play.data.format.*;
import play.data.validation.*;


@Entity
@DiscriminatorValue("member")
public class Member extends User {


    @Constraints.Required
    private String addressLine1;
    @Constraints.Required
    private String addressLine2;
    @Constraints.Required
    private String addressLine3;
    //@Constraints.Required
    private String phoneNumber;
    //@Constraints.Required
    private String medicalCondition;
    //@Constraints.Required
    private String doctorsName;
    //@Constraints.Required
    private String doctorsPhone;
    //@Constraints.Required
    private String emergencyName;
    //@Constraints.Required
    private String emergencyPhone;
    //@Constraints.Required
    private String relationship;
    //@Constraints.Required
    private String securityQuestion;
   // @Constraints.Required
    private String securityAnswer;


@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
private List<Appointment> apList;

@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
private List<Progress> progressList;
//private java.sql.Date
//    dateOfBirth

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private YourBooking basket;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<BookingOrder> orders;


    public Member(String email, String role, String firstName, String lastName, String password, String addressLine1, String addressLine2, String addressLine3, String phoneNum, String medicalCondition, String doctorsName, String doctorsPhone, String emergencyName, String emergencyPhone, String relationship, String securityQuestion, String securityAnswer, List<Appointment> apList, List<Progress> progressList) {
        super(email,role,firstName, lastName, password);
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.phoneNumber = phoneNumber;
        this.medicalCondition = medicalCondition;
        this.doctorsName = doctorsName;
        this.doctorsPhone = doctorsPhone;
        this.emergencyName = emergencyName;
        this.emergencyPhone = emergencyPhone;
        this.relationship = relationship;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.apList = apList;
        this.progressList = progressList;
    }
    public static Finder<String,Member> find = new Finder<String,Member>(Member.class);
    public static List<Member> findAll() {
        return Member.find.all();
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public String getDoctorsName() {
        return doctorsName;
    }

    public void setDoctorsName(String doctorsName) {
        this.doctorsName = doctorsName;
    }

    public String getDoctorsPhone() {
        return doctorsPhone;
    }

    public void setDoctorsPhone(String doctorsPhone) {
        this.doctorsPhone = doctorsPhone;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

 public YourBooking getBasket() {
        return basket;
    }

    public void setBasket(YourBooking basket) {
        this.basket = basket;
    }

    public List<BookingOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<BookingOrder> orders) {
        this.orders = orders;
    }

//    public java.sql.Date getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(java.sql.Date dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
}
