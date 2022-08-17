import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

public class MainMenu {
    private static HotelResource instHotelResource = HotelResource.getInstance();
    private static Collection < Reservation > reservation = new HashSet < > ();
    public static Collection < IRoom > freeRooms =  new HashSet < > ();

    public static void main(String[] args) {
        MainMainMenu();
    }
    public static void MainMainMenu() {
        boolean keepRunning = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (keepRunning) {
                try {
                    System.out.println("Welcome to the Hotel Reservation App Main Menu");
                    System.out.println("1. Find and reserve a room");
                    System.out.println("2. See my reservation");
                    System.out.println("3. Create an account");
                    System.out.println("4. Admin");
                    System.out.println("5. Exit");
                    int selection = Integer.parseInt(scanner.nextLine());
                    switch (selection) {
                        case 1:
                            String yn;
                            boolean roomKeepRunning = true;
                            java.util.Date inDate = getDate("In Date");
                            java.util.Date outDate = getDate("Out Date");
                            Collection < IRoom > freeRooms = instHotelResource.findARoom(inDate, outDate);

                            if (!freeRooms.isEmpty()) {
                                System.out.println("Room(s) available!\n");
                                printRooms(freeRooms);
                                System.out.println("\n");

                            } else {
                                Calendar rCheckin = Calendar.getInstance();
                                Calendar rCheckout = Calendar.getInstance();

                                rCheckin.setTime(inDate);
                                rCheckout.setTime(outDate);
                                rCheckin.add(Calendar.DAY_OF_MONTH, 7);
                                rCheckout.add(Calendar.DAY_OF_MONTH, 7);

                                inDate = rCheckin.getTime();
                                outDate = rCheckout.getTime();

                                freeRooms = instHotelResource.findARoom(inDate, outDate);
                                printRooms(freeRooms);
                                if (!freeRooms.isEmpty()) {
                                    System.out.println("No rooms available in the range of date. \nAlternative Room:");
                                    System.out.println("Checkin Date: " + inDate);
                                    System.out.println("Checkout Date:" + outDate);
                                    printRooms(freeRooms);

                                } else {
                                    System.out.println("\nThere is no room available.\n");
                                    MainMenu.MainMainMenu();

                                }
                            }
                            Scanner ynScanner = new Scanner(System.in);
                            while (roomKeepRunning) {
                                try {
                                    System.out.println("Would you like to book a room? y/n");
                                    yn = ynScanner.nextLine();
                                    if (yn.equalsIgnoreCase("y")) {
                                        String bookyn;
                                        IRoom room;
                                        String email = null;
                                        boolean bookRoomKeepRunning = true;
                                        boolean getNumberKeepRunning = true;
                                
                                        while (bookRoomKeepRunning) {
                                            try {
                                                Scanner bookynScanner = new Scanner(System.in);
                                                System.out.println("Do you have an account? y/n");
                                                bookyn = bookynScanner.nextLine();
                                                if (bookyn.equalsIgnoreCase("y")) {
                                                    email = getEmail();
                                
                                                    Customer customer = instHotelResource.getCustomer(email);
                                                    if (customer.equals(null)) {
                                                        System.out.println("No email");
                                                        throw new IllegalArgumentException();
                                                    }
                                                    
                                        while (getNumberKeepRunning) {
                                            try {
                                                Scanner roomScanner = new Scanner(System.in);
                                                System.out.println("What room number would you like to reserve");
                                                String Roomselection = roomScanner.nextLine();       
                                                if(Roomselection.contains(((model.Room)freeRooms.toArray()[0]).number)){
                                                room = instHotelResource.getRoom(Roomselection);
                                                instHotelResource.bookARoom(email, room, inDate, outDate);
                                                getNumberKeepRunning = false;
                                                }
                                                else{
                                                    System.out.println("That room has already been booked.\nSelect another room");
                                                }
                                            }
                                        
                                        catch (Exception ex) {
                                            System.out.println("Error - Invalid input");
                                        } finally {
                                            bookRoomKeepRunning = false;
                                        }
                                        }
                                    }
                                    
                                             else if  (yn.equalsIgnoreCase("n")) {
                                                MainMenu.MainMainMenu();
                                            }
                                        
                                        
                                        bookRoomKeepRunning = false;
                                        } finally {
                                            bookRoomKeepRunning = false;
                                        }
                                    }                                        

                                    } else if (yn.equalsIgnoreCase("n")) {
                                        MainMenu.MainMainMenu();
                                    } else {
                                        System.out.println("Please enter only y or n");
                                    }
                                } catch (Exception ex) {
                                    System.out.println("\nError - Invalid input\n");
                                }

                                roomKeepRunning = false;
                            }
                            break;
                        case 2:

                            String Email = getEmail();

                            reservation = instHotelResource.getCustomerReservations(Email);
                            if (reservation.isEmpty()) {
                                System.out.println("You have no reservations");
                                MainMenu.MainMainMenu();
                            } else {

                                for (Reservation currentReservation: reservation) {

                                    System.out.println(currentReservation);
                                }
                            }
                            MainMenu.MainMainMenu();
                            break;

                        case 3:
                            System.out.println("Create an account");

                            String email = getEmail();
                            String fname = getfname("Please enter your capitalized First Name");
                            String lname = getlname("Please enter your capitalized Last Name");
                            instHotelResource.createACustomer(email, fname, lname);
                            break;
                        case 4:
                            AdminMenu.openAdmin();
                            break;
                        case 5:
                            keepRunning = false;
                            System.exit(0);
                            break;
                        default: System.out.println("\nError - Invalid input, please enter a number between 1 and 5\n");
                            break;
                    }
                } catch (Exception ex) {
                    System.out.println("\nError - Invalid input, please enter a number between 1 and 5\n");
                }
            }
        }
    }

    private static String getlname(String string) {
        String lname = null;
        String lnameRegex = "[A-Z]+([ '-]*[a-zA-Z]+)*";
        Pattern pattern = Pattern.compile(lnameRegex);
        boolean lanemKeepRunning = true;

        while (lanemKeepRunning) {
            try {

                Scanner lnameScanner = new Scanner(System.in);
                System.out.println("Enter your capitalized Last Name");
                lname = lnameScanner.nextLine();

                if (!pattern.matcher(lname).matches()) {
                    throw new IllegalArgumentException();

                } else {
                    lanemKeepRunning = false;
                }

            } catch (Exception ex) {
                System.out.println("Error, Invalid Last Name");

            }
        }

        return lname;

    }

    private static String getfname(String string) {
        String fname = null;
        String fnameRegex = "[A-Z]+([ '-]*[a-zA-Z]+)*";
        Pattern pattern = Pattern.compile(fnameRegex);
        boolean fnameKeepRunning = true;

        while (fnameKeepRunning) {
            try {

                Scanner fnameScanner = new Scanner(System.in);
                System.out.println("Enter your capitalized First Name");
                fname = fnameScanner.nextLine();

                if (!pattern.matcher(fname).matches()) {
                    throw new IllegalArgumentException();

                } else {
                    fnameKeepRunning = false;
                }
            } catch (Exception ex) {
                System.out.println("Error, Invalid First Name");

            }
        }
        return fname;
    }

    private static String getEmail() {
        String email = null;
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        boolean emailKeepRunning = true;

        while (emailKeepRunning) {
            try {

                Scanner emailScanner = new Scanner(System.in);
                System.out.println("Enter Email format: name@domain.com");
                email = emailScanner.nextLine();

                if (!pattern.matcher(email).matches()) {
                    throw new IllegalArgumentException();

                } else {
                    emailKeepRunning = false;
                }
            } catch (Exception ex) {
                System.out.println("Error, Invalid email format");

            }

        }

        return email;

    }

    public static java.util.Date getDate(String dateType) {
        java.util.Date formatedDate = null;
        boolean DateKeepRunning = true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        while (DateKeepRunning) {


            System.out.println("Enter check " + dateType + " mm/dd/yyyy example 02/01/2020");

            try {

                Scanner DateScanner = new Scanner(System.in);
                String date = DateScanner.nextLine();
                formatedDate = dateFormat.parse(date);

            } catch (Exception ex) {
                System.out.println("Error -  Invalid Input");
                continue;
            }
            DateKeepRunning = false;
        }
        return formatedDate;
    }

    private static void printRooms(Collection < IRoom > rooms) {
        if (rooms.isEmpty()) {
        } else {
            rooms.forEach(System.out::println);
        }
    }
}