package com.example.Classes;

import com.example.Exceptions.RoomNotAvailableException;

import java.time.LocalDate;

public class Receptionist extends Staff {
    public Receptionist(String username, String password, LocalDate dateOfBirth, int workingHours) throws Exception{
        super(username, password, dateOfBirth, Role.RECEPTIONIST, workingHours);
        super.setUserType("RECEPTIONIST");

        HotelDatabase.instance.addReceptionist(this);
    }



    public Reservation createReservation(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) throws RoomNotAvailableException {
        if (!room.isAvailable()) {
            throw new RoomNotAvailableException(room.getRoomNumber());
        }

        Reservation newRes = new Reservation(guest, room, checkIn, checkOut);
        HotelDatabase.instance.addReservation(newRes);
        return newRes;
    }

    public void checkIn(Reservation reservation) {

        if (reservation.getStatus() == Reservation.ReservationStatus.CONFIRMED) {

            System.out.println("Guest " + reservation.getGuest().getUsername() + " has checked in.");
        }
    }

    public void checkOut(Reservation reservation) {

        reservation.getRoom().setAvailable(true);
        reservation.setStatus(Reservation.ReservationStatus.COMPLETED);


        System.out.println("Check-out complete for Room: " + reservation.getRoom().getRoomNumber());
    }
}
