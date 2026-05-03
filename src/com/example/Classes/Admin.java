package com.example.Classes;

import com.example.Exceptions.AmenityDoesNotExistException;
import com.example.Exceptions.RoomDoesNotExistException;

import java.time.LocalDate;
import java.util.ArrayList;


public class Admin extends Staff{
    public Admin(String username, String password, LocalDate dateOfBirth, int workingHours) throws Exception{
        super(username, password, dateOfBirth, Role.ADMIN, workingHours);
        super.setUserType("ADMIN");

        HotelDatabase.instance.addAdmins(this);
    }



    
    //Room CRUDs
    public void createRoom(int roomNumber, String roomType, ArrayList<Amenity> amenities, double pricePerNight){
        HotelDatabase.instance.addRoom(new Room(roomNumber, roomType, amenities, pricePerNight));
    }

    public ArrayList<Room> viewRooms() { return HotelDatabase.instance.getRooms(); }

    public void updateRoom(int roomNumber, String roomtype, ArrayList<Amenity> amenities, double pricePerNight) throws RoomDoesNotExistException {
        int roomNumberIndex = -1;

        for (int i = 0; i < HotelDatabase.instance.getRooms().size(); i++){
            Room room = HotelDatabase.instance.getRooms().get(i);

            if (room.getRoomNumber() == roomNumber){
                roomNumberIndex = i;
                break;
            }
        }

        if (roomNumberIndex == -1){
            throw new RoomDoesNotExistException(roomNumber);
        } else {
            HotelDatabase.instance.setRoom(roomNumberIndex, new Room(roomNumber, roomtype, amenities,pricePerNight ));
        }
    }

    public void removeRoom(int roomNumber) throws RoomDoesNotExistException{
        int roomIndex = -1;

        for (int i = 0; i < HotelDatabase.instance.getRooms().size(); i++){
            if (HotelDatabase.instance.getRooms().get(i).getRoomNumber() == roomNumber) {
                roomIndex = i;
                break;
            }
        }

        if (roomIndex == -1) {
            throw new RoomDoesNotExistException(roomNumber);
        } else {
            Room roomObject = HotelDatabase.instance.getRooms().get(roomIndex);
            HotelDatabase.instance.removeRoom(roomObject);
        }
    }




    //Amenity CRUDs
    public void createAmenity(String name, int id, String description, double price){
        HotelDatabase.instance.addAmenity(new Amenity(name, id, description, price));
    }

    public ArrayList<Amenity> viewAmenities(){ return HotelDatabase.instance.getAmenities(); }

    public void updateAmenity(String originalAmenityName, String name, int id, String description, double price) throws AmenityDoesNotExistException {
        int originalAmenityIndex = -1;

        for (int i = 0; i < HotelDatabase.instance.getAmenities().size(); i++){
            if (HotelDatabase.instance.getAmenities().get(i).getName().equalsIgnoreCase(originalAmenityName)){
                originalAmenityIndex = i;
                break;
            }
        }

        if (originalAmenityIndex == -1){
            throw new AmenityDoesNotExistException(originalAmenityName);
        } else {
            HotelDatabase.instance.setAmenity(originalAmenityIndex, new Amenity(name, id, description, price));
        }
    }

    public void removeAmenity(String amenity) throws AmenityDoesNotExistException{
        int amenityIndex = -1;

        for (int i = 0; i < HotelDatabase.instance.getAmenities().size(); i++){
            if (HotelDatabase.instance.getAmenities().get(i).getName().equalsIgnoreCase(amenity)) {
                amenityIndex = i;
                break;
            }
        }

        if (amenityIndex == -1) {
            throw new AmenityDoesNotExistException(amenity);
        } else {
            Amenity amenityObject = HotelDatabase.instance.getAmenities().get(amenityIndex);
            HotelDatabase.instance.removeAmenity(amenityObject);
        }
    }




    //Room Type CRUDs
    public void createRoomType(String roomType, Double roomPrice){ HotelDatabase.instance.ROOM_TYPES.addType(roomType, roomPrice); }
    public ArrayList<String> viewRoomTypes(){ return HotelDatabase.instance.ROOM_TYPES.getTypes(); }

    public void updateRoomType(String originalRoomType, String newRoomType, Double originalRoomPrice, Double newRoomPrice){
        HotelDatabase.instance.ROOM_TYPES.update(originalRoomType, newRoomType, originalRoomPrice, newRoomPrice);
    }

    public void removeRoomType(String roomType){ HotelDatabase.instance.ROOM_TYPES.removeType(roomType); }



    public void confirmReservation(Reservation reservation) throws Exception{
        if (reservation.getStatus() == Reservation.ReservationStatus.PENDING){
            reservation.setStatus(Reservation.ReservationStatus.CONFIRMED);
        } else {
            throw new Exception("Reservation can't be confirmed due to non-pending status: " + reservation.getStatus());
        }
    }
}
