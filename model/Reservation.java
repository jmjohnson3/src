package model;

import java.sql.Date;

public class Reservation {
    Customer customer;
    public IRoom room;
    public java.util.Date inDate;
    public java.util.Date outDate;

    public Reservation(Customer customer, IRoom room, java.util.Date inDate, java.util.Date outDate) {
        this.customer = customer;
        this.room = room;
        this.inDate = inDate;
        this.outDate = outDate;

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public java.util.Date getinDate() {
        return inDate;
    }

    public void setinDate(Date inDate) {
        this.inDate = inDate;
    }

    public java.util.Date getoutDate() {
        return outDate;
    }

    public void setoutDate(Date outDate) {
        this.outDate = outDate;
    }

    @Override
    public String toString(){
        return "Customer: " + customer + ", Room: " + room + ", Check In Date: " + inDate + ", Check Out Date: " + outDate;
    }

    @Override
    public int hashCode() {
        return 0;
    }
        
    @Override
    public boolean equals(Object o) {
        return false;

    }
}