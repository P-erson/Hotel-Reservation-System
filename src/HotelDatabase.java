import java.util.ArrayList;
public class HotelDatabase {

   private ArrayList<Amenity> amenities = new ArrayList<Amenity>();

    public ArrayList<Amenity> getAmenities()
    {
        return this.amenities;
    }

    public void addAmenity(Amenity amenity)
    {
        this.amenities.add(amenity);
    }
    public void removeAmenity(Amenity amenity)
    {
        this.amenities.remove(amenity);
    }

}
