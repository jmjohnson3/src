package api;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

public class HotelResource {/*
    public static HotelResource instHotelResource = null;
    public static CustomerService customerService = CustomerService.getInstance();
    public static ReservationService reservationService = ReservationService.getInstance();

    

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);

    }

    public void createACustomer(String email, String fname, String lname){

        customerService.addCustomer(email, fname, lname);
        return;

    }

    public IRoom getRoom(String number){
        return ReservationService.getARoom(number);

    }

    public Reservation bookARoom(Customer customer, IRoom room, java.util.Date inDate, java.util.Date outDate){
        CustomerService.getInstance();
        //Customer customer = CustomerService.getCustomer(customerEmail);
        return ReservationService.reserveARoom(customer, room, inDate, outDate);
        
    }

    //public Collection<Reservation> getCustomersReservation(Customer customerEmail){
        //Customer customer = getCustomer(customerEmail);
        //return ReservationService.getCustomersReservation(customerEmail);
        
    //}

    public Collection<IRoom> findARoom(java.util.Date inDate, java.util.Date outDate){

        return reservationService.findRooms(inDate, outDate);
        
    }
    public Collection<IRoom> alternativeRooms(java.util.Date inDate, java.util.Date outDate) {
        return reservationService.findRooms(inDate,outDate);
    }

    public static HotelResource getInstance(){
        if (instHotelResource ==  null) {
            instHotelResource = new HotelResource();
        }
        return instHotelResource;
    }

    public Collection<Reservation> getCustomersReservations(String Email) {
        Collection<Reservation> reservationsPerCust= new HashSet<>();
        //Customer customer =  null;
        Customer customer = getCustomer(Email);
        try {

            if (customer.equals(null)) {

                throw new NullPointerException();
            }
        } catch (Exception ex)
        {
            System.out.println("No email found");

            return reservationsPerCust; 
        }
        reservationsPerCust =  ReservationService.getCustomersReservation(customer);
        return reservationsPerCust;
    }*/
    private static HotelResource hotelResource = null;
    private HotelResource(){}
    public static HotelResource getInstance() {
        if (null == hotelResource) {
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    private CustomerService customerService = CustomerService.getInstance();
    private static ReservationService reservationService = ReservationService.getInstance();


    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String fname, String lname) {
        customerService.addCustomer(email, fname, lname);
    }

    public static IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String email, IRoom room,
                                 java.util.Date inDate, java.util.Date outDate) {
        return reservationService.reserveARoom(getCustomer(email)
                , room, inDate, outDate);
    }
    public Collection<Reservation> getCustomerReservations (String email) {
        Customer customer = getCustomer(email);

        if (customer == null) {
            return Collections.emptyList();
        }

        return reservationService.getCustomersReservation(getCustomer(email));
    }

    public Collection<IRoom> findARoom(java.util.Date inDate, java.util.Date outDate) {
        return reservationService.findRooms(inDate, outDate);
    }


}
