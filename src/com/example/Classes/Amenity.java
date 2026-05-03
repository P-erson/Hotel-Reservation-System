package com.example.Classes;

public class Amenity
{
    private String name; // name of wifi , type of tv , name of mini-bar
    private int id; //
    private String description; // description of the amenity
    private double price;// if the hotel charges extra for any amenity

    public Amenity(String name , int id , String description , double price )
    {
        this.name = name;
        this.description = description;
        this.id = id;
        this.price = price;
    }


    //Setters
    public void setName(String n) { name = n; }
    public void setId(int ID) { id = ID; }
    public void setDescription(String d) { description = d; }
    public void setPrice(double p) { price = p; }


    //Getters
    public String getName() { return name; }
    public int getId() { return id; }
    public String getDescription() { return description; }
    public double getPrice() { return price;}
    /* public ArrayList<String> getAmenities()
    {
        return this.HotelDatabase.amenities;
    }
    public void addAmenity()
    {
        this.HotelDatabase.amenities.add(amenity);
    }
    public void removeAmenity()
    {
        this.HotelDatabase.amenities.remove(amenity);
    }*/


    
    @Override
    public String toString()
    {
        return "the name is " +  this.name + "the id is " + this.id + "the description is "  + this.description + "the price is" + this.price;
    }



}

