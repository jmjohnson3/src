package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer { 
    private final String fname;
    private final String lname;
    private final String email;

    public Customer(String fname, String lname, String email) {
        this.fname = fname;
        this.lname = lname;
        final String emailRegex = "^(.+)@(.+).(.+)$";
        final Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Email does not match the format 'name@domain.com'");
        }
        this.email = email;
    }

    public String getEmail() {

        return email;
    }


    @Override
    public String toString() {
        return "First Name: " + fname + ", Last Name: " + lname + ", Email: " + email;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(fname, customer.fname) && Objects.equals(lname, customer.lname) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fname, lname, email);
    }
}
