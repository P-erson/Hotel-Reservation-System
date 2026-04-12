import java.util.ArrayList;

public class Room
{
    private int roomNumber;
    private String type;
    private ArrayList<Amenity> amenities;

    public Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.amenities = new ArrayList<>();
    }

    public Room(int roomNumber , String type , ArrayList<Amenity> amenities)
    {
        this.roomNumber = roomNumber;
        this.type = type;
        this.amenities = amenities;
    }

    //Setters
    public void setRoomNumber(int RN) { roomNumber=RN; }
    public void setType(String t) { type = t; }
    public void setAmenities(ArrayList<Amenity> A) { amenities = A; }

    //Getters
    public int getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public ArrayList<Amenity> getAmenities() { return amenities; }


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

}
