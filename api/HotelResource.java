package api;

import java.util.Collection;
import java.util.Collections;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

public class HotelResource {
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
