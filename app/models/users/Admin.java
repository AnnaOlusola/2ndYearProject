package models.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.*;
import javax.persistence.*;

import models.Appointment;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.*;

@Entity
// This is a User of type admin
@DiscriminatorValue("admin")

// Administrator inherits from the User class
public class Admin extends User{


        @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
        private List<Appointment> adminApList;


        public Admin()
        {

        }

public Admin(String email, String role, String firstName, String lastName, String password)
        {
        super(email, role, firstName, lastName, password);
        }

        }
