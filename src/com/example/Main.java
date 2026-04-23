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
                if (res.getRoom() != null) {
                    System.out.println("Guest: " + res.getGuest().getUsername() + " | Room: " + res.getRoom().getRoomNumber() + " | Status: " + res.getStatus());
                } else {
                    System.out.println("Empty reservation | Status: " + res.getStatus());
                }
            }


            // 2. GUEST REGISTRATION with validation loops
            System.out.println("           GUEST REGISTRATION");


            String username = null;
            while (username == null) {
                System.out.print("Enter username: ");
                String input = scanner.nextLine();
                try {
                    if (input == null || input.isBlank()) throw new Exception("Username cannot be blank.");
                    username = input;
                } catch (Exception e) {
                    System.out.println("Invalid username: " + e.getMessage() + " Please try again.");
                }
            }


            String password = null;
            while (password == null) {
                System.out.print("Enter password (min 8 characters): ");
                String input = scanner.nextLine();
                try {
                    if (input == null || input.isBlank() || input.length() < 8)
                        throw new Exception("Password must be at least 8 characters.");
                    password = input;
                } catch (Exception e) {
                    System.out.println("Invalid password: " + e.getMessage() + " Please try again.");
                }
            }


            LocalDate dob = null;
            while (dob == null) {
                System.out.print("Enter date of birth (YYYY-MM-DD): ");
                String input = scanner.nextLine();
                try {
                    LocalDate parsed = LocalDate.parse(input);
                    if (!parsed.isBefore(LocalDate.now()) || !parsed.isAfter(LocalDate.of(1900, 1, 1)))
                        throw new Exception("Date must be in the past and after 1900.");
                    dob = parsed;
                } catch (Exception e) {
                    System.out.println("Invalid date: " + e.getMessage() + " Please try again.");
                }
            }


            System.out.print("Enter address: ");
            String address = scanner.nextLine();


            System.out.print("Enter room preferences: ");
            String preferences = scanner.nextLine();


            double balance = -1;
            while (balance < 0) {
                System.out.print("Enter initial balance: ");
                try {
                    balance = Double.parseDouble(scanner.nextLine());
                    if (balance < 0) throw new Exception("Balance cannot be negative.");
                } catch (Exception e) {
                    System.out.println("Invalid balance: " + e.getMessage() + " Please try again.");
                    balance = -1;
                }
            }


            Guest.Gender gender = null;
            while (gender == null) {
                System.out.print("Enter gender (MALE/FEMALE): ");
                try {
                    gender = Guest.Gender.valueOf(scanner.nextLine().toUpperCase());
                } catch (Exception e) {
                    System.out.println("Invalid gender. Please enter MALE or FEMALE.");
                }
            }


            Guest newGuest = null;
            try {
                newGuest = new Guest(username, password, dob, address, preferences, balance, gender);
                System.out.println("\nRegistration successful! Welcome, " + newGuest.getUsername());
            } catch (Exception e) {
                System.out.println("Registration failed: " + e.getMessage());
                return;
            }


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
                    loggedInGuest = newGuest.login(loginUsername, loginPassword);
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
                for (Room r : availableRooms) {
                    System.out.println("Room " + r.getRoomNumber() + " | Type: " + r.getType() + " | Price per night: $" + r.getPricePerNight());
                }
            }




            // 5. MAKE A RESERVATION


            System.out.println("\n========================================");
            System.out.println("          MAKE A RESERVATION");
            System.out.println("========================================");


            if (!availableRooms.isEmpty()) {
                Room roomToBook = availableRooms.get(0);
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


                    ArrayList<Reservation> myReservations = loggedInGuest.viewReservations();
                    for (Reservation res : myReservations) {
                        if (res.getRoom() != null) {
                            System.out.println("Room: " + res.getRoom().getRoomNumber() + " | Status: " + res.getStatus() + " | Check-in: " + res.getCheckInDate() + " | Check-out: " + res.getCheckOutDate());
                        }
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



