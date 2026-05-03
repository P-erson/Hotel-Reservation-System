package com.example.Classes;

import java.util.ArrayList;

public class Room
{
    //as any room created will still have be avaliable so it is always true until a reservation is made so
    //its value will change there , thats why i did not add it in the constructors
    private boolean isAvailable = true;
    private int roomNumber;
    private String type;
    private ArrayList<Amenity> amenities;
    private double pricePerNight;

    public Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.amenities = new ArrayList<>();
    }

    public Room(int roomNumber , String type , ArrayList<Amenity> amenities, double pricePerNight)
    {
        this.roomNumber = roomNumber;
        this.type = type;
        this.amenities = amenities;
    }

    //Setters
    public void setRoomNumber(int RN) { roomNumber=RN; }
    public void setType(String t) { type = t; }
    public void setAmenities(ArrayList<Amenity> A) { amenities = A; }
    public void setAvailable(boolean available) {isAvailable = available;}
    public void setPricePerNight(double pricePerNight) {this.pricePerNight = pricePerNight;}

    //Getters
    public int getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public ArrayList<Amenity> getAmenities() { return amenities; }
    public boolean isAvailable() {return isAvailable;}


    public void updateAmenity(int oldAmenityId, Amenity newAmenity){
        for (int i = 0; i < amenities.size(); i++){
            if (amenities.get(i).getId() == oldAmenityId){
                amenities.remove(i);
                break;
            }
        }

        addAmenity(newAmenity);
    }
    public void addAmenity(Amenity amenity) { amenities.add(amenity); }
    public void removeAmenity(Amenity amenity) { amenities.remove(amenity); }

    public double getPricePerNight() {return pricePerNight;}
}
