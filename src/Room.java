import java.util.ArrayList;

public class Room
{
    private int roomNumber;
    private RoomType type;
    private ArrayList<Amenity> amenities;

    public Room(int roomNumber, RoomType type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.amenities = new ArrayList<>();
    }
    public Room(int roomNumber , RoomType type , ArrayList<Amenity> amenities)
    {
        this.roomNumber = roomNumber;
        this.type = type;
        this.amenities = amenities;
    }
    public void setRoomNumber(int RN)
    {
        this.roomNumber=RN;
    }
    public void setType(RoomType t)
    {
        this.type = t;
    }
    public void setAmenities(ArrayList<Amenity> A)
    {
        this.amenities = A;
    }
    public int getRoomNumber()
    {
        return this.roomNumber;
    }
    public RoomType getType()
    {
        return this.type;
    }
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
