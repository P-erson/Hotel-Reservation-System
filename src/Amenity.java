import java.util.ArrayList;

public class Amenity
{
    private String name; // name of wifi , type of tv , name of mini-bar
    private int id; //
    private String description; // description of the amenity
    private double price;// if the hotel charges extra for any amenity
private ArrayList<String> amenities;
    public Amenity(String name , int id , String description , double price , ArrayList<String> amenities)
    {
        this.name = name;
        this.description = description;
        this.id = id;
        this.price = price;
        this.amenities = amenities;
    }
    public void setName(String n)
    {
        this.name = n;
    }
    public void setId(int ID)
    {
        this.id = ID;
    }
    public void setDescription(String d)
    {
        this.description = d;
    }
    public void setPrice(double p)
    {
        this.price = p;
    }
        public String getName()
        {
            return this.name;
        }
        public int getId()
        {
            return this.id;
        }
        public String getDescription()
        {
            return this.description;
        }
        public double getPrice()
        {
            return this.price;
        }
        public ArrayList<String> getAmenities()
        {
            return this.amenities;
        }
        public void addAmenity(String amenity)
        {
            this.amenities.add(amenity);
        }
        public void removeAmenity(String amenity)
        {
            this.amenities.remove(amenity);
        }
@Override
        public String toString()
        {
            return "the name is " +  this.name + "the id is " + this.id + "the description is "  + this.description + "the price is" + this.price;
        }



}

