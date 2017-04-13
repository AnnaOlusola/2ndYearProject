 package models.users;

public class Login {
    private String email;
    private String password;

    //validate method-invoked during error checking after form, based on a login object has been submitted
    public String validate() {
        //call the static authenticate method in user
        if (User.authenticate(getEmail(), getPassword()) == null) {
            return "UserName or Password incorrect";
                    //.......................................................................................................pop up here
        }
        return null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
