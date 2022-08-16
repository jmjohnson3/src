import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

public class AdminMenu {
    public static List < IRoom > roomList = new ArrayList < > ();
    private static Collection < IRoom > roomsHash = new HashSet < > ();
    public static Map < String, IRoom > rooms = new HashMap < String, IRoom > ();


    private static Collection < Customer > Customerz = new HashSet < > ();
    private static AdminResource instAdminResource = AdminResource.getInstance();

    private static AdminMenu instAdminMenu = null;

    private AdminMenu() {

    };

    public static AdminMenu getInstance() {
        if (instAdminMenu == null) {
            instAdminMenu = new AdminMenu();
        }
        return instAdminMenu;
    }

    public static void openAdmin() {
        boolean keepRunning = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                try {
                    System.out.println("Welcome to the Hotel Reservation App Admin Menu");
                    System.out.println("1. See all Customers");
                    System.out.println("2. See all Rooms");
                    System.out.println("3. See all Reservations");
                    System.out.println("4. Add a Room");
                    System.out.println("5. Back to Main Menu");
                    int selection = Integer.parseInt(scanner.nextLine());
                    switch (selection) {
                        case 1:
                            Customerz = instAdminResource.getAllCustomers();
                            if (Customerz.isEmpty()) {
                                System.out.println("\nThere are no customers\n");
                                continue;
                            } else {

                                for (Customer cust: Customerz) {
                                    System.out.println(cust);
                                }

                            }
                            break;
                        case 2:
                            roomsHash = instAdminResource.getAllRooms();
                            if (roomsHash.isEmpty()) {
                                System.out.println("There are no rooms");
                                continue;
                            } else {

                                for (IRoom room: roomsHash) {
                                    System.out.println(room);
                                }
                            }
                            break;
                        case 3:
                            instAdminResource.displayAllReservation();
                            break;
                        case 4:
                            System.out.println("Add a room\n");
                            IRoom room;
                            String roomNumber = null;
                            Double roomPrice = null;
                            RoomType roomType = null;
                            boolean keepRunningType = true;
                            boolean keepRunningNumber = true;
                            boolean PricekeepRunning = true;
                            while (keepRunningNumber) {
                                try {
                                    Scanner numberScanner = new Scanner(System.in);
                                    System.out.println("Enter room number");
                                    roomNumber = numberScanner.nextLine();
                 
                            if (HotelResource.getRoom(roomNumber) == null) {
                                keepRunningNumber = false;
                                        
                            }
                            else{
                                System.out.println("Room number already exists");
                                System.out.println("Please enter another room number");

                            };
                                    
                                } catch (Exception ex) {
                                    continue;
                                }
                            }
                            
                        
                        while (PricekeepRunning) {
                            try {
                                Scanner priceScanner = new Scanner(System.in);
                                System.out.println("Enter the room price: ");
                                roomPrice = priceScanner.nextDouble();
                                priceScanner.nextLine();
                            } catch (Exception ex) {
                                ex.getLocalizedMessage();
                            }
                            PricekeepRunning = false;
                        }
                        while (keepRunningType) {
             try {
                    Scanner RoomTypescanner = new Scanner(System.in);
                    System.out.println("Enter room type, 1 for Single, 2 for double");
                    int Typeselection = Integer.parseInt(RoomTypescanner.nextLine());
                    switch (Typeselection) {
                        case 1:
                        roomType = model.RoomType.SINGLE;
                        break;
                        case 2:
                        roomType = model.RoomType.DOUBLE;
                        break;
                    }
                    } catch (Exception ex) {
                        System.out.println("\nError - Invalid input, please enter a number between 1 or 2\n");
                    } finally {
                        keepRunningType = false;
                    }
                }
            

                                        room = new Room(roomNumber, roomPrice, roomType);
                            roomList.add(room);
                            instAdminResource.addRoom(roomList);
                           break;
                        case 5:
                            MainMenu.MainMainMenu();
                            break;
                    }
                } catch (Exception ex) {
                    System.out.println("\nError - Invalid input, please enter a number between 1 and 5\n");
                }
            }
        }
    } {
        openAdmin();
    }
}