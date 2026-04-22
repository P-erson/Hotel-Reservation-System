package com.example;

import com.example.Exceptions.AmenityDoesNotExistException;
import com.example.Exceptions.RoomDoesNotExistException;

import java.time.LocalDate;
import java.util.ArrayList;


public class Admin extends Staff{
    public Admin(String username, String password, LocalDate dateOfBirth, int workingHours) throws Exception{
        super(username, password, dateOfBirth, Role.ADMIN, workingHours);

        DATABASE.addAdmins(this);
    }



    
    //Room CRUDs
    public void createRoom(int roomNumber, String roomType, ArrayList<Amenity> amenities, double pricePerNight){
        DATABASE.addRoom(new Room(roomNumber, roomType, amenities, pricePerNight));
    }

    public ArrayList<Room> viewRooms() { return DATABASE.getRooms(); }

    public void updateRoom(int roomNumber, String roomtype, ArrayList<Amenity> amenities, double pricePerNight) throws RoomDoesNotExistException{
        int roomNumberIndex = -1;

        for (int i = 0; i < DATABASE.getRooms().size(); i++){
            Room room = DATABASE.getRooms().get(i);

            if (room.getRoomNumber() == roomNumber){
                roomNumberIndex = i;
                break;
            }
        }

        if (roomNumberIndex == -1){
            throw new RoomDoesNotExistException(roomNumber);
        } else {
            DATABASE.setRoom(roomNumberIndex, new Room(roomNumber, roomtype, amenities,pricePerNight ));
        }
    }

    public void removeRoom(int roomNumber) throws RoomDoesNotExistException{
        int roomIndex = -1;

        for (int i = 0; i < DATABASE.getRooms().size(); i++){
            if (DATABASE.getRooms().get(i).getRoomNumber() == roomNumber) {
                roomIndex = i;
                break;
            }
        }

        if (roomIndex == -1) {
            throw new RoomDoesNotExistException(roomNumber);
        } else {
            Room roomObject = DATABASE.getRooms().get(roomIndex);
            DATABASE.removeRoom(roomObject);
        }
    }




    //Amenity CRUDs
    public void createAmenity(String name, int id, String description, double price){
        DATABASE.addAmenity(new Amenity(name, id, description, price));
    }

    public ArrayList<Amenity> viewAmenities(){ return DATABASE.getAmenities(); }

    public void updateAmenity(String originalAmenityName, String name, int id, String description, double price) throws AmenityDoesNotExistException{
        int originalAmenityIndex = -1;

        for (int i = 0; i < DATABASE.getAmenities().size(); i++){
            if (DATABASE.getAmenities().get(i).getName().equalsIgnoreCase(originalAmenityName)){
                originalAmenityIndex = i;
                break;
            }
        }

        if (originalAmenityIndex == -1){
            throw new AmenityDoesNotExistException(originalAmenityName);
        } else {
            DATABASE.setAmenity(originalAmenityIndex, new Amenity(name, id, description, price));
        }
    }

    public void removeAmenity(String amenity) throws AmenityDoesNotExistException{
        int amenityIndex = -1;

        for (int i = 0; i < DATABASE.getAmenities().size(); i++){
            if (DATABASE.getAmenities().get(i).getName().equalsIgnoreCase(amenity)) {
                amenityIndex = i;
                break;
            }
        }

        if (amenityIndex == -1) {
            throw new AmenityDoesNotExistException(amenity);
        } else {
            Amenity amenityObject = DATABASE.getAmenities().get(amenityIndex);
            DATABASE.removeAmenity(amenityObject);
        }
    }




    //Room Type CRUDs
    public void createRoomType(String roomType, Double roomPrice){ DATABASE.ROOM_TYPES.addType(roomType, roomPrice); }
    public ArrayList<String> viewRoomTypes(){ return DATABASE.ROOM_TYPES.getTypes(); }

    public void updateRoomType(String originalRoomType, String newRoomType, Double originalRoomPrice, Double newRoomPrice){
        DATABASE.ROOM_TYPES.update(originalRoomType, newRoomType, originalRoomPrice, newRoomPrice);
    }

    public void removeRoomType(String roomType){ DATABASE.ROOM_TYPES.removeType(roomType); }



    public void confirmReservation(Reservation reservation) throws Exception{
        if (reservation.getStatus() == Reservation.ReservationStatus.PENDING){
            reservation.setStatus(Reservation.ReservationStatus.CONFIRMED);
        } else {
            throw new Exception("Reservation can't be confirmed due to non-pending status: " + reservation.getStatus());
        }
    }
}
