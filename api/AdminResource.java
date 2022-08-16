package api;

import java.util.Collection;
import java.util.List;


import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

public class AdminResource {
    public static ReservationService reservationService = ReservationService.getInstance();
    
    public static AdminResource instAdminResource = null;
    public static CustomerService customerService = CustomerService.getInstance();

    private AdminResource(){    }

    public static AdminResource getInstance(){
        if (instAdminResource ==  null) {
            instAdminResource = new AdminResource();
        }
        return instAdminResource;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
        
    }

    public void addRoom(List<IRoom> rooms){
       rooms.forEach(reservationService::addRoom);
       System.out.println(rooms);
    }


    public Collection<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();

    }

    public void displayAllReservation(){
        reservationService.printAllReservation();
    }
}
