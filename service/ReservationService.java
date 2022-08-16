package service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

public class ReservationService {
    public static HashMap < String, IRoom > rooms = new HashMap < String, IRoom > ();
    public static Map < String, IRoom > mapOfRooms = new HashMap < String, IRoom > ();
    public static Map < String, Collection < Reservation >> reservations = new HashMap < String, Collection < Reservation >> ();
    private static Collection < Reservation > reservationsHash = new HashSet < > ();
    private static Collection < IRoom > roomHash = new HashSet < > ();
    private static ReservationService reservationService = null;
    private ReservationService() {};
    public static ReservationService getInstance() {
        if (null == reservationService) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room) {
        roomHash.add((Room) room);
        mapOfRooms.put(room.getNumbeString(), room);

    }

    public IRoom getARoom(String roomNumber) {
        return mapOfRooms.get(roomNumber);
    }


    public Reservation reserveARoom(Customer customer, IRoom room, java.util.Date inDate,
        java.util.Date outDate) {
        Reservation reservation = new Reservation(customer, room, inDate, outDate);
        Collection < Reservation > CuReservations = getCustomersReservation(customer);

        if (CuReservations == null) {
            CuReservations = new LinkedList < > ();
        }

            CuReservations.add(reservation);
            reservationsHash.add(reservation);        
            reservations.put(customer.getEmail(), CuReservations);
            return reservation;       
    }

    public Collection < Reservation > getCustomersReservation(Customer customer) {
        return reservations.get(customer.getEmail());
    }

    public void printAllReservation() {
        if (reservationsHash.isEmpty()) {
            System.out.println("There are no reservations");

        } else {
            printReservedRooms();
        }
        return;
    }


    private void printReservedRooms() {
        for (Reservation reservation: reservationsHash) {
            System.out.println(reservation);
        }
    }

    public Collection < IRoom > findRooms(java.util.Date inDate, java.util.Date outDate) {
        Collection < IRoom > freeRooms = new HashSet < > ();
        if (reservations.size() == 0) {
            freeRooms = roomHash;
            return freeRooms;
        }
        for (Reservation reservation: reservationsHash) {
            for (IRoom room: roomHash) {
                if ((room.getNumbeString()==(reservation.getRoom().getNumbeString())) ||
                    ((inDate.before(reservation.getinDate()) && outDate.before(reservation.getinDate())) ||
                        (inDate.after(reservation.getoutDate()) && outDate.after(reservation.getoutDate()))) ||
                    (reservation.getRoom().getNumbeString()!=(room.getNumbeString()))) {
                    freeRooms.add(room);
            }
        }
        }
        fixOutput(freeRooms, inDate, outDate);
        return freeRooms;
    }
    private static void fixOutput(Collection < IRoom > freeRooms, java.util.Date inDate, java.util.Date outDate) {
        for (Reservation reservation: reservationsHash) {
            for (IRoom room: roomHash) {
                if (room.getNumbeString()==(reservation.getRoom().getNumbeString()) &&
                    !((inDate.before(reservation.getinDate()) && 
                    outDate.before(reservation.getinDate())) &&
                        (inDate.after(reservation.getoutDate()) && 
                        outDate.after(reservation.getoutDate()))))
                    if (!inDate.after(reservation.getinDate()) && 
                    !inDate.before(reservation.getoutDate()) ||
                        (!outDate.after(reservation.getinDate()) && 
                        !outDate.before(reservation.getoutDate())) ||
                        (inDate.equals(reservation.getinDate())) &&
                        (outDate.equals(reservation.getoutDate())))
                        freeRooms.remove(room);
            }
        }
    }

    public static Collection < Reservation > getAllReservation(Customer customer) {
        for (Reservation reservation: reservationsHash) {
            if (reservation.getCustomer().equals(customer)) {
                reservationsHash.add(reservation);
            }
        }
        return reservationsHash;
    }

    public static Collection < IRoom > getAllRooms() {
        return mapOfRooms.values();
    }



}