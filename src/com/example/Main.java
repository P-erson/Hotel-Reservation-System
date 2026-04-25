package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {



    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {


        System.out.println("   Welcome to the Hotel Reservation System");




        System.out.println("-Pre-populated Guests in Database -");
        for (Guest g : User.DATABASE.getGuests()) {
            System.out.println("Username: " + g.getUsername() + " | Balance: " + g.getBalance() + " | Gender: " + g.getGender());
        }


        System.out.println("\n- Pre-populated Rooms in Database -");
        for (Room r : User.DATABASE.getRooms()) {
            System.out.println("Room " + r.getRoomNumber() + " | Type: " + r.getType() + " | Available: " + r.isAvailable());
        }


        System.out.println("\n- Pre-populated Reservations in Database -");
        for (Reservation res : User.DATABASE.getReservations()) {
            if (res.getRoom() != null && res.getGuest() != null) {
                System.out.println("Guest: " + res.getGuest().getUsername() + " | Room: " + res.getRoom().getRoomNumber() + " | Status: " + res.getStatus());
            } else {
                System.out.println("Empty reservation | Status: " + res.getStatus());
            }
        }


        // 2. GUEST REGISTRATION with validation loops
        System.out.println("           GUEST REGISTRATION");

        Guest g = new Guest();

        {
            String input = null;
            while (input == null) {
                System.out.print("Enter username: ");
                input = scanner.nextLine();
                try {
                    g.setUsername(input);
                } catch (Exception e) {
                    System.out.println("Invalid username: " + e.getMessage() + " Please try again.");
                    input = null;
                }
            }
        }

        {
            String input = null;
            while (input == null) {
                System.out.print("Enter password (min 8 characters): ");
                input = scanner.nextLine();
                try {
                    g.setPassword(input);
                } catch (Exception e) {
                    System.out.println("Invalid password: " + e.getMessage() + " Please try again.");
                    input = null;
                }
            }
        }

        {
            LocalDate input = null;
            while (input == null) {
                System.out.print("Enter date of birth (YYYY-MM-DD): ");
                try {
                    input = LocalDate.parse(scanner.nextLine());
                    g.setDateOfBirth(input);
                } catch (Exception e) {
                    System.out.println("Invalid date: " + e.getMessage() + ". Please try again.");
                    input = null;
                }
            }
        }


        System.out.print("Enter address: ");
        g.setAddress(scanner.nextLine());


        System.out.print("Enter room preferences: ");
        g.setRoomPreferences(scanner.nextLine());

        {
            double balance = -1;
            while (balance == -1) {
                System.out.print("Enter initial balance: ");
                try {
                    balance = scanner.nextInt();
                    g.setBalance(balance);
                } catch (Exception e) {
                    System.out.println("Invalid balance: " + e.getMessage() + " Please try again.");
                    balance = -1;
                }
            }
        }

        {
            Guest.Gender gender = null;
            while (gender == null) {
                System.out.print("Enter gender (MALE/FEMALE): ");
                try {
                    gender = Guest.Gender.valueOf(scanner.nextLine().toUpperCase());
                    g.setGender(gender);
                } catch (Exception e) {
                    System.out.println("Invalid gender. Please enter MALE or FEMALE.");
                    gender = null;
                }
            }
        }

        // Add the newly registered guest to the database
        User.DATABASE.addGuest(g);
        System.out.println("Registration successful!");

        // 3. GUEST LOGIN


        System.out.println("\n========================================");
        System.out.println("              GUEST LOGIN");
        System.out.println("========================================");


        Guest loggedInGuest = null;
        while (loggedInGuest == null) {
            System.out.print("Enter username: ");
            String loginUsername = scanner.nextLine();
            System.out.print("Enter password: ");
            String loginPassword = scanner.nextLine();
            try {
                loggedInGuest = Guest.login(loginUsername, loginPassword);
                System.out.println("Login successful! Welcome back, " + loggedInGuest.getUsername());
            } catch (Exception e) {
                System.out.println("Login failed: " + e.getMessage() + " Please try again.");
            }
        }


        // 4. VIEW AVAILABLE ROOMS


        System.out.println("\n========================================");
        System.out.println("          AVAILABLE ROOMS");
        System.out.println("========================================");


        ArrayList<Room> availableRooms = loggedInGuest.viewAvailableRooms();
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available at the moment.");
        } else {
            for (Room availableRoom : availableRooms) {
                System.out.println("Room " + availableRoom.getRoomNumber() + " | Type: " + availableRoom.getType() + " | Price per night: $" + availableRoom.getPricePerNight());
            }
        }




        // 5. MAKE A RESERVATION


        System.out.println("\n========================================");
        System.out.println("          MAKE A RESERVATION");
        System.out.println("========================================");


        if (!(availableRooms.isEmpty())) {
            Room roomToBook = availableRooms.getFirst();
            LocalDate checkIn = LocalDate.now().plusDays(1);
            LocalDate checkOut = LocalDate.now().plusDays(3);


            try {
                Reservation newReservation = loggedInGuest.makeReservation(roomToBook, checkIn, checkOut);
                System.out.println("Reservation made successfully!");
                System.out.println("Room: " + newReservation.getRoom().getRoomNumber() + " | Check-in: " + checkIn + " | Check-out: " + checkOut);
                System.out.println("Status: " + newReservation.getStatus());




                // 6. VIEW RESERVATIONS


                System.out.println("\n========================================");
                System.out.println("         YOUR RESERVATIONS");
                System.out.println("========================================");


                try {
                    ArrayList<Reservation> myReservations = loggedInGuest.viewReservations();
                    for (Reservation myReservation : myReservations) {
                        if (myReservation.getRoom() != null) {
                            System.out.println("Room: " + myReservation.getRoom().getRoomNumber() + " | Status: " + myReservation.getStatus() + " | Check-in: " + myReservation.getCheckInDate() + " | Check-out: " + myReservation.getCheckOutDate());
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }


                // 7. CANCEL A RESERVATION — using a second room
                System.out.println("\n========================================");
                System.out.println("         CANCEL A RESERVATION");
                System.out.println("========================================");


                if (availableRooms.size() > 1) {
                    Room roomToCancel = availableRooms.get(1);
                    loggedInGuest.makeReservation(roomToCancel, checkIn, checkOut);
                    System.out.println("Made a second reservation for Room " + roomToCancel.getRoomNumber() + " to demonstrate cancellation.");


                    loggedInGuest.cancelReservation(roomToCancel.getRoomNumber());
                    System.out.println("Reservation for Room " + roomToCancel.getRoomNumber() + " cancelled successfully!");
                    System.out.println("Room " + roomToCancel.getRoomNumber() + " is now available: " + roomToCancel.isAvailable());
                } else {
                    System.out.println("Not enough rooms to demonstrate cancellation.");
                }




                // 8. CHECKOUT
                System.out.println("\n========================================");
                System.out.println("              CHECKOUT");
                System.out.println("========================================");


                System.out.println("Guest balance before checkout: $" + loggedInGuest.getBalance());
                System.out.println("Checking out from Room " + roomToBook.getRoomNumber() + "...");


                try {
                    Invoice invoice = loggedInGuest.checkout(roomToBook.getRoomNumber(), Invoice.PaymentMethod.CASH);
                    System.out.println("Checkout successful!");
                    System.out.println("Total Amount: $" + invoice.getTotalAmount());
                    System.out.println("Payment Method: " + invoice.getMethods());
                    System.out.println("Payment Date: " + invoice.getPaymentDate());
                    System.out.println("Guest balance after checkout: $" + loggedInGuest.getBalance());
                } catch (Exception e) {
                    System.out.println("Checkout failed: " + e.getMessage());
                }


            } catch (Exception e) {
                System.out.println("Reservation failed: " + e.getMessage());
            }
        } else {
            System.out.println("No available rooms to make a reservation.");
        }


        System.out.println("     ENd of test");
        scanner.close();
    }
}



